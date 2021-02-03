import java.util.*;
import java.io.*;

class Activity implements Serializable { //start of class

   private String activityName;
   private String activityDescription;
   private boolean activityImportance;
   private boolean activityUrgency;
   private int priorityCode;
   private Calendar activityDueDate;
   private Date activityDateValue;
   private Date activityTimeValue;
   private long activityID;

   String getActivityName() {
      return activityName;
   }

   void setActivityName(String anActivityName) {
      activityName = anActivityName;
   }

   String getActivityDescription() {
      return activityDescription;
   }

   void setActivityDescription(String anActivityDescription) {
      activityDescription = anActivityDescription;
   }

   boolean getActivityImportance() {
      return activityImportance;
   }

   void setActivityImportance(boolean anActivityImportance) {
      activityImportance = anActivityImportance;
   }

   boolean getActivityUrgency() {
      return activityUrgency;
   }

   void setActivityUrgency(boolean anActivityUrgency) {
      activityUrgency = anActivityUrgency;
   }

   int getPriorityCode() {
      return priorityCode;
   }

   void setPriorityCode(boolean anActivityImportance, boolean anActivityUrgency) {
      //code for determining priority code
      priorityCode = 0; // make sure it always starts off at zero
      // first check importance
      if (anActivityImportance) {
         priorityCode += 3;
      } else {
         priorityCode += 5;
      }
      // next check urgency before deciding on what to return
      if (anActivityUrgency) {
         //do nothing
      } else {
         priorityCode += 1;
      }
      // this way there is a 3 returned for important and urgent
      // there is a 4 returned for important but not urgent
      // there is a 5 returned for not important but urgent
      // there is a 6 returned for not important and not urgent
   }

   Calendar getActivityDueDate() {
      return activityDueDate;
   }

   void setActivityDueDate(Calendar anActivityDueDate) {
      activityDueDate = anActivityDueDate;
   }

   Date getActivityDateValue() {
      return activityDateValue;
   }

   void setActivityDateValue(Date anActivityDateValue) {
      activityDateValue = anActivityDateValue;
   }

   Date getActivityTimeValue() {
      return activityTimeValue;
   }

   void setActivityTimeValue(Date anActivityTimeValue) {
      activityTimeValue = anActivityTimeValue;
   }

   long getActivityID() {
      return activityID;
   }

   void setActivityID(long anActivityID) {
      activityID = anActivityID;
   }
}