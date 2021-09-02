package View.GUIMenu;

import Controller.Controller;
import Model.Account.Account;
import Model.Account.AccountInformation;
import Model.Account.Buyer;
import Model.Account.Seller;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.w3c.dom.Text;

public class AccountMenuGUI {

    private HBox topMenu;
    private VBox mainMenu;
    private Button back, logOut, showPersonalInfo;
    private Account account;
    private Stage window;
    private Scene scene;

    public AccountMenuGUI( Account account) {
        window = new Stage();

        topMenu = new HBox();
        topMenu.setPadding(new Insets(20, 20, 20, 20));
        topMenu.setSpacing(10);

        back = new Button("Back to main menu");
        back.setOnAction(e -> {
            window.close();
            //todo enter mainMenu
        });

        logOut = new Button("LogOut");
        logOut.setOnAction(e -> {
            Controller.setCurrentAccount(null);
            //todo enter mainMenu
        });

        showPersonalInfo = new Button("Show personal info");
        showPersonalInfo.setOnAction(e -> showInfo());
    }

    private void showInfo(){
        Stage window2 = new Stage();
        TextField username, password, name, lastName, email, phoneNumber, companyInformation;
        Button button = new Button("update");
        AccountInformation information = account.getAccountInformation();

        username = new TextField();
        username.setPromptText(information.getUsername());
        username.setEditable(false);

        password = new TextField();
        password.setPromptText(information.getPassWord());

        name = new TextField();
        name.setPromptText(information.getName());

        lastName = new TextField();
        lastName.setPromptText(information.getLastname());

        email = new TextField();
        email.setPromptText(information.getEmail());

        phoneNumber = new TextField();
        phoneNumber.setPromptText(information.getPhoneNumber());

        if(account instanceof Seller){
            companyInformation = new TextField();
            companyInformation.setPromptText(((Seller) account).getCompanyInformation());
        }

        button.setOnAction(e -> {
            information.setPhoneNumber(phoneNumber.getText());
            information.setLastName(lastName.getText());
            information.setEmail(email.getText());
            information.setName(name.getText());
            information.setPassWord(password.getText());
            AlertBox.display("Congrats", "All fields were updated");
        });

        VBox box = new VBox(15);
        box.setAlignment(Pos.CENTER);
        box.getChildren().addAll(username, password, name, lastName, email, phoneNumber);
        if(account instanceof Seller){
            companyInformation = new TextField();
            companyInformation.setPromptText(((Seller) account).getCompanyInformation());
            box.getChildren().add(companyInformation);
        }

        Scene scene = new Scene(box, 500, 500);
        window2.setScene(scene);
        window2.initModality(Modality.APPLICATION_MODAL);
        window2.show();
    }
}
