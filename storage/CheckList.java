package storage ;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class CheckList extends NormalTask implements Serializable {

    private Date deadline = null ;

    public CheckList( String title, String description ) {
        super(title, description);
    }

    public CheckList( String title, String description, Date deadline )  {
        super(title, description);
        this.deadline = deadline ;
    }


    private ArrayList<CheckListItem> items = new ArrayList<>();

    public void addItem( String name ) {
        items.add(new CheckListItem(name)) ;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline ;
    }

    public void removeItem( String title ) {
        for( CheckListItem item : items ) {
            if (item.getTitle().equals(title)) {
                items.remove(item);
            }
        }
    }

    public void doneItem( String name ) {
        for( CheckListItem item : items ) {
            if (item.getTitle().equals(name)) {
                item.done();
            }
        }
    }

    public boolean getDoneStatus() {
        boolean doneCheck = false ;
        for( CheckListItem item : items) {
            if (!item.getDoneStatus()) {
                break;
            }
            else {
                doneCheck = true ;
            }
        }
        return doneCheck ;
    }

    public Date getDeadline() {
        return deadline ;
    }

    public ArrayList<CheckListItem> getChecklist() {
        return items ;
    }

    @Override
    public String toString() {
        return "Task Type : " + "CheckList" + '\n'
                + " Information : {" + '\n'
                + "Task Title : " + getTitle() + '\n'
                + "Task Description : " + getDescription() + '\n'
                + "Done Status : " + getDoneStatus() + '\n'
                + "CheckList : \n" + items + '\n';
    }

}
