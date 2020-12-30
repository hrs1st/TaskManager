package storage ;

import java.io.Serializable;
import java.util.Date;

public class TimedTask extends NormalTask implements Serializable {

   private Date taskDate ;

   public TimedTask( String title, String description, Date taskDate ) {
       super(title,description);
       this.taskDate = taskDate ;
   }

   public void setTaskDate(Date taskDate) {
       this.taskDate = taskDate ;
   }

   public Date getTaskDate() {
       return taskDate ;
   }

    @Override
    public String toString() {
        return "Task Type : " + "TimedTask" + '\n'
                + " Information : {" + '\n'
                + "Task Title : " + getTitle() + '\n'
                + "Task Description : " + getDescription() + '\n'
                + "Deadline : " + taskDate + '\n'
                + "Done Status : " + getDoneStatus() + " }" ;
    }

}
