import io.FileManager;
import io.User;

import java.io.Serializable;

public class Main implements Serializable {

    public static void main(String[] args) {

        TaskManager taskManager = new TaskManager();
        User user = new User();
        FileManager fileManager = new FileManager(user);
        UserInterface userInterface = new UserInterface(taskManager,fileManager);

        userInterface.start();

    }
}
