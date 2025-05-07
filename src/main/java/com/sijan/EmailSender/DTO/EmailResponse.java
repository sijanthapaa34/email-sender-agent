package com.sijan.EmailSender.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmailResponse {
    private boolean success;
    private String message;
    private Map<String, String> failedRecipients = new HashMap<>();
}
