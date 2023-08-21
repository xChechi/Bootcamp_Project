package chechi.nino.bootcamp.util;

import org.hibernate.cfg.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Component
public class EmailUtils {

    private final String fromEmail;
    private final String password;

    @Autowired
    public EmailUtils(String fromEmail, String emailPassword) {
        this.fromEmail = fromEmail;
        this.password = emailPassword;
    }

    public static void sendPasswordEmail(String toEmail, String newPassword) {

        //Manually added javax.mail to project structure. Jar file is in lib folder

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.abv.bg");
        props.put("mail.smtp.port", "465");

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return null;
                //return new PasswordAuthentication(fromEmail, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            //message.setFrom(new InternetAddress(fromEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject("Password Reset");
            message.setText("Your new password: " + newPassword);

            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

}
