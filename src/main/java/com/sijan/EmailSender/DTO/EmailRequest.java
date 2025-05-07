package com.sijan.EmailSender.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmailRequest {

    @NotEmpty(message = "Recipients list cannot be empty")
    private List<@Email(message = "Invalid email format") String> recipients;

    @NotBlank(message = "Subject cannot be blank")
    private String subject;

    @NotBlank(message = "CV file path cannot be blank")
    private String cvFilePath;
}
