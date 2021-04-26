package pp.spring.cookbook.mail;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class MailSenderService {

    private static final String DOMAIN_OWNER_EMAIL = "cookbook@byom.de";

    private JavaMailSender javaMailSender;

    public MailSenderService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendMailsFromContactForm(String email, String username, String content) throws MessagingException {
        sendEmailToDomainOwner(email, username, content);
        sendConfirmationToClient(email, username, content);
    }

    private void sendEmailToDomainOwner(String email, String username, String content) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
        mimeMessageHelper.setTo(DOMAIN_OWNER_EMAIL);
        mimeMessageHelper.setFrom(email);
        mimeMessageHelper.setSubject("Message from contact form");
        mimeMessageHelper.setReplyTo(email);
        String text = "<p>Sender: " + username + "(" + email + ") </p>";
        text += "<p>Subject: </p>";
        text += content;
        mimeMessageHelper.setText(text, true);
        javaMailSender.send(mimeMessage);
    }

    private void sendConfirmationToClient(String email, String username, String content) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
        mimeMessageHelper.setTo(email);
        mimeMessageHelper.setFrom(DOMAIN_OWNER_EMAIL);
        mimeMessageHelper.setSubject("Confirmation of sending email");
        mimeMessageHelper.setReplyTo(DOMAIN_OWNER_EMAIL);
        String text = "<p>Hi" + username + "! Your message has been sent! </p>";
        text += "<p>Message: </p>";
        text += content;
        mimeMessageHelper.setText(text, true);
        javaMailSender.send(mimeMessage);
    }

    public void sendPasswordResetLink(String email, String key) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
        mimeMessageHelper.setTo(email);
        mimeMessageHelper.setFrom(DOMAIN_OWNER_EMAIL);
        mimeMessageHelper.setSubject("Reset link: ");
        String link = "<a href=\"http://localhost:8080/resetHasla?klucz=" + key + "\">Click</a>";
        String text = "<p>Hi! Here you can find link to reset your password: + " + link + "</p>";
        mimeMessageHelper.setText(text, true);
        javaMailSender.send(mimeMessage);
    }
}
