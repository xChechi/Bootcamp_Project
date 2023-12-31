package chechi.nino.bootcamp.util;

import jakarta.annotation.PostConstruct;
import org.hibernate.cfg.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Component
@ConfigurationProperties(prefix = "email")
public class EmailUtils {
    /*
    @Value("${email.username}")
    private String fromEmail;

    @Value("${email.password}")
    private String password;

     */
    private String fromEmail;
    private String password;

    @Value("${email.username}")
    private void setFromEmail(String fromEmail) {
        this.fromEmail = fromEmail;
    }

    @Value("${email.password}")
    private void setPassword(String password) {
        this.password = password;
    }

    @PostConstruct
    private void init() {
        // This method will be invoked after properties are set
        System.out.println("From Email: " + fromEmail);
        System.out.println("Password: " + password);
    }

    @Async
    public void sendPasswordEmail(String toEmail, String newPassword) {
        System.out.println("From Email: " + fromEmail);
        System.out.println("Password: " + password);

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "sandbox.smtp.mailtrap.io");
        props.put("mail.smtp.port", "2525");

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        });

        String htmlContent = "<html><head>"
                + "<style>"
                + "body { background-color: #ffffff; }"
                + ".header { font-size: 24px; color: #a35e00; font-weight: bold; }"
                + ".content { font-size: 16px; color: #a35e00; }"
                + "</style>"
                + "</head><body>"
                + "<div class=\"header\">Moonlight Hotel</div>"
                + "<div class=\"content\">Here is your new generated password to log in your account:</div>"
                + "<div class=\"password\">" + newPassword + "</div>"
                + "</body></html>";

        try {
            Message message = new MimeMessage(session);
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject("Password Reset");

            //message.setText("Your new password: " + newPassword);
            message.setContent(htmlContent, "text/html; charset=utf-8");

            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}