package com.sijan.EmailSender.controller;

import com.sijan.EmailSender.DTO.EmailRequest;
import com.sijan.EmailSender.DTO.EmailResponse;
import com.sijan.EmailSender.service.EmailService;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/email")
public class EmailController {

    private static final Logger log = LoggerFactory.getLogger(EmailController.class);

    @Autowired
    private EmailService emailService;

    @PostMapping("/send")
    public ResponseEntity<EmailResponse> sendEmails(@Validated @RequestBody EmailRequest emailRequest) {
        log.info("Received request to send emails to {} recipients", emailRequest.getRecipients().size());
        EmailResponse response = emailService.sendPersonalizedEmails(emailRequest);
        return ResponseEntity.ok(response);
    }
}
