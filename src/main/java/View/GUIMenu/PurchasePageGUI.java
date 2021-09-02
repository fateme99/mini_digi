package View.GUIMenu;

import Controller.Controller;
import Model.Account.Buyer;
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
import Controller.BuyerController;

public class PurchasePageGUI {

    private Double finalPrice;
    private Buyer buyer;
    private Stage window;
    private Scene scene;

    public PurchasePageGUI( Buyer buyer) {
        window = new Stage();
        this.buyer = buyer;
        window.setTitle("Purchase");

        BorderPane bp = new BorderPane();
        bp.setPadding(new Insets(10,50,50,50));

        HBox hb = new HBox();
        hb.setPadding(new Insets(20,20,20,30));

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(20,20,20,20));
        gridPane.setHgap(5);
        gridPane.setVgap(5);

        Label lblPhone = new Label("Phone number");
        final TextField txtPhone = new TextField();
        Label lblAddress = new Label("Address");
        final TextField address = new TextField();

        Label lblOffTicket = new Label("Off ticket");
        final TextField offTicketField = new PasswordField();

        Button btnLogin = new Button("Submit");
        final Label lblMessage = new Label("total amount = " + buyer.getCartValue() + "$");

        gridPane.add(lblPhone, 0, 0);
        gridPane.add(txtPhone, 1, 0);
        gridPane.add(lblAddress, 0, 1);
        gridPane.add(address, 1, 1);
        gridPane.add(lblOffTicket, 0, 2);
        gridPane.add(offTicketField, 1, 2);
        gridPane.add(btnLogin, 2, 3);
        gridPane.add(lblMessage, 1, 3);


        Reflection r = new Reflection();
        r.setFraction(0.7f);
        gridPane.setEffect(r);

        DropShadow dropShadow = new DropShadow();
        dropShadow.setOffsetX(5);
        dropShadow.setOffsetY(5);

        Text text = new Text("Purchase");
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

        //Action for btnLogin
        btnLogin.setOnAction(e -> {
            if(txtPhone.getText().equals("")  || address.getText().equals("") ){
                AlertBox.display("Error", "You should complete phone number and address fields first");
            }else if(!offTicketField.getText().equals("")) {
                try {
                    finalPrice = Controller.useOffTicket(offTicketField.getText(), buyer);
                    try {
                        BuyerController.purchase(buyer, finalPrice);
                        window.close();
                        AlertBox.display("Congrats", "Purchase was successful!");
                    } catch (Exception ex) {
                        AlertBox.display("Error", ex.getMessage());
                    }
                } catch (Exception ex) {
                    AlertBox.display("Error", ex.getMessage());
                }
            }else{
                finalPrice = buyer.getCartValue();
                try {
                    BuyerController.purchase(buyer, finalPrice);
                    window.close();
                    AlertBox.display("Congrats", "Purchase was successful!");
                } catch (Exception ex) {
                    AlertBox.display("Error", ex.getMessage());
                }
            }
        });

        bp.setTop(hb);
        bp.setCenter(gridPane);

        scene = new Scene(bp);
        //scene.getStylesheets().add(cssFile.toExternalForm());

    }

    public void display() {
        window.setScene(scene);
        window.show();
    }
}
