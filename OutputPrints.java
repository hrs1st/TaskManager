import storage.CheckList;
import storage.CheckListItem;
import storage.NormalTask;

import java.io.Serializable;

public class OutputPrints implements Serializable {

    private TaskManager taskManager ;

    OutputPrints(TaskManager taskManager){
        this.taskManager = taskManager ;
    }

    void printMainMenu() {
        StringBuilder builder = new StringBuilder();

        builder.append("Here is your Taskmanager");
        builder.append("\n\n");

        builder.append("Enter \"1\" to view all tasks");
        builder.append('\n');

        builder.append("Enter \"2\" to add a new task");
        builder.append('\n');

        builder.append("Enter \"3\" to view your account menu");
        builder.append('\n');

        builder.append("Enter \"0\" to quit");

        System.out.println(builder.toString());
    }

    void printTasks()
    {
        StringBuilder builder = new StringBuilder();
        int index=1;

        for(NormalTask task : taskManager.tasks) {
            builder.append(index);
            builder.append(") ");
            builder.append(task);
            builder.append('\n');
            index++ ;
        }
        builder.append('\n');
        builder.append("Enter the index of a task to view its options");
        builder.append('\n');
        builder.append("Enter \"0\" to go to the main menu");

        System.out.println(builder.toString());
    }

    void printAddTaskMenu() {
        StringBuilder builder = new StringBuilder();

        builder.append("Enter \"1\" to add NormalTask");
        builder.append('\n');

        builder.append("Enter \"2\" to add TimedTask");
        builder.append('\n');

        builder.append("Enter \"3\" to add CheckList");
        builder.append('\n');

        builder.append("Enter \"0\" to go to the main menu");

        System.out.println(builder.toString());
    }

    void printAddChecklistMenu() {
        StringBuilder builder = new StringBuilder();

        builder.append("Enter \"1\" to add a normal CheckList");
        builder.append('\n');

        builder.append("Enter \"2\" to add timed CheckList");
        builder.append('\n');

        builder.append("Enter \"0\" to go to the main menu");

        System.out.println(builder.toString());
    }

    void printTaskOptionsMenu(NormalTask task) {
        StringBuilder builder = new StringBuilder();

        builder.append("Task Title : ").append(task.getTitle());
        builder.append("\n");
        builder.append("Task Description : ").append(task.getDescription());
        builder.append("\n\n");

        builder.append("Enter \"1\" to edit the task");
        builder.append('\n');

        builder.append("Enter \"2\" to delete the task");
        builder.append('\n');

        builder.append("Enter \"3\" to make the task done");
        builder.append('\n');

        builder.append("Enter \"0\" to go to the main menu");

        System.out.println(builder.toString());
    }

    void printCheckListOptionMenu(CheckList checkList) {
        StringBuilder builder = new StringBuilder();

        builder.append("CheckList Title : ").append(checkList.getTitle());
        builder.append("\n");
        builder.append("CheckList Description : ").append(checkList.getDescription());
        builder.append("\n\n");

        builder.append("Enter \"1\" to edit the details of CheckList");
        builder.append('\n');

        builder.append("Enter \"2\" to delete the CheckList");
        builder.append('\n');

        builder.append("Enter \"3\" to make the CheckList done");
        builder.append('\n');

        builder.append("Enter \"4\" to view items of the CheckList");
        builder.append('\n');

        builder.append("Enter \"5\" to add items to the CheckList");
        builder.append('\n');

        builder.append("Enter \"0\" to go to the main menu");

        System.out.println(builder.toString());
    }

    void printCheckListItems(CheckList checkList) {
        StringBuilder builder = new StringBuilder();

        int index = 1 ;
        for(CheckListItem item : checkList.getChecklist()) {
            builder.append(index);
            builder.append(") ");
            builder.append(item);
            builder.append('\n');
            index++;
        }
        builder.append('\n');
        builder.append("Enter the index of an item to view its options");
        builder.append('\n');
        builder.append("Enter \"0\" to go to the main menu");

        System.out.println(builder.toString());
    }

    void printCheckListItemMenu(CheckListItem item) {
        StringBuilder builder = new StringBuilder();

        builder.append("Item Title : ").append(item.getTitle());
        builder.append("\n\n");

        builder.append("Enter \"1\" to edit the title of item");
        builder.append('\n');

        builder.append("Enter \"2\" to make the item done");
        builder.append('\n');

        builder.append("Enter \"3\" to remove item from the CheckList");
        builder.append('\n');

        builder.append("Enter \"0\" to go to the main menu");

        System.out.println(builder.toString());
    }

    void printStartMenu() {
        StringBuilder builder = new StringBuilder();

        builder.append("Enter \"1\" to log in");
        builder.append('\n');

        builder.append("Enter \"2\" to crate a new account");
        builder.append('\n');

        builder.append("Enter \"0\" to quit");

        System.out.println(builder.toString());
    }

    void printAccountMenu() {
            StringBuilder builder = new StringBuilder();

            builder.append("Enter \"1\" to save the changes");
            builder.append('\n');

            builder.append("Enter \"2\" to edit your account");
            builder.append('\n');

            builder.append("Enter \"0\" to go to the main menu");
            builder.append('\n');

            System.out.println(builder.toString());
    }

}