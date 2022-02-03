import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.*;
import java.io.File;
import java.io.IOException;
import java.util.Properties;

public class EmailFile {

    private static EmailFile instance = null;

    public static EmailFile getInstance(){
        if(instance == null){
            instance = new EmailFile();
        }
        return instance;
    }

    private EmailFile(){

    }

    public void sendEmail(String to, String title, String body, String path)
            throws MessagingException, IOException {

        Session session = sessionCreate();
        System.out.println("Sending email to : " + to);

        Message message = new MimeMessage(session);
        Multipart multipart = messageBodyPreparation(message, to, title, body, path);

        message.setContent(multipart);
        Transport.send(message);
        System.out.println("Email Sent Successfully to :"+to+" !!!");
    }

    /*
        Creating a proper message using sender's, receiver's mail ids and body
        and title.
     */
    private Multipart messageBodyPreparation(Message message, String to,
                                               String title, String body, String path)
            throws MessagingException {

        message.setFrom(new InternetAddress(Main.SENDER_EMAIL));
        message.setRecipients(Message.RecipientType.TO,
                InternetAddress.parse(to));
        message.setSubject(title);
        //message.setContent(body, "text/html; charset=utf-8");
        BodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setContent(body, "text/html; charset=utf-8");

        MimeBodyPart attachmentPart = new MimeBodyPart();
        try {
            attachmentPart.attachFile(new File(path));
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);
        multipart.addBodyPart(attachmentPart);
        return multipart;
    }

    /*
        Creating a session with the connection of Javax.mail
     */
    private Session sessionCreate() {

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.ssl.protocols", "TLSv1.2");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {

                    protected PasswordAuthentication
                    getPasswordAuthentication() {

                        /*System.out.println("Authenticating with Email : " +
                                Main.SENDER_EMAIL + " " +
                                "and Password : " + Main.PASSWORD + "");*/

                        return new PasswordAuthentication(Main.SENDER_EMAIL,
                                Main.PASSWORD);
                    }

                });

        return session;
    }

    //public SendEmailToTAs(List<>){}

}
