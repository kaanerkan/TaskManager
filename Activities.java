import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.text.*;
import java.io.*;

class Activities<a> implements Comparator<Activity> { //start of class


  java.util.List<Activity> priority = new ArrayList<Activity>();
  java.util.List<String> datas = new ArrayList<String>();



  public int compare(Activity one, Activity two) {
    int pc1 = one.getPriorityCode();
    int pc2 = two.getPriorityCode();
    if(pc1 < pc2) return -1;
    if(pc2 < pc1) return 1;
    return one.getActivityDueDate().getTime().compareTo(two.getActivityDueDate().getTime());
  }

  public void add(Activity hello) {
    priority.add(hello);
    Collections.sort(priority, new Activities());
    saveCurrent();
  }

  public void delete(Activity goodbye) {
    priority.remove(goodbye);
    saveCurrent();
  }

  public int index(Activity idx) {
    return priority.indexOf(idx);
  }

  public Activity sendToEdit(int index) {
    Activity editThis = priority.get(index);
    return editThis;
  }

  public void sendToDelete(int index) {
    delete(priority.get(index));
  }

  public void loadCurrent() {
    Activity activityRestore = new Activity();
    try {

      login aaa = new login();

      ObjectInputStream is = new ObjectInputStream(new FileInputStream("currentActivities - " + String.valueOf(login.index) + ".ser"));
      if (is == null) {
        //do nothing
      } else {
        try {
          while (is != null) {
            activityRestore = (Activity) is.readObject();
            priority.add(activityRestore);
          }
        } catch (EOFException ex) {

        }
      }
    } catch (Exception ex) {
    
    }
  }

  public void saveCurrent() {
    try {

      ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("currentActivities - " + String.valueOf(login.index) + ".ser"));
      if (priority.isEmpty()) {
        JOptionPane.showMessageDialog(null,"There are no activities listed and therefore nothing will be saved.");
        os.writeObject(priority);
        os.close();
      } else {
        for (Activity saveActivities : priority) {
          os.writeObject(saveActivities);
        }
        os.close();
      }
    } catch (IOException ex) {
    
    }
  }

  public void saveCompleted() {
    //
  }

  public void saveDeleted() {
    //
  }

}