package app;

import io.github.cdimascio.dotenv.Dotenv;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailSender {
    // Regular expression for email validation
    private static final Pattern EMAIL_PATTERN = Pattern.compile(
            "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$",
            Pattern.CASE_INSENSITIVE
    );

    public void sendEmail(String to, String subject, String body) throws InvalidEmailException {
        // Validate email first
        if (!isValidEmail(to)) {
            throw new InvalidEmailException("Invalid email address: " + to);
        }
        Dotenv dotenv = Dotenv.load();
        final String fromEmail = dotenv.get("EMAIL");
        final String password = dotenv.get("EMAIL_PASSWORD");

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(fromEmail, "UniSpend"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(subject);
            message.setContent(body, "text/html; charset=UTF-8");

            Transport.send(message);
            System.out.println("Email sent successfully!");

        } catch (MessagingException | UnsupportedEncodingException e) {
            // Log the specific error
            System.err.println("Email sending failed: " + e.getMessage());
            throw new EmailSendingException("Failed to send email", e);
        }
    }

    // Method to validate email format
    private boolean isValidEmail(String email) {
        if (email == null) {
            return false;
        }

        Matcher matcher = EMAIL_PATTERN.matcher(email);
        return matcher.matches();
    }

    // Custom exception for invalid email
    public static class InvalidEmailException extends Exception {
        public InvalidEmailException(String message) {
            super(message);
        }
    }

    // Custom exception for email sending failures
    public static class EmailSendingException extends RuntimeException {
        public EmailSendingException(String message, Throwable cause) {
            super(message, cause);
        }
    }
}