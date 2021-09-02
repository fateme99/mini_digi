import Controller.Controller;
import Controller.ManagerController;
import View.Menus.CreatAccountMenu;
import View.Menus.MainMenu;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        Controller controller = new Controller();
        startProgram();
        new MainMenu( controller ).run();
    }

    public static void startProgram(){
        if ( !new File("database.ifs").exists() ){
            new CreatAccountMenu( null ).creatAdmin();
        }
        else{
            Controller.initializer();
        }
    }
}
