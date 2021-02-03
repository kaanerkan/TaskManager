

//Import packages
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.lang.String;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ComponentListener;
import java.awt.*;  
import java.awt.event.*;  
import javax.swing.*;  
import java.util.*;  
import java.text.*;  
import java.io.*;  
import java.lang.*;
    

public class CalendarProgram{
 static JLabel lblMonth, lblYear;
 static JButton buttonPrev, buttonNext;
 static JButton addEvent;
 static JButton scheduledMail;
 static ImageIcon addEventIcon;
 static JTable tableCalendar;
 static JComboBox cmbYear;
 static JFrame frameMain;
 static Container pane;
 static DefaultTableModel mtableCalendar; //Table model
 static JScrollPane stableCalendar; //The scrollpane
 static JPanel pnlCalendar;
 static int realYear, realMonth, realDay, currentYear, currentMonth;
 static JPanel buttonPanel;

 public CalendarProgram(){



  Activity a = new Activity();

  //Look and feel
  try {UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());}
  catch (ClassNotFoundException e) {}
  catch (InstantiationException e) {}
  catch (IllegalAccessException e) {}
  catch (UnsupportedLookAndFeelException e) {}

  //Prepare frame
  Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
  frameMain = new JFrame ("Time Manager"); //Create frame
  frameMain.setBounds(0, 0, screenSize.width, screenSize.height); //Set size to screen
  pane = frameMain.getContentPane(); //Get content pane
  pane.setLayout(null); //Apply null layout
  frameMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Close when X is clicked
  Rectangle r = frameMain.getBounds();
  //Create controls
  lblMonth = new JLabel ("January");
  lblYear = new JLabel ("Change year:");
  cmbYear = new JComboBox();
  buttonPrev = new JButton ("<<");
  buttonNext = new JButton (">>");
  addEventIcon = new ImageIcon("C://Users//Tahir//Desktop//cs102//proje//project//add-event.png");
  addEvent = new JButton (addEventIcon);
  mtableCalendar = new DefaultTableModel(){public boolean isCellEditable(int rowIndex, int mColIndex){return false;}};
  tableCalendar = new JTable(mtableCalendar);
  stableCalendar = new JScrollPane(tableCalendar);
  pnlCalendar = new JPanel(null);
  buttonPanel = new JPanel();
  scheduledMail = new JButton("Send Email");
  buttonPanel.setPreferredSize(new Dimension (screenSize.width/5,screenSize.height));
  JLabel imgLabel = new JLabel();
  imgLabel.setBounds(0,0,screenSize.width/6,screenSize.height/3);

  addEvent.setToolTipText("Add Event");
  BufferedImage img = null;
  try {
   img = ImageIO.read(new File("C://Users//Tahir//Desktop//cs102//proje//project//logo.png"));
  } catch (IOException e) {
   e.printStackTrace();
  }
  Image dimg = img.getScaledInstance(imgLabel.getWidth(), imgLabel.getHeight(),
          Image.SCALE_SMOOTH);

  ImageIcon imageIcon = new ImageIcon(dimg);
  imgLabel = new JLabel(imageIcon);


  buttonPanel.add(imgLabel);
  buttonPanel.add(addEvent);
  buttonPanel.add(scheduledMail);


  //Set border
  pnlCalendar.setBorder(BorderFactory.createTitledBorder("Calendar"));

  //Register action listeners
  buttonPrev.addActionListener(new buttonPrev_Action());
  addEvent.addActionListener(new addEvent_Action());
  scheduledMail.addActionListener(new scheduledmail_Action());
  buttonNext.addActionListener(new buttonNext_Action());
  cmbYear.addActionListener(new cmbYear_Action());
  scheduledMail.addActionListener(new scheduledmail_Action());
  //Add controls to pane
  pane.add(pnlCalendar);
  pnlCalendar.add(lblMonth);
  pnlCalendar.add(lblYear);
  pnlCalendar.add(cmbYear);
  pnlCalendar.add(buttonPrev);
  pnlCalendar.add(buttonNext);
  pnlCalendar.add(stableCalendar);

  //Set bounds


  pnlCalendar.setBounds(0, 0, screenSize.width*4/5, screenSize.height);
  lblMonth.setBounds(screenSize.width*2/5, screenSize.height/50, 100, 25);
  lblYear.setBounds(screenSize.width/50, screenSize.height*43/50, screenSize.width*4/50, screenSize.height*3/50);
  cmbYear.setBounds(screenSize.width*4/50, screenSize.height*87/100, screenSize.width*5/50, screenSize.height*2/50);
  buttonPrev.setBounds(screenSize.width/50, screenSize.height/50, screenSize.width/10, screenSize.height/30);
  buttonNext.setBounds(screenSize.width*34/50, screenSize.height/50, screenSize.width/10, screenSize.height/30);
  stableCalendar.setBounds(screenSize.width/100, screenSize.height/15, screenSize.width*39/50, screenSize.height);


  //Make frame visible
  frameMain.setResizable(false);
  frameMain.setVisible(true);
  frameMain.setLayout(new BorderLayout() );
  frameMain.add(buttonPanel, BorderLayout.EAST);

  //Get real month/year
  GregorianCalendar cal = new GregorianCalendar(); //Create calendar
  realDay = cal.get(GregorianCalendar.DAY_OF_MONTH); //Get day
  realMonth = cal.get(GregorianCalendar.MONTH); //Get month
  realYear = cal.get(GregorianCalendar.YEAR); //Get year
  currentMonth = realMonth; //Match month and year
  currentYear = realYear;

  //Add headers
  String[] headers = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"}; //All headers
  for (int i=0; i<7; i++){
   mtableCalendar.addColumn(headers[i]);
  }

  tableCalendar.getParent().setBackground(tableCalendar.getBackground()); //Set background

  //No resize/reorder
  tableCalendar.getTableHeader().setResizingAllowed(false);
  tableCalendar.getTableHeader().setReorderingAllowed(false);

  //Single cell selection
  tableCalendar.setColumnSelectionAllowed(true);
  tableCalendar.setRowSelectionAllowed(true);
  tableCalendar.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

  //Set row/column count
  tableCalendar.setRowHeight(screenSize.height/8);
  mtableCalendar.setColumnCount(7);
  mtableCalendar.setRowCount(6);

  //Populate table
  for (int i=realYear-100; i<=realYear+100; i++){
   cmbYear.addItem(String.valueOf(i));
  }

  //Refresh calendar
  refreshCalendar (realMonth, realYear); //Refresh calendar
 }

 public static void refreshCalendar(int month, int year){

  //Variables
  Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
  String[] months =  {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
  int nod, som; //Number Of Days, Start Of Month

  //Allow/disallow buttons
  buttonPrev.setEnabled(true);
  buttonNext.setEnabled(true);
  if (month == 0 && year <= realYear-10){buttonPrev.setEnabled(false);} //Too early
  if (month == 11 && year >= realYear+100){buttonNext.setEnabled(false);} //Too late
  lblMonth.setText(months[month]); //Refresh the month label (at the top)
  lblMonth.setBounds(screenSize.width*2/5, screenSize.height/50, 180, 25); //Re-align label with calendar
  cmbYear.setSelectedItem(String.valueOf(year)); //Select the correct year in the combo box
  stableCalendar.setBounds(screenSize.width/100, screenSize.height/15, screenSize.width*39/50, screenSize.height);
  //Clear table
  for (int i=0; i<6; i++){
   for (int j=0; j<7; j++){
    mtableCalendar.setValueAt(null, i, j);

   }
  }

  //Get first day of month and number of days
  GregorianCalendar cal = new GregorianCalendar(year, month, 1);
  nod = cal.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);
  som = cal.get(GregorianCalendar.DAY_OF_WEEK);

  //Draw calendar
  for (int i=1; i<=nod; i++){
   int row = ((i+som-2)/7);
   int column  =  (i+som-2)%7;
   mtableCalendar.setValueAt(i, row, column);
   

  }

  //Apply renderers
  tableCalendar.setDefaultRenderer(tableCalendar.getColumnClass(0), new tableCalendarRenderer());
 }
 static class tableCalendarRenderer extends DefaultTableCellRenderer{
  public Component getTableCellRendererComponent (JTable table, Object value, boolean selected, boolean focused, int row, int column){
   super.getTableCellRendererComponent(table, value, selected, focused, row, column);
  
   if (column == 0 || column == 6){ //Week-end
    setBackground(new Color(255, 220, 220));
   }
   else{ //Week
    setBackground(new Color(255, 255, 255));
   }
   if (value != null){
    if (Integer.parseInt(value.toString()) == realDay && currentMonth == realMonth && currentYear == realYear){ //Today
     setBackground(new Color(220, 20, 255));
 
    }

   }
   setBorder(null);
   setForeground(Color.black);
   return this;
  }
 }

 static class buttonPrev_Action implements ActionListener{
  public void actionPerformed (ActionEvent e){
   if (currentMonth == 0){ //Back one year
    currentMonth = 11;
    currentYear -= 1;
   }
   else{ //Back one month
    currentMonth -= 1;
   }
   refreshCalendar(currentMonth, currentYear);
  }
 }
  static class addEvent_Action implements ActionListener{
  public void actionPerformed (ActionEvent e){


   addEvent a = new addEvent();
   a.createAndShowGUI();
  }
 }
 static class scheduledmail_Action implements ActionListener{
  public void actionPerformed (ActionEvent e){

   MailGUI aaa = new MailGUI();

  }
 }
 static class buttonNext_Action implements ActionListener{
  public void actionPerformed (ActionEvent e){
   if (currentMonth == 11){ //Foward one year
    currentMonth = 0;
    currentYear += 1;
   }
   else{ //Foward one month
    currentMonth += 1;
   }
   refreshCalendar(currentMonth, currentYear);
  }
 }
 static class cmbYear_Action implements ActionListener{
  public void actionPerformed (ActionEvent e){
   if (cmbYear.getSelectedItem() != null){
    String b = cmbYear.getSelectedItem().toString();
    currentYear = Integer.parseInt(b);
    refreshCalendar(currentMonth, currentYear);
   }
  }
 }
}