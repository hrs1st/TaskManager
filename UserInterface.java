import io.FileManager;
import io.User;
import io.UsersFile;
import io.UsersList;
import storage.CheckList;
import storage.CheckListItem;
import storage.NormalTask;
import storage.TimedTask;

import java.io.FileNotFoundException;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;

public class UserInterface implements Serializable {

    private final int VIEW_TASKS = 1 ;
    private final int ADD_TASK = 2 ;
    private final int EXIT = 0 ;

    private final int ADD_NORMAL = 1 ;
    private final int ADD_TIMED = 2 ;
    private final int ADD_CHECKLIST = 3 ;
    private final int MAINMENU = 0 ;

    private final int NORMAL_CHECKLIST = 1 ;
    private final int TIMED_CHECKLIST = 2 ;

    private final int EDIT_NORMAL = 1 ;
    private final int DELETE_TASK = 2 ;
    private final int DONE_TASK = 3 ;
    private final int EDIT_TIMED = 1 ;

    private final int EDIT_CHECKLIST = 1 ;
    private final int OPEN_CHECKLIST = 4 ;
    private final int ADD_TO_CHECK = 5 ;
    private final int DONE_ITEM = 2 ;
    private final int DELETE_ITEM = 3 ;

    private final int LOG_IN = 1 ;
    private final int CREATE_ACCOUNT = 2 ;
    private final int ACCOUNT_MENU = 3 ;
    private final int SAVE = 1 ;
    private final int EDIT_ACCOUNT = 2 ;



    private TaskManager taskManager ;
    private FileManager fileManager ;
    private Scanner scanner ;
    private OutputPrints outputPrints ;
    private UsersList usersList ;

    UserInterface(TaskManager taskManager , FileManager fileManager) {
        this.taskManager = taskManager ;
        this.fileManager = fileManager ;
        this.usersList = new UsersList();
        this.outputPrints = new OutputPrints(taskManager);
        scanner = new Scanner(System.in).useDelimiter("\n");
    }

    public void start() {
        outputPrints.printStartMenu();

        int input = scanner.nextInt();

        switch (input) {
            case LOG_IN:
                logIn();
                break;

            case CREATE_ACCOUNT :
                createAccount();
                break;

            case EXIT :
                System.out.println("U Exited, Goodbye!");
                System.exit(0);
        }
    }

    public void logIn() {
        System.out.println("Enter your UserName :");
        String inputUserName = scanner.next();

        System.out.println("Enter your Password :");
        String inputPassword = scanner.next();

        //load
        if (checkPassword(inputUserName,inputPassword)) {
            taskManager.tasks = fileManager.readFromFile();
        }

        mainMenu();
    }

    public boolean checkPassword(String inputUserName, String inputPassword) {
        boolean check = false ;

        fileManager.getUser().setUsername(inputUserName);
        usersList.addToList(fileManager.getUser());

        usersList.setUserslist(UsersFile.readFromUsersFile());
        User specificUser = usersList.getSpecificUser(inputUserName);
        if(inputPassword.equals(specificUser.getPassword())) {
            check = true ;
        }
        return check ;
    }

    public void createAccount() {
        System.out.println("set a UserName :");

        String inputUserName = scanner.next();
        if(!inputUserName.isEmpty()) {
        fileManager.getUser().setUsername(inputUserName);
        }

        System.out.println("set a Password :");
        String inputPassword = scanner.next();
        if(!inputPassword.isEmpty()) {
            fileManager.getUser().setPassword(inputPassword);
        }
        usersList.addToList(fileManager.getUser());

        mainMenu();
    }

    public void mainMenu() {
        outputPrints.printMainMenu();

        int input = scanner.nextInt();

        switch (input) {
            case VIEW_TASKS :
                openAllTasks();
                break;

            case ADD_TASK :
                addTask();
                break;

            case ACCOUNT_MENU :
                accountOptions();
                break;

            case EXIT :
                System.out.println("U Exited, Goodbye!");
                System.exit(0);
        }
    }

    public void accountOptions() {
        outputPrints.printAccountMenu();

        int input = scanner.nextInt();

        switch (input) {
            case SAVE :
                fileManager.writeToFile(taskManager.tasks);
                UsersFile.writeToUsersFile(usersList.getUserslist());
                mainMenu();
                break;

            case EDIT_ACCOUNT :
                createAccount();
                break;

            case MAINMENU :
                mainMenu();
                break;
        }
    }

    private void openAllTasks() {
        outputPrints.printTasks();

        int input = scanner.nextInt();
        if(input == 0) {mainMenu();}
        else {
            for (NormalTask task : taskManager.tasks) {
                if (input-1 == taskManager.tasks.indexOf(task)) {
                    if (task instanceof CheckList) {
                        checkListOptions((CheckList) task);
                    } else if (task instanceof TimedTask) {
                        timedTaskOptions((TimedTask) task);
                    } else {
                        normalTaskOptions(task);
                    }
                }
            }
        }
    }

    private void addTask() {
        outputPrints.printAddTaskMenu();

        int input = scanner.nextInt();

        switch (input) {
            case ADD_NORMAL :
                addNormalTask();
                break;

            case ADD_TIMED :
                addTimedTask();
                break;

            case ADD_CHECKLIST :
                checkListAddChoose();
                break;

            case MAINMENU :
                mainMenu();
                break;
        }
    }

    private void addNormalTask() {
        String title = inputTitle() ;
        String description = inputDescription() ;

        NormalTask task = new NormalTask(title,description);
        taskManager.tasks.add(task);

        mainMenu();
    }

    private void addTimedTask() {
        String title = inputTitle();
        String description = inputDescription();
        Date date = inputDate();

        TimedTask task = new TimedTask(title,description,date);
        taskManager.tasks.add(task);

        mainMenu();
    }

    private void checkListAddChoose() {
        outputPrints.printAddChecklistMenu();

        int input = scanner.nextInt();
        switch (input) {
            case NORMAL_CHECKLIST :
                addChecklist();
                break;

            case TIMED_CHECKLIST :
                addTimedCheckList();
                break;

            case MAINMENU :
                mainMenu();
                break;
        }
    }

    private void addChecklist() {
        String title = inputTitle();
        String description = inputDescription();

        CheckList checkList = new CheckList(title,description);
        taskManager.tasks.add(checkList);

        mainMenu();
    }

    private void addTimedCheckList() {
        String title = inputTitle();
        String description = inputDescription();
        Date deadline = inputDate();

        CheckList checkList = new CheckList(title,description,deadline);
        taskManager.tasks.add(checkList);

        mainMenu();
    }

    private void createCheckListItem(CheckList checkList) {
        String name = inputTitle();
        //CheckListItem item = new CheckListItem(name);
        checkList.addItem(name);

        mainMenu();
    }

    private void deleteTask(NormalTask task) {
        taskManager.removeTask(task.getTitle());

        mainMenu();
    }

    private void normalTaskOptions(NormalTask task) {
        outputPrints.printTaskOptionsMenu(task);

        int input = scanner.nextInt();
        switch (input) {
            case EDIT_NORMAL :
                editNormalTask(task);
                break;

            case DELETE_TASK :
                deleteTask(task);
                break;

            case DONE_TASK :
                task.done();
                break;

            case MAINMENU :
                mainMenu();
                break;
        }
    }

    private void timedTaskOptions(TimedTask task) {
        outputPrints.printTaskOptionsMenu(task);

        int input = scanner.nextInt();
        switch (input) {
            case EDIT_TIMED :
                editTimedTask(task);
                break;

            case DELETE_TASK :
                deleteTask(task);
                break;

            case DONE_TASK :
                task.done();
                break;

            case MAINMENU :
                mainMenu();
                break;
        }
    }

    private void checkListOptions(CheckList checkList) {
        outputPrints.printCheckListOptionMenu(checkList);

        int input = scanner.nextInt();

        switch (input) {

            case EDIT_CHECKLIST :
                editCheckListDetails(checkList);
                break;

            case DELETE_TASK :
                deleteTask(checkList);
                break;

            case DONE_TASK :
                checkList.done();
                break;

            case OPEN_CHECKLIST :
                openCheckList(checkList);
                break;

            case ADD_TO_CHECK :
                createCheckListItem(checkList);
                break;

            case MAINMENU :
                mainMenu();
                break;
        }
    }

    private void editNormalTask(NormalTask task) {
        String title = inputTitle();
        if(!title.isEmpty()){
            task.setTitle(title);
        }

        String description = inputDescription();
        if(!description.isEmpty()){
            task.setDescription(description);
        }

        mainMenu();
    }

    private void editTimedTask(TimedTask task) {
        String title = inputTitle();
        if(!title.isEmpty()){
            task.setTitle(title);
        }

        String description = inputDescription();
        if(!description.isEmpty()){
            task.setDescription(description);
        }

        Date date = inputDate();
        if(date != null) {
            task.setTaskDate(date);
        }

        mainMenu();
    }

    private void editCheckListDetails(CheckList checkList) {
        String title = inputTitle();
        if(!title.isEmpty()){
            checkList.setTitle(title);
        }

        String description = inputDescription();
        if(!description.isEmpty()){
            checkList.setDescription(description);
        }

        if(checkList.getDeadline()!=null) {
            Date date = inputDate();
            if(date != null) {
                checkList.setDeadline(date);
            }
        }

        mainMenu();
    }

    private void openCheckList(CheckList checkList) {
        outputPrints.printCheckListItems(checkList);

        int input = scanner.nextInt();
        if(input==0) {mainMenu();}
        else {
            for (CheckListItem item : checkList.getChecklist()) {
                if (input - 1 == checkList.getChecklist().indexOf(item)) {
                    checkListItemOptions(item, checkList);
                }
            }
        }
    }

    private void checkListItemOptions(CheckListItem item , CheckList checkList) {
        outputPrints.printCheckListItemMenu(item);

        int input = scanner.nextInt();

        switch (input) {
            case EDIT_CHECKLIST :
                editCheckListItemTitle(item);
                break;

            case DONE_ITEM :
                item.done();
                break;

            case DELETE_ITEM :
                checkList.removeItem(item.getTitle());
                break;

            case MAINMENU :
                mainMenu();
                break;
        }
    }

    private void editCheckListItemTitle(CheckListItem item) {
        String title = inputTitle();
        if(!title.isEmpty()){
            item.setTitle(title);
        }

        mainMenu();
    }

    private String inputTitle() {
        System.out.println("Enter a title for the task :");
        return scanner.next();
    }

    private String inputDescription() {
        System.out.println("Enter a description for the task :");
        return scanner.next();
    }

    private Date inputDate() {
        System.out.println("Enter a date for the task in format YYYY-MM-DD HH:mm :");

        String formattedDate = scanner.next();
        String formattedTime = scanner.next();
        Date date = null ;
        try {
            date = new SimpleDateFormat("YYYY-MM-DD HH:mm").parse(formattedDate + " " + formattedTime);
        }catch (ParseException e) {e.printStackTrace();}

        return date ;
    }

}
