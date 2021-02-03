import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class login extends JFrame {
  static JButton blogin;
  static JPanel loginpanel;
  static JTextField txuser;
  static JTextField pass;
  static JButton newUSer;
  static JLabel username;
  static JLabel password;
  static JFrame mainFrame;
  static int index = 0;
  
  public static void main(String[] args){
    
    mainFrame = new JFrame ("Login To Time Manager"); //Create frame
    mainFrame.setBounds(500, 280, 300, 200); //Set size to screen
    blogin = new JButton("Login");
    loginpanel = new JPanel();
    txuser = new JTextField(15);
    pass = new JPasswordField(15);
    newUSer = new JButton("Sign Up?");
    username = new JLabel("E-mail - ");
    password = new JLabel("Password - ");
    loginpanel.setLayout (null);
    
    
    txuser.setBounds(70,30,150,20);
    pass.setBounds(70,65,150,20);
    blogin.setBounds(110,100,80,20);
    newUSer.setBounds(110,135,80,20);
    username.setBounds(20,28,80,20);
    password.setBounds(20,63,80,20);
    
    loginpanel.add(blogin);
    loginpanel.add(txuser);
    loginpanel.add(pass);
    loginpanel.add(newUSer);
    loginpanel.add(username);
    loginpanel.add(password);
    
    
    mainFrame.getContentPane().add(loginpanel);
    mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    mainFrame.setVisible(true);
    
    Writer writer = null;
    File check = new File("userPass.txt");
    
    ArrayList<String> dataBase = new ArrayList<String>();
    dataBase.add("currentActivities - 1.ser");
    dataBase.add("currentActivities - 2.ser");
    dataBase.add("currentActivities - 3.ser");
    dataBase.add("currentActivities - 4.ser");
    dataBase.add("currentActivities - 5.ser");
    
    
    if(check.exists()){
      
    }else{
      try{
        File texting = new File("userPass.txt");
        writer = new BufferedWriter(new FileWriter(texting));
        writer.write("message");
      }catch(IOException e){
        e.printStackTrace();
      }
    }
    
    
    
    
    blogin.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        try {
          File file = new File("userPass.txt");
          File mail = new File("mail.txt");
          Scanner scan = new Scanner(file);
          Scanner scanMail = new Scanner(mail);;
          String line = null;
          FileWriter filewrite = new FileWriter(file, true);
          String userName = "";
          String recipient =  "";
          String password = "";
          String body = "";
          String year = "";
          String month = "";    // 0 to 11
          String day = "";
          String hour = "";
          String minute = "";
          String usertxt = "";
          String passtxt = "";
          String puname = txuser.getText();
          String ppaswd = pass.getText();
          
          
          while ( scan.hasNext()  && !puname.equals(usertxt) && !ppaswd.equals(passtxt)) {
            usertxt = scan.nextLine();
            passtxt = scan.nextLine();
            if(puname.equals(usertxt) && ppaswd.equals(passtxt)) {
              CalendarProgram menu = new CalendarProgram();
              mainFrame.setVisible(false);
              
              while ( scanMail.hasNext() ) {
   
                userName = scanMail.nextLine();
                recipient = scanMail.nextLine();
                password = scanMail.nextLine();
                body = scanMail.nextLine();
                year = scanMail.nextLine();
                month = scanMail.nextLine();
                day = scanMail.nextLine();
                hour = scanMail.nextLine();
                minute = scanMail.nextLine();
                
                 Mail mailSend = new Mail(userName, recipient, password, body, year, month, day, hour, minute);
                
              }
            }
            else if(puname.equals("") && ppaswd.equals("")){
              JOptionPane.showMessageDialog(null," enter username and password ");
              
            }
            else {
              txuser.setText("");
              pass.setText("");
              txuser.requestFocus();
              
            }
            index++;
          }
          
          if( !puname.equals(usertxt) && !ppaswd.equals(passtxt)){
            
            JOptionPane.showMessageDialog(null,"wrong username or password");
          }
          
          
        } catch (IOException d) {
          d.printStackTrace();
        }
        
      }
    });
    
    
    newUSer.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e) {
        NewUser user = new NewUser();
        
        
      }
    });
  }
  
}
