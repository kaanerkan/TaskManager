import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.text.*;
import java.io.*;

public class addEvent {

   final JTabbedPane optionsPane = new JTabbedPane();
   final JTabbedPane invisible = new JTabbedPane();

   final Activities test = new Activities();
   final TabView tabView = new TabView();
   final FieldView newView = new FieldView();
   final FieldView editView = new FieldView();

   int editing = -1;

   public void addComponentToPane(Container pane) {

      test.loadCurrent();
      int index = 0;
      Activity activityRestore = new Activity();
      try {
         ObjectInputStream is = new ObjectInputStream(new FileInputStream("currentActivities - " + String.valueOf(login.index) + ".ser"));
         if (is == null) {
            //do nothing
         } else {
            try {
               while (is != null) {
                  activityRestore = (Activity) is.readObject();
                  tabView.addActivityTab(activityRestore,index);
                  activateTabViewListeners();
                  index ++;
               }
            } catch (EOFException ex) {
            
            }
         }
      } catch (Exception ex) {
      
      }
      newView.setupPanel();
      newView.info();
      editView.setupPanel();
      editView.info();

      newView.buttonOneAddActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent ae1) {
            if (newView.fieldsCheck()) {
               Activity current = new Activity();
               current = newView.getActivityInfo();
               test.add(current);
               tabView.addActivityTab(current, test.index(current));
               newView.clearFields();
               activateTabViewListeners();
            }
         }
      });

      newView.buttonTwoAddActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent ae2) {
            newView.clearFields();
         }
      });

      editView.buttonOneAddActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent ae1) {
            if (editView.fieldsCheck()) {
               Activity current = new Activity();
               current = editView.getActivityInfo();
               tabView.remove(editing);
               test.sendToDelete(editing);
               editing = -1;
               test.add(current);
               tabView.addActivityTab(current, test.index(current));
               editView.clearFields();
               activateTabViewListeners();
               optionsPane.setEnabledAt(0,true);
               optionsPane.setEnabledAt(1,true);
               optionsPane.setSelectedIndex(0);
               optionsPane.setEnabledAt(2,false);
            }
         }
      }
      )
      ;

      editView.buttonTwoAddActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent ae2) {
            editView.clearFields();
            optionsPane.setEnabledAt(0,true);
            optionsPane.setEnabledAt(1,true);
            optionsPane.setSelectedIndex(0);
            optionsPane.setEnabledAt(2,false);
         }
      });

      optionsPane.addTab("List of Current Activities", tabView);
      optionsPane.addTab("Enter Activity Info", newView.getPanel());
      optionsPane.addTab("Edit Activity", editView.getPanel());
      optionsPane.setEnabledAt(2,false);
      pane.add(optionsPane, BorderLayout.CENTER);
   }

   public static void createAndShowGUI() {
      //Create and set up the window.
      JFrame frame = new JFrame("PlannerAppDemo");
      frame.setPreferredSize(new Dimension(500,500));
     // frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      //Create and set up the content pane
      PlannerAppDemo demo = new PlannerAppDemo();
      demo.addComponentToPane(frame.getContentPane());

      //Display the window.
      frame.pack();
      frame.setVisible(true);
   }

   public void activateTabViewListeners() {
      tabView.completeButtonAddActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent aec) {
            int idx = tabView.getSelectedIndex();
            tabView.remove(idx);
            test.sendToDelete(idx);
         }
      });

      tabView.deleteButtonAddActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent aed) {
            int c = JOptionPane.showConfirmDialog(null,
                    "Are you sure you want to delete this activity?",
                    "Confirm Delete",
                    JOptionPane.YES_NO_OPTION);
            if (c == JOptionPane.YES_OPTION) {
               int idx = tabView.getSelectedIndex();
               tabView.remove(idx);
               test.sendToDelete(idx);
            }
         }
      });

      tabView.editButtonAddActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent aee) {
            int idx = tabView.getSelectedIndex();
            editing = idx;
            editView.setActivityInfo(test.sendToEdit(idx));
            optionsPane.setEnabledAt(1,false);
            optionsPane.setEnabledAt(2,true);
            optionsPane.setSelectedIndex(2);
            optionsPane.setEnabledAt(0,false);
         }
      });
   }

   public static void main(String[] args) {
      try {

         UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
      } catch (UnsupportedLookAndFeelException ex) {
         ex.printStackTrace();
      } catch (IllegalAccessException ex) {
         ex.printStackTrace();
      } catch (InstantiationException ex) {
         ex.printStackTrace();
      } catch (ClassNotFoundException ex) {
         ex.printStackTrace();
      }

      UIManager.put("swing.boldMetal", Boolean.FALSE);

      javax.swing.SwingUtilities.invokeLater(new Runnable() {
         public void run() {
            createAndShowGUI();
         }
      });
   }
}