import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class MailGUI extends JFrame {


    static JButton submit = new JButton();
    static JPanel loginpanel = new JPanel();
    static JTextField recipient = new JTextField();
    static JTextField sender= new JTextField();
    static JTextField password= new JTextField();
    static JTextPane text = new JTextPane();
    static JTextField year= new JTextField();
    static JTextField month= new JTextField();
    static JTextField day= new JTextField();
    static JTextField hour= new JTextField();
    static JTextField minute= new JTextField();

    static JLabel yearLabel = new JLabel("Year");
    static JLabel monthLabel = new JLabel("Month");
    static JLabel dayLabel = new JLabel("Day");
    static JLabel hourLabel= new JLabel("Hour");
    static JLabel minuteLabel= new JLabel("Minute");

    static JLabel recipientLabel = new JLabel("Recipient");
    static JLabel passwordLabel = new JLabel("Password");
    static JLabel textLabel = new JLabel("text");
    static JLabel senderLabel = new JLabel("Sender");
    static JFrame mainFrame ;





    public MailGUI(){

        mainFrame = new JFrame ("Send E-mail"); //Create frame
        mainFrame.setBounds(500, 280, 300, 200); //Set size to screen
        submit = new JButton("Submit");
        loginpanel = new JPanel();
        //  txuser = new JTextField(15);
        //  pass = new JPasswordField(15);

        //username = new JLabel("E-mail - ");
        //   password = new JLabel("Password - ");
        loginpanel.setLayout (null);
        loginpanel.add(submit);
        loginpanel.add(sender);
        loginpanel.add(senderLabel);
        loginpanel.add(password);
        loginpanel.add(passwordLabel);
        loginpanel.add(recipient);
        loginpanel.add(recipientLabel);
        loginpanel.add(text);
        loginpanel.add(textLabel);
        loginpanel.add(year);
        loginpanel.add(month);
        loginpanel.add(day);
        loginpanel.add(hour);
        loginpanel.add(minute);
        loginpanel.add(yearLabel);
        loginpanel.add(monthLabel);
        loginpanel.add(dayLabel);
        loginpanel.add(hourLabel);
        loginpanel.add(minuteLabel);

        sender.setBounds(70,30,150,20);
        password.setBounds(70,65,150,20);
        recipient.setBounds(70,100,150,20);
        text.setBounds(70,135,500,160);
        year.setBounds(70,325,40,40);
        month.setBounds(70,365,40,40);
        day.setBounds(70,405,40,40);
        hour.setBounds(70,445,40,40);
        minute.setBounds(70,485,40,40);
        submit.setBounds(70,520,40,40);

        senderLabel.setBounds(0,30,60,20);
        textLabel.setBounds(0,135,60,20);
        recipientLabel.setBounds(0,100,60,20);
        passwordLabel.setBounds(0,65,60,20);
        yearLabel.setBounds(0,325,60,20);
        monthLabel.setBounds(0,365,60,20);
        dayLabel.setBounds(0,405,60,20);
        hourLabel.setBounds(0,445,60,20);
        minuteLabel.setBounds(0,485,60,20);

        mainFrame.getContentPane().add(loginpanel);
        mainFrame.setVisible(true);

        submit.addActionListener(new submit_Action());
    }

    static class submit_Action implements ActionListener{
        public void actionPerformed (ActionEvent e){
         
          try{
           File file = new File("mail.txt");
           FileWriter filewrite = new FileWriter(file, true);
           String userName = MailGUI.sender.getText();
           String recipient =  MailGUI.recipient.getText();
           String password = MailGUI.password.getText();
           String body = MailGUI.text.getText();
           String year = MailGUI.year.getText();
           String month = MailGUI.month.getText();    // 0 to 11
           String day = MailGUI.day.getText();
           String hour = MailGUI.hour.getText();
           String minute = MailGUI.minute.getText();
           filewrite.write(userName+"\r\n" +recipient+ "\r\n" + password + "\r\n" + body + "\r\n" + year + "\r\n" + month + "\r\n" + day + "\r\n" + hour +"\r\n" + minute + "\r\n");
           filewrite.close();
           
        //   Mail sendMail = new Mail( userName, recipient, password, body, year, month, day, hour, minute);
          }
          catch (IOException d) {
                    d.printStackTrace();
                }
     
         //  Mail sendMail = new Mail( userName, recipient, password, body, year, month, day, hour, minute);
          
        }
    }



}