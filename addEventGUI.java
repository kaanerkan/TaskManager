import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.text.*;
import java.io.*;

class addEventGUI extends JPanel {

   JPanel createPanel = new JPanel(new GridBagLayout()); //goes in the 'Create New Activity' tab under the optionsPane

   JPanel topPanel;
   JPanel middlePanel;
   JPanel bottomPanel;

   JLabel titleLabel;
   JLabel descriptionLabel;
   JLabel importantLabel;
   JLabel urgentLabel;
   JLabel dueDateLabel;
   JLabel timeLabel;

   JTextField titleText;
   JTextField descriptionText;
   JRadioButton radioYesImportant;
   JRadioButton radioNoImportant;
   JRadioButton radioImportant;
   JRadioButton radioYesUrgent;
   JRadioButton radioNoUrgent;
   JRadioButton radioUrgent;
   JSpinner dateSpinner;
   JSpinner timeSpinner;
   ButtonGroup buttonGroupA;
   ButtonGroup buttonGroupB;

   Date dateValue;
   Date timeValue;

   private JButton buttonOne;
   private JButton buttonTwo;

   void setupPanel() {
      createPanel.setBackground(Color.blue);

      //stuff that goes in the top panel of 'Create New Activity' tab
      topPanel = new JPanel(new GridBagLayout());
      GridBagConstraints a = new GridBagConstraints();
      topPanel.setBackground(Color.cyan);
      a.gridx = 0;
      a.gridy = 0;
      a.gridwidth = 3;
      a.gridheight = 3;
      a.weightx = 1;
      a.weighty = 1;
      a.fill = GridBagConstraints.BOTH;
      a.anchor = GridBagConstraints.FIRST_LINE_START;
      createPanel.add(topPanel,a);

      titleLabel = new JLabel("Title:");
      GridBagConstraints a00 = new GridBagConstraints();
      a00.gridx = 0;
      a00.gridy = 0;
      a00.weightx = 1;
      a00.weighty = 1;
      a00.fill = GridBagConstraints.BOTH;
      a00.anchor = GridBagConstraints.FIRST_LINE_START;
      topPanel.add(titleLabel, a00);

      descriptionLabel = new JLabel("Description:");
      GridBagConstraints a01 = new GridBagConstraints();
      a01.gridx = 0;
      a01.gridy = 1;
      a01.weightx = 1;
      a01.weighty = 1;
      a01.fill = GridBagConstraints.BOTH;
      a01.anchor = GridBagConstraints.LINE_START;
      topPanel.add(descriptionLabel, a01);

      importantLabel = new JLabel("Important:");
      GridBagConstraints a02 = new GridBagConstraints();
      a02.gridx = 0;
      a02.gridy = 2;
      a02.weightx = 1;
      a02.weighty = 1;
      a02.fill = GridBagConstraints.BOTH;
      a02.anchor = GridBagConstraints.LAST_LINE_START;
      topPanel.add(importantLabel, a02);

      titleText = new JTextField();
      titleText.setColumns(10);
      GridBagConstraints a10 = new GridBagConstraints();
      a10.gridx = 1;
      a10.gridy = 0;
      a10.gridwidth = 2;
      a10.weightx = 1;
      a10.weighty = 1;
      a10.fill = GridBagConstraints.BOTH;
      a10.anchor = GridBagConstraints.PAGE_START;
      topPanel.add(titleText, a10);
      descriptionText = new JTextField();
      descriptionText.setColumns(10);
      GridBagConstraints a11 = new GridBagConstraints();
      a11.gridx = 1;
      a11.gridy = 1;
      a11.gridwidth = 2;
      a11.weightx = 1;
      a11.weighty = 1;
      a11.fill = GridBagConstraints.BOTH;
      a11.anchor = GridBagConstraints.CENTER;
      topPanel.add(descriptionText, a11);

      buttonGroupA = new ButtonGroup();
      radioImportant = new JRadioButton();
      buttonGroupA.add(radioImportant);

      radioYesImportant = new JRadioButton("Yes");
      radioYesImportant.setBackground(Color.cyan);
      GridBagConstraints a12 = new GridBagConstraints();
      a12.gridx = 1;
      a12.gridy = 2;
      a12.gridwidth = 1;
      a12.weightx = 1;
      a12.weighty = 1;
      a12.fill = GridBagConstraints.BOTH;
      a12.anchor = GridBagConstraints.PAGE_END;
      buttonGroupA.add(radioYesImportant);
      topPanel.add(radioYesImportant, a12);
      radioNoImportant = new JRadioButton("No");
      radioNoImportant.setBackground(Color.cyan);
      GridBagConstraints a22 = new GridBagConstraints();
      a22.gridx = 2;
      a22.gridy = 2;
      a22.gridwidth = 1;
      a22.weightx = 1;
      a22.weighty = 1;
      a22.fill = GridBagConstraints.BOTH;
      a22.anchor = GridBagConstraints.LAST_LINE_END;
      buttonGroupA.add(radioNoImportant);
      topPanel.add(radioNoImportant, a22);

      titleLabel.setLabelFor(titleText);
      descriptionLabel.setLabelFor(descriptionText);
      importantLabel.setLabelFor(radioYesImportant);

      //stuff that goes in the middle panel of 'Create New Activity' tab
      middlePanel = new JPanel(new GridBagLayout());
      GridBagConstraints b = new GridBagConstraints();
      middlePanel.setBackground(Color.cyan);
      b.gridx = 0;
      b.gridy = 3;
      b.gridwidth = 3;
      b.gridheight = 3;
      b.weightx = 1;
      b.weighty = 1;
      b.fill = GridBagConstraints.BOTH;
      b.anchor = GridBagConstraints.FIRST_LINE_START;
      createPanel.add(middlePanel,b);

      urgentLabel = new JLabel("Urgent:");
      GridBagConstraints b00 = new GridBagConstraints();
      b00.gridx = 0;
      b00.gridy = 0;
      b00.weightx = 1;
      b00.weighty = 1;
      b00.fill = GridBagConstraints.BOTH;
      b00.anchor = GridBagConstraints.FIRST_LINE_START;
      middlePanel.add(urgentLabel, b00);

      dueDateLabel = new JLabel("Due Date:");
      GridBagConstraints b01 = new GridBagConstraints();
      b01.gridx = 0;
      b01.gridy = 1;
      b01.weightx = 1;
      b01.weighty = 1;
      b01.fill = GridBagConstraints.BOTH;
      b01.anchor = GridBagConstraints.LINE_START;
      middlePanel.add(dueDateLabel, b01);

      timeLabel = new JLabel("Complete By:");
      GridBagConstraints b02 = new GridBagConstraints();
      b02.gridx = 0;
      b02.gridy = 2;
      b02.weightx = 1;
      b02.weighty = 1;
      b02.fill = GridBagConstraints.BOTH;
      b02.anchor = GridBagConstraints.LAST_LINE_START;
      middlePanel.add(timeLabel, b02);

      buttonGroupB = new ButtonGroup();
      radioUrgent = new JRadioButton();
      buttonGroupB.add(radioUrgent);

      radioYesUrgent = new JRadioButton("Yes");
      radioYesUrgent.setBackground(Color.cyan);
      GridBagConstraints b10 = new GridBagConstraints();
      b10.gridx = 1;
      b10.gridy = 0;
      b10.gridwidth = 1;
      b10.weightx = 1;
      b10.weighty = 1;
      b10.fill = GridBagConstraints.BOTH;
      b10.anchor = GridBagConstraints.PAGE_START;
      buttonGroupB.add(radioYesUrgent);
      middlePanel.add(radioYesUrgent, b10);

      radioNoUrgent = new JRadioButton("No");
      radioNoUrgent.setBackground(Color.cyan);
      GridBagConstraints b20 = new GridBagConstraints();
      b20.gridx = 2;
      b20.gridy = 0;
      b20.gridwidth = 1;
      b20.weightx = 1;
      b20.weighty = 1;
      b20.fill = GridBagConstraints.BOTH;
      b20.anchor = GridBagConstraints.FIRST_LINE_END;
      buttonGroupB.add(radioNoUrgent);
      middlePanel.add(radioNoUrgent, b20);

      //Date and Time Spinners
      Calendar calendar = Calendar.getInstance();
      Date initialDate = calendar.getTime();
      calendar.add(Calendar.YEAR, -100);
      Date earliestDate = calendar.getTime();
      calendar.add(Calendar.YEAR, 200);
      Date latestDate = calendar.getTime();
      SpinnerDateModel dateModel = new SpinnerDateModel(initialDate, //initial date
              earliestDate, //minimum date
              latestDate,  //maximum date
              Calendar.YEAR);
      dateSpinner = new JSpinner(dateModel);
      dateSpinner.setEditor(new JSpinner.DateEditor(dateSpinner, "dd/MM/yyyy"));
      dateValue = (Date) dateSpinner.getValue();
      GridBagConstraints b11 = new GridBagConstraints();
      b11.gridx = 1;
      b11.gridy = 1;
      b11.gridwidth = 2;
      b11.weightx = 1;
      b11.weighty = 1;
      b11.fill = GridBagConstraints.BOTH;
      b11.anchor = GridBagConstraints.CENTER;
      middlePanel.add(dateSpinner, b11);

      calendar.add(Calendar.YEAR, -100);
      Date initialTime = calendar.getTime();
      calendar.add(Calendar.YEAR, -100);
      Date earliestTime = calendar.getTime();
      calendar.add(Calendar.YEAR, 200);
      Date latestTime = calendar.getTime();
      SpinnerDateModel timeModel = new SpinnerDateModel(initialTime, //initial date
              earliestTime, //minimum date
              latestTime,  //maximum date
              Calendar.YEAR);
      timeSpinner = new JSpinner(timeModel);
      timeSpinner.setEditor(new JSpinner.DateEditor(timeSpinner, "hh:mm aa"));
      timeValue = (Date) timeSpinner.getValue();
      GridBagConstraints b12 = new GridBagConstraints();
      b12.gridx = 1;
      b12.gridy = 2;
      b12.gridwidth = 2;
      b12.weightx = 1;
      b12.weighty = 1;
      b12.fill = GridBagConstraints.BOTH;
      b12.anchor = GridBagConstraints.PAGE_END;
      middlePanel.add(timeSpinner, b12);

      urgentLabel.setLabelFor(radioYesUrgent);
      dueDateLabel.setLabelFor(dateSpinner);
      timeLabel.setLabelFor(timeSpinner);

      //stuff that goes in the bottom panel of 'Create New Activity' tab
      bottomPanel = new JPanel(new GridBagLayout());
      GridBagConstraints c = new GridBagConstraints();
      bottomPanel.setBackground(Color.cyan);
      c.gridx = 0;
      c.gridy = 6;
      c.gridwidth = 3;
      c.gridheight = 3;
      c.weightx = 1;
      c.weighty = 1;
      c.fill = GridBagConstraints.BOTH;
      c.anchor = GridBagConstraints.FIRST_LINE_START;
      createPanel.add(bottomPanel,c);
   }

   void addButton(JButton buttonOne) {
      GridBagConstraints c01 = new GridBagConstraints();
      c01.gridx = 0;
      c01.gridy = 1;
      c01.gridwidth = 1;
      c01.gridheight = 2;
      c01.fill = GridBagConstraints.BOTH;
      c01.insets = new Insets(5,10,5,10);
      c01.anchor = GridBagConstraints.LINE_START;
      c01.weightx = 1;
      c01.weighty = 1;
      bottomPanel.add(buttonOne, c01);
   }

   void addSecondButton(JButton buttonTwo) {
      GridBagConstraints c11 = new GridBagConstraints();
      c11.gridx = 1;
      c11.gridy = 1;
      c11.gridwidth = 1;
      c11.gridheight = 2;
      c11.fill = GridBagConstraints.BOTH;
      c11.insets = new Insets(5,10,5,10);
      c11.anchor = GridBagConstraints.LINE_START;
      c11.weightx = 1;
      c11.weighty = 1;
      bottomPanel.add(buttonTwo, c11);
   }

   void info() {
      bottomPanel.removeAll();
      buttonOne = new JButton("Save");
      buttonTwo = new JButton("Cancel");
      addButton(buttonOne);
      addSecondButton(buttonTwo);
   }

   JPanel getPanel() {
      return createPanel;
   }

   void buttonOneAddActionListener(ActionListener al1) {
      buttonOne.addActionListener(al1);
   }

   void buttonTwoAddActionListener(ActionListener al2) {
      buttonTwo.addActionListener(al2);
   }

   Activity getActivityInfo() {
      Date dS = (Date) dateSpinner.getValue();
      Date tS= (Date) timeSpinner.getValue();
      Calendar dueDate = Calendar.getInstance();
      String dateLine = String.format("%1$td/%1$tm/%1$tY %2$tl:%2$tM %2$Tp",dS,tS);
      SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm aa");
      try {
         Date date = sdf.parse(dateLine);
         dueDate.setTime(date);
      } catch (ParseException dateE) {
        
      }

      //collect important info and make an activity object  
      Activity currentActivity = new Activity();
      currentActivity.setActivityName(titleText.getText());
      currentActivity.setActivityDescription(descriptionText.getText());
      currentActivity.setActivityImportance(radioYesImportant.isSelected());
      currentActivity.setActivityUrgency(radioYesUrgent.isSelected());
      currentActivity.setPriorityCode(radioYesImportant.isSelected(),radioYesUrgent.isSelected());
      currentActivity.setActivityDueDate(dueDate);
      currentActivity.setActivityDateValue((Date) dateSpinner.getValue());
      currentActivity.setActivityTimeValue((Date) timeSpinner.getValue());
      currentActivity.setActivityID(System.currentTimeMillis());
      return currentActivity;
   }



   void setActivityInfo(Activity e) {
      titleText.setText(e.getActivityName());
      descriptionText.setText(e.getActivityDescription());
      if (e.getActivityImportance()) {
         radioYesImportant.setSelected(true);
      } else {
         radioNoImportant.setSelected(true);
      }
      if (e.getActivityUrgency()) {
         radioYesUrgent.setSelected(true);
      } else {
         radioNoUrgent.setSelected(true);
      }
      dateSpinner.setValue(e.getActivityDateValue());
      timeSpinner.setValue(e.getActivityTimeValue());
   }

   boolean fieldsCheck() {
      boolean isReady = false;
      if ((titleText.getText() != null) && (radioYesImportant.isSelected() || radioNoImportant.isSelected()) && (radioYesUrgent.isSelected() || radioNoUrgent.isSelected())) {
         isReady = true;
      }
      return isReady;
   }

   void clearFields() {
      titleText.setText("");
      descriptionText.setText("");
      radioImportant.setSelected(true);
      radioUrgent.setSelected(true);
      dateSpinner.setValue(dateValue);
      timeSpinner.setValue(timeValue);
   }
}