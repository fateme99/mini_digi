package View.GUIMenu;

import Controller.Controller;
import Model.Account.Account;
import Model.Account.Buyer;
import Model.Account.Manager;
import Model.Account.Seller;
import View.Menus.BuyerView;
import View.Menus.ManagerView;
import View.Menus.Menu;
import View.Menus.SellerView;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Reflection;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;

public class LoginAndSignUp {

    //private URL cssFile = getClass().getResource("/Users/imanalipour/Documents/programming/java/AP-Project2020-team-59-git/src/main/resources/CssFiles/LoginMenu.css");

    final TextField txtUserName;
    final TextField pf;
    private Stage window;
    private Scene scene;

    public LoginAndSignUp() {
        window = new Stage();
        window.setTitle("Login");

        BorderPane bp = new BorderPane();
        bp.setPadding(new Insets(10,50,50,50));

        HBox hb = new HBox();
        hb.setPadding(new Insets(20,20,20,30));

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(20,20,20,20));
        gridPane.setHgap(5);
        gridPane.setVgap(5);

        Label lblUserName = new Label("Username");
        txtUserName = new TextField();
        Label lblPassword = new Label("Password");
        pf = new PasswordField();
        Button btnLogin = new Button("Login");
        final Label lblMessage = new Label();
        Hyperlink linkToRegistrationMenu = new Hyperlink("Or Register");
        linkToRegistrationMenu.setOnAction(e -> new RegistraitionMenuGUI( false).display());

        gridPane.add(lblUserName, 0, 0);
        gridPane.add(txtUserName, 1, 0);
        gridPane.add(lblPassword, 0, 1);
        gridPane.add(pf, 1, 1);
        gridPane.add(btnLogin, 2, 1);
        gridPane.add(lblMessage, 1, 2);
        gridPane.add(linkToRegistrationMenu, 2, 2);


        Reflection r = new Reflection();
        r.setFraction(0.7f);
        gridPane.setEffect(r);

        DropShadow dropShadow = new DropShadow();
        dropShadow.setOffsetX(5);
        dropShadow.setOffsetY(5);

        Text text = new Text("Login page");
        text.setFont(Font.font("Courier New", FontWeight.BOLD, 28));
        text.setEffect(dropShadow);

        hb.getChildren().add(text);

        //Add ID's to Nodes
        bp.setStyle("-fx-background-color:  #e8e8e8");
        gridPane.setStyle("-fx-background-color:  linear-gradient(lightgray, gray);\n-fx-border-color: white;-fx-border-radius: 20;\n" +
                "    -fx-padding: 10 10 10 10;\n" +
                "    -fx-background-radius: 20;");
        btnLogin.setStyle("-fx-background-radius: 30, 30, 29, 28;\n" +
                "    -fx-padding: 3px 10px 3px 10px;\n" +
                "    -fx-background-color: linear-gradient(orange, orangered );");
        text.setStyle("-fx-fill:  linear-gradient(orange , orangered);");
        linkToRegistrationMenu.setStyle("-fx-color: white;");
        linkToRegistrationMenu.setFont(Font.font("Verdana", FontPosture.ITALIC, 10));

        btnLogin.setOnAction(e -> run());

        bp.setTop(hb);
        bp.setCenter(gridPane);

        scene = new Scene(bp);

    }

    public void run(){
        try {
            if ( !Controller.usernameExists(txtUserName.getText()) ){
                throw new Exception("no user with such username exists");
            }
            Account account = Manager.getAccountByUsername(txtUserName.getText());
            if (account.passwordIsCorrect(pf.getText())) {
                goToAccountsPage(account);
                window.close();
            } else {
                throw new Exception("password is incorrect");
            }
        }
        catch (Exception e){
            AlertBox.display("Error", e.getMessage());
        }
    }

    private void goToAccountsPage(Account account){
        Controller.setCurrentAccount(account);
        if ( account instanceof Buyer){
            addUserCartToAccount(account);
        }
    }

    private void addUserCartToAccount(Account account){
        Buyer buyer = (Buyer)account;
        buyer.addItemsToCart(Controller.getCurrentUser().getCart());
    }

    public void display() {
        window.setScene(scene);
        window.show();
    }
}
