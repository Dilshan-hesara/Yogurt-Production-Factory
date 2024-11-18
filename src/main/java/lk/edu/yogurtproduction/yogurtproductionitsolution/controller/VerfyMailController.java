package lk.edu.yogurtproduction.yogurtproductionitsolution.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import lk.edu.yogurtproduction.yogurtproductionitsolution.dto.UserDto;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.Random;

public class VerfyMailController {

    @FXML
    private TextField txtOtp;


    @FXML
    private Label txtMail;

    @FXML
    private Label txtUser;



    private String generatedOtp;
    private static final String SENDER_EMAIL = "mkdhyogurtfactory@gmail.com";
    private static final String SENDER_PASSWORD = "vcev juis zcnl pifa"; // Use an app password here
    private String recipientEmail;
    private boolean isVerified = false;
    private String getUserName;

    /**
     * Sets the user details and generates the OTP for email verification.
     */

    public void setUserDetails(UserDto user) {
        if (user != null) {
            this.recipientEmail = user.getPassword();
            txtMail.setText(user.getPassword());
            getUserName = user.getUsername();
            txtUser.setText(getUserName);
            generateOtp();
            sendOtpEmail();
        }
    }

    /**
     * Generates a random OTP and assigns it to the `generatedOtp` variable.
     */
    private void generateOtp() {
        int otp = 100000 + new Random().nextInt(900000); // Generate a 6-digit OTP
        generatedOtp = String.valueOf(otp);
        System.out.println("Generated OTP: " + generatedOtp); // For debugging purposes
    }

    /**
     * Sends the OTP to the recipient's email.
     */
    private void sendOtpEmail() {
        if (recipientEmail == null || !isValidEmail(recipientEmail)) {
            showAlert(Alert.AlertType.WARNING, "Invalid email address! Cannot send OTP.");
            return;
        }

        String subject = "Your OTP for Verification";
        String body = "Dear "+getUserName+",\n\nYour OTP is: " + generatedOtp + "\n\nPlease use this to verify your email.\n\nRegards,\nMKD Yogurt Factory";

        // Configure email properties
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(SENDER_EMAIL, SENDER_PASSWORD);
            }
        });

        // Send email in a background thread
        new Thread(() -> {
            try {
                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress(SENDER_EMAIL));
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
                message.setSubject(subject);
                message.setText(body);

                Transport.send(message);
                Platform.runLater(() -> showAlert(Alert.AlertType.INFORMATION, "OTP email sent successfully!"));
            } catch (MessagingException e) {
                e.printStackTrace();
                Platform.runLater(() -> showAlert(Alert.AlertType.ERROR, "Error sending email: " + e.getMessage()));
            }
        }).start();
    }

    /**
     * Validates the OTP entered by the user.
     */
    @FXML
    void verifyOtp(ActionEvent event) {
        String enteredOtp = txtOtp.getText().trim();
        if (generatedOtp != null && generatedOtp.equals(enteredOtp)) {
            isVerified = true;
            showAlert(Alert.AlertType.INFORMATION, "OTP verified successfully!");
            closeWindow(); // Close the verification window
        } else {
            showAlert(Alert.AlertType.ERROR, "Invalid OTP. Please try again.");
        }
    }

    /**
     * Checks if the OTP verification was successful.
     *
     * @return true if verified, false otherwise.
     */
    public boolean isVerified() {
        return isVerified;
    }

    /**
     * Validates the format of an email address.
     */
    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return email.matches(emailRegex);
    }

    /**
     * Displays an alert with the specified type and message.
     */
    private void showAlert(Alert.AlertType alertType, String message) {
        Alert alert = new Alert(alertType);
        alert.setContentText(message);
        alert.showAndWait();
    }

    /**
     * Closes the current window.
     */
    private void closeWindow() {
        Platform.runLater(() -> txtOtp.getScene().getWindow().hide());
    }

    public void VeffiMail(ActionEvent actionEvent) {

        String enteredOtp = txtOtp.getText().trim();
        if (generatedOtp != null && generatedOtp.equals(enteredOtp)) {
            isVerified = true;
            showAlert(Alert.AlertType.INFORMATION, "OTP verified successfully!");
            closeWindow(); // Close the verification window
        } else {
            showAlert(Alert.AlertType.ERROR, "Invalid OTP. Please try again.");
        }
    }
}
