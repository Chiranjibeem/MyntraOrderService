package com.example.mailexchange.MailExchange.controller;

import com.example.mailexchange.MailExchange.configure.EmailServiceImpl;
import com.example.mailexchange.MailExchange.model.EmailTemplate;
import com.example.mailexchange.MailExchange.model.SendNewEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

@Controller
public class SendMailController {

    private final String UPLOAD_DIR = System.getProperty("user.home") + "/";
    @Autowired
    public EmailServiceImpl emailService;

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

    @RequestMapping("/sendNewEmail")
    public ModelAndView sendNewEmail(@ModelAttribute("newEmail") SendNewEmail newEmail, BindingResult result, Model model) {
        if (newEmail != null && newEmail.getTemplate() == null) {
            return new ModelAndView("sendNewEmail");
        } else {
            String fileName = StringUtils.cleanPath(newEmail.getTemplate().getOriginalFilename());
            try {
                if (fileName.toUpperCase().endsWith("CSV") || fileName.toUpperCase().endsWith("TXT")) {
                    Path path = Paths.get(UPLOAD_DIR + fileName + getTimestamp());
                    Files.copy(newEmail.getTemplate().getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
                    File file = path.toFile();

                    List<EmailTemplate> emailTemplateList = emailService.getAllReport(file);

                    int successCount = 0;
                    int failureCount = 0;
                    for (EmailTemplate emailTemplate : emailTemplateList) {
                        try {
                            emailService.sendEmailWithAttachment(newEmail.getSenderEmail(), newEmail.getSenderName(), newEmail.getSubject(), newEmail.getMessage(), emailTemplate.getEmail());
                            successCount = successCount + 1;
                        } catch (Exception e) {
                            e.printStackTrace();
                            failureCount = failureCount + 1;
                        }
                    }
                    model.addAttribute("successcount", successCount);
                    model.addAttribute("failurecount", failureCount);
                    System.out.println(emailTemplateList.size());
                } else {
                    throw new Exception("File Format Wrong");
                }

            } catch (Exception e) {
                model.addAttribute("error", "File Format Wrong");
            }
        }

        return new ModelAndView("sendNewEmail");
    }
}
