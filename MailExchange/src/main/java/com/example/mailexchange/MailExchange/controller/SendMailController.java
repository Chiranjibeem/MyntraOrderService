package com.example.mailexchange.MailExchange.controller;

import com.example.mailexchange.MailExchange.configure.EmailServiceImpl;
import com.example.mailexchange.MailExchange.configure.EmailTaskExecutor;
import com.example.mailexchange.MailExchange.model.*;
import com.example.mailexchange.MailExchange.repository.ErrorReasonRepository;
import com.example.mailexchange.MailExchange.repository.SequenceTestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

@Controller
public class SendMailController {

    private final String UPLOAD_DIR = System.getProperty("user.home") + "/";
    private final String DOWNLOAD_DIR = System.getProperty("user.dir") + "/";
    @Autowired
    public EmailServiceImpl emailService;

    @Autowired
    public ErrorReasonRepository errorReasonRepository;

    @Autowired
    private ExecutorService executorService;

    @Autowired
    private SequenceTestRepository sequenceTestRepository;

    @RequestMapping("/sendMailList")
    public ModelAndView sendMailList(Model model) {
        return new ModelAndView("sendMailList");
    }

    public String getTimestamp() {
        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyymmddhhmmss");
        String strDate = dateFormat.format(date);
        return strDate;
    }

    public List<EmailStatus> sendEmailInvokeAll(List<EmailTaskExecutor> emailTaskExecutorList) throws Exception {
        List<Future<EmailStatus>> emailResponses = executorService.invokeAll(emailTaskExecutorList);
        if (!CollectionUtils.isEmpty(emailResponses)) {
            List<EmailStatus> successResponse = emailResponses.stream().map(sResponse -> {
                try {
                    EmailStatus emailStatus = sResponse.get();
                    return emailStatus;
                } catch (InterruptedException i) {

                } catch (ExecutionException e) {

                }
                return null;
            }).collect(Collectors.toList());
            return successResponse;

        }
        return null;
    }

    @RequestMapping("/sendNewEmail")
    public ModelAndView sendNewEmail(@ModelAttribute("newEmail") SendNewEmail newEmail1, BindingResult result, Model model) {
        if (newEmail1 != null && newEmail1.getTemplate() == null) {
            return new ModelAndView("sendNewEmail");
        } else {
            SendNewEmail newEmail = new SendNewEmail();
            newEmail.setSenderEmail(newEmail1.getSenderEmail().trim());
            newEmail.setSenderName(newEmail1.getSenderName().trim());
            newEmail.setSubject(newEmail1.getSubject().trim());
            newEmail.setMessage(newEmail1.getMessage());
            newEmail.setTemplate(newEmail1.getTemplate());

            String fileName = StringUtils.cleanPath(newEmail.getTemplate().getOriginalFilename());
            try {
                if (fileName.toUpperCase().endsWith("CSV") || fileName.toUpperCase().endsWith("TXT")) {
                    Path path = Paths.get(UPLOAD_DIR + fileName + getTimestamp());
                    Files.copy(newEmail.getTemplate().getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
                    File file = path.toFile();

                    List<ErrorReason> errorReasons = new ArrayList<>();
                    List<EmailTemplate> emailTemplateList = emailService.getAllReport(file);

                    int successCount = 0;
                    int failureCount = 0;
                    int totalcount = emailTemplateList.size();
                    boolean flag = false;
                    String newFormatEmail = newEmail.getMessage();
                    if (newFormatEmail.contains("Dear,")) {
                        flag = true;
                    }
                    List<EmailStatus> failureResponse = new ArrayList<>();
                    List<EmailTaskExecutor> emailTaskExecutorList = new ArrayList<>();
                    EmailStatus header = new EmailStatus("email", "name", "status");
                    for (EmailTemplate emailTemplate : emailTemplateList) {
                        if (flag) {
                            newFormatEmail = newFormatEmail.replaceFirst("Dear,", "Dear " + emailTemplate.getName() + ",");
                        }
                        EmailTaskExecutor emailTaskExecutor = new EmailTaskExecutor(newEmail.getSenderEmail(), newEmail.getSenderName(), newEmail.getSubject(), newFormatEmail, emailTemplate.getEmail().trim(), emailTemplate.getName().trim(),emailService);
                        emailTaskExecutorList.add(emailTaskExecutor);
                    }

                    if (!CollectionUtils.isEmpty(emailTaskExecutorList)) {
                        try {
                            List<EmailStatus> emailStatusList = sendEmailInvokeAll(emailTaskExecutorList);
                            List<EmailStatus> successResponse = emailStatusList.stream().filter(response -> "SUCCESS".equalsIgnoreCase(response.getStatus())).collect(Collectors.toList());
                            failureResponse = emailStatusList.stream().filter(response -> !"SUCCESS".equalsIgnoreCase(response.getStatus())).collect(Collectors.toList());
                            if (!CollectionUtils.isEmpty(successResponse)) {
                                successCount = successResponse.size();
                            }
                            if (!CollectionUtils.isEmpty(failureResponse)) {
                                failureCount = failureResponse.size();
                                errorReasons = failureResponse.stream().map(failure -> {
                                    ErrorReason reason = new ErrorReason();
                                    reason.setFromEmail(failure.getEmail());
                                    reason.setToEmail(failure.getName());
                                    reason.setErrorMsg("Error");
                                    return reason;
                                }).collect(Collectors.toList());
                            }
                            if (!CollectionUtils.isEmpty(errorReasons)) {
                                errorReasonRepository.saveAll(errorReasons);
                            }
                            if (!CollectionUtils.isEmpty(failureResponse)) {
                                fileName = fileName.substring(0,fileName.lastIndexOf("."));
                                boolean fileCreated = emailService.generateStatusFile(failureResponse, DOWNLOAD_DIR + fileName + getTimestamp()+".csv");
                                if (fileCreated) {
                                    model.addAttribute("fileCreated", "Y");
                                } else {
                                    model.addAttribute("fileCreated", "N");
                                }
                            } else {
                                model.addAttribute("fileCreated", "N");
                            }
                            model.addAttribute("successcount", successCount);
                            model.addAttribute("failurecount", failureCount);
                            model.addAttribute("totalcount", totalcount);
                        } catch (Exception e) {
                        }
                    }
                } else {
                    throw new Exception("File Format Wrong");
                }

            } catch (Exception e) {
                model.addAttribute("error", "File Format Wrong");
            }
        }

        //need to remove
        SequenceTest sequenceTest = new SequenceTest();
        sequenceTest.setSequenceName("NAME");
        sequenceTestRepository.save(sequenceTest);

        return new ModelAndView("sendNewEmail");
    }
}
