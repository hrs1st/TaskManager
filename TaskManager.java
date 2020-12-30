import storage.CheckList;
import storage.NormalTask;
import storage.TimedTask;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class TaskManager implements Serializable {

    public TaskManager(){};

    ArrayList<NormalTask> tasks = new ArrayList<>();

    public NormalTask createNormalTask( String title, String description ) {
        return new NormalTask(title, description);
    }

    public TimedTask createTimedTask(String title, String description, Date taskDate) {
        return new TimedTask(title,description,taskDate);
    }

    public CheckList createCheckList(String title, String description) {
        return new CheckList(title,description);
    }

    public void addTask(NormalTask task) {
        tasks.add(task);
    }

    public void removeTask(int index) {
        tasks.remove(index);
    }

    public void removeTask(String title) {
        for(NormalTask task : tasks) {
            if(task.getTitle().equals(title)) {
                tasks.remove(task);
            }
        }
    }

    public <X extends NormalTask> X getTask(int index) {
        return (X) tasks.get(index) ;
    }

    public <X extends NormalTask> X getTask(String title) {
        int index = 0 ;
        for(NormalTask task : tasks) {
            if (task.getTitle().equals(title)) {
                return (X) task ;
            }
        }
        return null;
    }

    public ArrayList<NormalTask> getTasks() {
        return tasks ;
    }

    @Override
    public String toString() {
        return "Tasks list :\n"
                + tasks ;
    }

}
