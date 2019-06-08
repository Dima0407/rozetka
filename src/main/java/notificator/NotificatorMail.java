package notificator;

import org.apache.log4j.Logger;
import utils.PropertiesLoader;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Properties;

public class NotificatorMail {

    private final String PATH_TO_PROPERTIES = "src/main/resources/mail.properties";

    private Logger logger;
    private String from;
    private String user;
    private String pass;

    public NotificatorMail() {
        Properties properties = PropertiesLoader.loadProperties(PATH_TO_PROPERTIES);
        logger = Logger.getLogger(NotificatorMail.class.getName());
        from = properties.getProperty("mail.from");
        user = properties.getProperty("mail.user");
        pass = properties.getProperty("mail.pass");
    }


    public void sendMessage(String pathTo, String text, String filePath) {

        String addressList = getAddressList(pathTo);

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(user, pass);
                    }
                });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(addressList));
            message.setSubject("Test Result");
            BodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText(text);
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);
            messageBodyPart = new MimeBodyPart();
            DataSource source = new FileDataSource(filePath);
            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName(filePath);
            multipart.addBodyPart(messageBodyPart);

            message.setContent(multipart);

            Transport.send(message);

            logger.info("Results are exported [" + addressList.substring(0, addressList.length() - 1) + "]");
        } catch (MessagingException e) {
            logger.error("Error exporting results [" + addressList.substring(0, addressList.length() - 1) + "]");
            e.printStackTrace();
        }
    }

    private String getAddressList(String filePath) {
        List<String> lines = null;
        StringBuilder addressList = new StringBuilder();
        try {
            lines = Files.readAllLines(Paths.get(filePath), StandardCharsets.UTF_8);
        } catch (IOException e) {
            logger.error("Error reading a file [" + filePath + "]");
            e.printStackTrace();
        }
        if (lines != null) {
            for (String address : lines) {
                addressList.append(address).append(",");
            }
        }
        return addressList.toString();
    }
}