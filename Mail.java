import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Calendar;
import java.lang.String;

public class Mail {

    private static String USER_NAME = MailGUI.sender.getText();  // GMail user name (just the part before "@gmail.com")
    private static String PASSWORD = MailGUI.password.getText(); // GMail password
    private static String RECIPIENT = MailGUI.recipient.getText();


    public Mail (String getUserName,  String getRecipient,  String getPassword, String getBody, String getYear, String getMonth, String getDay, String getHour, String getMinute)
    {
        System.out.println(RECIPIENT);
        System.out.println(MailGUI.year.getText());
        System.out.println(PASSWORD);
        System.out.println(USER_NAME);



        Calendar cal = Calendar.getInstance();
        cal.set(Integer.parseInt(getYear),Integer.parseInt(getMonth)-1,Integer.parseInt(getDay),Integer.parseInt(getHour),Integer.parseInt(getMinute),00);
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);      // 0 to 11
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int minute = cal.get(Calendar.MINUTE);
        int second = cal.get(Calendar.SECOND);

        Date time = cal.getTime();

        String[] to = {getRecipient}; // list of recipient email addresses
        String subject = "Time Manager";


        Timer myTimer = new Timer();
        TimerTask gorev =new TimerTask()
        {
            @Override
            public void run()
            {

                sendFromGMail(getUserName, getPassword, to, subject, getBody);

            }
        };
        myTimer.schedule(gorev,time);
    }

    public static void sendFromGMail (String from, String pass, String[] to, String subject, String body)
    {
        Properties props = System.getProperties();
        String host = "smtp.gmail.com";
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.user", from);
        props.put("mail.smtp.password", pass);
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");

        Session session = Session.getDefaultInstance(props);
        MimeMessage message = new MimeMessage(session);

        try
        {
            message.setFrom(new InternetAddress(from));
            InternetAddress[] toAddress = new InternetAddress[to.length];

            // To get the array of addresses
            for( int i = 0; i < to.length; i++ )
            {
                toAddress[i] = new InternetAddress(to[i]);
            }

            for( int i = 0; i < toAddress.length; i++)
            {
                message.addRecipient(Message.RecipientType.TO, toAddress[i]);
            }

            message.setSubject(subject);
            message.setText(body);
            Transport transport = session.getTransport("smtp");
            transport.connect(host, from, pass);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
            System.out.println("Mail was sent");
        }
        catch (AddressException ae)
        {
            ae.printStackTrace();
        }
        catch (MessagingException me)
        {
            me.printStackTrace();
        }
    }
}
