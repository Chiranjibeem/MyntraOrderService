package com.example.mailexchange.MailExchange.configure;

import com.example.mailexchange.MailExchange.model.EmailStatus;
import com.example.mailexchange.MailExchange.model.EmailTemplate;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.bean.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Configuration
public class EmailServiceImpl {

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String fromEmailProp;

    public boolean generateStatusFile(List<EmailStatus> statuses, String fileName) {
        FileWriter fileWriter = null;
        CSVWriter writer = null;
        File file = null;
        try {
            if (fileName != null) {
                file = new File(fileName.replaceAll("%20", " "));
                if (!file.exists()) {
                    file.createNewFile();
                    file.canRead();
                    file.canWrite();
                    file.canExecute();
                }
            }
            fileWriter = new FileWriter(file, false);
            List<String[]> data = statuses.stream().map(i -> new String[]{i.getEmail(), i.getEmail(), i.getStatus()}).collect(Collectors.toList());
            writer = new CSVWriter(fileWriter, ',',
                    CSVWriter.NO_QUOTE_CHARACTER,
                    CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                    CSVWriter.DEFAULT_LINE_END);
            writer.writeAll(data);
            return true;
        } catch (Exception e) {
            return false;
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (Exception e) {

                }
            }
            if (fileWriter != null) {
                try {
                    fileWriter.close();
                } catch (Exception e) {
                }
            }
        }
    }


    public List<EmailTemplate> getAllReport(File file) throws Exception {
        List<EmailTemplate> emailTemplatesList = new ArrayList<>();
        HeaderColumnNameTranslateMappingStrategy<EmailTemplate> strategy
                = new HeaderColumnNameTranslateMappingStrategy<EmailTemplate>();
        strategy.setType(EmailTemplate.class);
        strategy.setColumnMapping(EmailTemplate.hashMap);

        CSVReader csvReader = null;
        csvReader = new CSVReader(new FileReader(file));
        CsvToBean csvToBean = new CsvToBean();
        csvToBean.setMappingStrategy(strategy);
        csvToBean.setCsvReader(csvReader);
        emailTemplatesList = csvToBean.parse();
        return emailTemplatesList;
    }

    public void sendEmailWithAttachment(String fromEmail, String personName, String subject, String message, String... toEmail) throws Exception {

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

        helper.setFrom(fromEmail, personName);
        helper.setTo(toEmail);
        helper.setSubject(subject);
        helper.setText(message, true);

        //ClassPathResource classPathResource = new ClassPathResource("Attachment.pdf");
        //helper.addAttachment(classPathResource.getFilename(), classPathResource);

        javaMailSender.send(mimeMessage);
    }
}