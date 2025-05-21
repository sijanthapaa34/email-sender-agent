package com.sijan.EmailSender.service;

import com.sijan.EmailSender.DTO.EmailRequest;
import com.sijan.EmailSender.DTO.EmailResponse;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EmailService {

    private static final Logger log = LoggerFactory.getLogger(EmailService.class);

    @Autowired
    private JavaMailSender mailSender;

    public EmailResponse sendPersonalizedEmails(EmailRequest emailRequest) {
        List<String> recipients = emailRequest.getRecipients();
        String subject = emailRequest.getSubject();
        String cvFilePath = emailRequest.getCvFilePath();

        // Validate CV file exists
        File cvFile = new File(cvFilePath);
        if (!cvFile.exists()) {
            return new EmailResponse(false, "CV file not found at: " + cvFilePath, new HashMap<>());
        }

        Map<String, String> failedRecipients = new HashMap<>();
        int successCount = 0;

        for (String recipient : recipients) {
            try {
                sendIndividualEmail(recipient, subject, cvFile);
                log.info("Email sent successfully to: {}", recipient);
                successCount++;
            } catch (Exception e) {
                log.error("Failed to send email to: {}", recipient, e);
                failedRecipients.put(recipient, e.getMessage());
            }
        }

        boolean allSucceeded = failedRecipients.isEmpty();

        String message;
        if (allSucceeded) {
            message = String.format("Successfully sent %d emails", successCount);
        } else {
            message = String.format("Sent %d emails, %d failed", successCount, failedRecipients.size());
        }

        return new EmailResponse(allSucceeded, message, failedRecipients);
    }

    private void sendIndividualEmail(String recipient, String subject, File cvFile) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(recipient);
        helper.setSubject(subject);
        helper.setText(buildEmailBody(), false);

        // Attach CV
        FileSystemResource file = new FileSystemResource(cvFile);
        helper.addAttachment("Sijan_Thapa_CV.pdf", file);

        mailSender.send(message);
    }

    private String buildEmailBody() {
        return "Dear HR Manager,\n\n"
                + "My name is Sijan Thapa, and I am currently a second-year Bachelor of Information Technology (BIT) student at Informatics College Pokhara.\n"
                + "I am seeking a Java/Spring Boot developer internship to gain hands-on experience and contribute to real-world projects in the industry.\n\n"
                + "I have practical experience building secure RESTful APIs using Java and Spring Boot, and I am currently exploring backend architectures,tools and microservices to deepen my understanding.\n"
                + "Additionally, I am proficient in working with SQL databases and using Git for version control.\n\n"
                + "I am passionate about backend development and eager to bring my dedication, problem-solving skills, and willingness to learn to your development team.\n\n"
                + "Please feel free to explore some of my works on GitHub:\n"
                + "- AI Personal Finance Manager:\n"
                + "  A secure RESTful application for managing personal finances with AI-driven insights.\n"
                + "  Features include smart transaction categorization, budget tracking, monthly summary reports, and JWT-based authentication.\n"
                + "  GitHub: https://github.com/sijanthapaa34/Personal-Expense-Tracker\n\n"
                + "- Other Small Projects: Email Agent, Movie Rental, and more:\n"
                + "  GitHub: https://github.com/sijanthapaa34//\n\n"
                + "I have attached my CV for your consideration and would appreciate the opportunity to discuss how I can contribute to your team.\n\n"
                + "Thank you very much for your time and consideration.\n\n"
                + "Best regards,\n"
                + "Sijan Thapa";
    }

}
