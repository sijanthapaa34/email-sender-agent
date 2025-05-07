# 📧 Email Sender Agent

A simple yet powerful Java Spring Boot application that automates sending personalized internship/job emails with a PDF CV attachment to multiple recipients.

## 🚀 Features

- Accepts a list of email addresses via API.
- Sends emails **individually** to each recipient.
- Includes a **custom message** and the **current date** in the body.
- Attaches a **PDF CV file** from a local path.
- Uses Spring Boot's `JavaMailSender` with SMTP.
- Handles validation and failure reporting gracefully.

## 🛠️ Tech Stack

- Java 21
- Spring Boot
- Spring Boot Starter Mail
- Jakarta Bean Validation
