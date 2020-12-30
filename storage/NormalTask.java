package storage ;

import java.io.Serializable;

public class NormalTask implements Serializable {

    private String title ;
    private String description ;
    private boolean doneStatus = false ;

    public NormalTask( String title, String description ) {
        this.title = title ;
        this.description = description ;
    }

    public void setTitle(String title) {
        this.title = title ;
    }

    public void setDescription(String description) {
        this.description = description ;
    }

    public String getTitle() {
        return title ;
    }

    public String getDescription() {
        return description ;
    }

    public void done() {
        doneStatus = true ;
    }

    public boolean getDoneStatus() {
        return doneStatus ;
    }

    @Override
    public String toString() {
        return "Task Type : " + "NormalTask" + '\n'
                + " Information : {" + '\n'
                + "Task Title : " + title + '\n'
                + "Task Description : " + description + '\n'
                + "Done Status : " + doneStatus + " }" ;
    }

}
