package storage ;

import java.io.Serializable;
import java.util.Date;

public class CheckListItem implements Serializable {

    private String title ;
    private boolean doneStatus = false ;

    public CheckListItem( String title ) {
        this.title = title ;
    }

    public void setTitle(String title) {
        this.title = title ;
    }

    public String getTitle() {
        return title ;
    }

    public boolean getDoneStatus() {
        return doneStatus ;
    }

    public void done() {
        doneStatus = true ;
    }

    @Override
    public String toString() {
        return "\nCheckListItem : {" + '\n'
                + "Name : " + title + '\n'
                + "DoneStatus : " + doneStatus
                + " }\n";
    }
}
