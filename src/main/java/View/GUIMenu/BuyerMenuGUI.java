package View.GUIMenu;

import Controller.Controller;
import Model.Account.Account;
import Model.Account.AccountInformation;
import Model.Account.Buyer;
import Model.Account.Seller;
import Model.Discount.OffTicket;
import Model.Good.Good;
import Model.log.BuyLog;
import Model.log.Log;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class BuyerMenuGUI {

    private HBox topMenu;
    private VBox mainMenu;
    private Button back, logOut, showPersonalInfo, showBuyLogs, viewCart, showOffTickets;
    private Account account;
    private Label role;
    private Stage window;
    private Scene scene;

    public BuyerMenuGUI( Account account) {
        window = new Stage();
        this.account = account;

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
            window.close();
            //todo enter mainMenu
        });

        showPersonalInfo = new Button("Show personal info");
        showPersonalInfo.setOnAction(e -> showInfo());

        role = new Label("Role : Buyer ");

        viewCart = new Button("View Cart");


        topMenu.getChildren().addAll(back, logOut, showPersonalInfo, role, viewCart);

        Label balance = new Label("Total balance = " + account.getBalance());

        mainMenu = new VBox(15);
        mainMenu.setAlignment(Pos.TOP_CENTER);
        mainMenu.getChildren().add(topMenu);
        mainMenu.getChildren().add(balance);

        TableColumn<BuyLog, String> logIds = new TableColumn<>("Buy Logs");
        logIds.setMaxWidth(200);
        logIds.setCellValueFactory(new PropertyValueFactory<>("logId"));

        TableView<BuyLog> buyLogTable = new TableView<>();
        buyLogTable.setItems(getAccountLogs());
        buyLogTable.getColumns().add(logIds);

        buyLogTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> showLogInfo(newSelection));

        mainMenu.getChildren().add(buyLogTable);

        TableColumn<OffTicket, String> offTickets = new TableColumn<>("Off tickets");
        offTickets.setMaxWidth(200);
        offTickets.setCellValueFactory(new PropertyValueFactory<>("offTicketId"));

        TableView<OffTicket> offTable = new TableView<>();
        offTable.setItems(offs());
        offTable.getColumns().add(offTickets);

        offTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> showOffInfo(newSelection));

        mainMenu.getChildren().add(offTable);

        scene = new Scene(mainMenu, 500, 300);

        viewCart.setOnAction(e -> new ShoppingCartGUI( (Buyer) account).display());
    }

    private void showOffInfo(OffTicket offTicket){
        Stage window2 = new Stage();
        Scene scene2;

        VBox vBox = new VBox(10);
        vBox.setAlignment(Pos.TOP_CENTER);

        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");

        Label info = new Label();
        info.setText("Starting date : " + offTicket.getStartingDate() + "\nEnding date : " + offTicket.getEndingDate() + "Off ticket id" + offTicket.getOffTicketId());

        vBox.getChildren().add(info);
        scene2 = new Scene(vBox, 200, 500);

        window2.initModality(Modality.APPLICATION_MODAL);
        window2.setScene(scene2);
        window2.show();
    }

    private ObservableList<OffTicket> offs(){
        ObservableList<OffTicket> offs =  FXCollections.observableArrayList();

        offs.addAll((account).getOffTickets());

        return offs;
    }

    private void showLogInfo(BuyLog log){
        Stage window2 = new Stage();
        Scene scene2;

        VBox vBox = new VBox(10);
        vBox.setAlignment(Pos.TOP_CENTER);

        String productNames = "";
        for (Good good : log.getGoodsExchanged()) {
            productNames = good.getProductName() + "\n";
        }

        Label info = new Label();
        info.setText("Log id : " + log.getLogId() + "\nDate : " + log.getDate() + "\nTotal Price : " + log.getMoneyExchanged() + "\nDiscount : " + log.getDiscountAmount() + "\nSeller name : " + log.getSellerName() + "\n" + productNames);

        vBox.getChildren().add(info);
        scene2 = new Scene(vBox, 300, 400);

        window2.initModality(Modality.APPLICATION_MODAL);
        window2.setScene(scene2);
        window2.show();
    }

    private ObservableList<BuyLog> getAccountLogs(){
        ObservableList<BuyLog> logs =  FXCollections.observableArrayList();

        logs.addAll(((Buyer) account).getBuyLog());

        return logs;
    }

    private void showInfo(){
        Stage window2 = new Stage();
        Scene scene2;
        TextField username, password, name, lastName, email, phoneNumber;
        final TextField finalCompanyInformation = new TextField();
        Button button = new Button("update");
        AccountInformation information = account.getAccountInformation();

        username = new TextField();
        username.setMaxWidth(150);
        username.setText(information.getUsername());
        username.setEditable(false);

        password = new TextField();
        password.setMaxWidth(150);
        password.setPromptText(information.getPassWord());

        name = new TextField();
        name.setMaxWidth(150);
        name.setPromptText(information.getName());

        lastName = new TextField();
        lastName.setMaxWidth(150);
        lastName.setPromptText(information.getLastname());

        email = new TextField();
        email.setMaxWidth(150);
        email.setPromptText(information.getEmail());

        phoneNumber = new TextField();
        phoneNumber.setMaxWidth(150);
        phoneNumber.setPromptText(information.getPhoneNumber());

        button.requestFocus();

        if(account instanceof Seller){
            finalCompanyInformation.setPromptText(((Seller) account).getCompanyInformation());
        }

        button.setOnAction(e -> {
            information.setPhoneNumber(phoneNumber.getText());
            information.setLastName(lastName.getText());
            information.setEmail(email.getText());
            information.setName(name.getText());
            information.setPassWord(password.getText());
            if(account instanceof Seller){
                ((Seller) account).setCompanyInformation(finalCompanyInformation.getText());
            }
            AlertBox.display("Congrats", "All fields were updated");
        });

        Label lblUserName = new Label("Username");
        Label lblPassword = new Label("Password");

        Label namelbl = new Label("Name");
        Label lastNamelbl = new Label("Last name");
        Label emailLbl = new Label("Email");
        Label lblNumber = new Label("PhoneNumber");

        GridPane box = new GridPane();
        box.setPadding(new Insets(20,20,20,20));
        box.setHgap(5);
        box.setVgap(5);

        box.add(username, 2, 0);
        box.add(lblUserName, 1, 0);
        box.add(password, 2, 1);
        box.add(lblPassword, 1, 1);

        box.add(name, 2, 2);
        box.add(namelbl, 1, 2);
        box.add(lastName, 2, 3);
        box.add(lastNamelbl, 1, 3);

        box.add(email, 2, 4);
        box.add(emailLbl, 1, 4);
        box.add(phoneNumber, 2, 5);
        box.add(lblNumber, 1, 5);

        box.add(button, 3, 5);

        if(account instanceof Seller){
            Label companyInformationlbl = new Label("Company Information");
            finalCompanyInformation.setPromptText(((Seller) account).getCompanyInformation());

            box.add(finalCompanyInformation, 2, 6);
            box.add(finalCompanyInformation, 1, 6);
        }

        scene2 = new Scene(box, 300, 500);
        window2.setScene(scene2);
        window2.initModality(Modality.APPLICATION_MODAL);
        window2.show();
    }

    public void display() {
        window.setScene(scene);
        window.show();
    }
}
