package View.GUIMenu;

import Controller.Controller;
import Model.Account.Buyer;
import Model.Account.User;
import Model.Good.Good;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;


public class ShoppingCartGUI {

    private TableView<Map.Entry<Good, Integer>> cart;
    //private HashMap<Good, Integer> cartmap;
    private Button addButton;
    private Button reduceButton;
    private Button purchase, view;
    private Buyer buyer;
    private Stage window;
    private Scene scene;

    public ShoppingCartGUI( Buyer buyer) {
        window = new Stage();

        this.buyer = buyer;

        //Number


        //Name column
        TableColumn<Map.Entry<Good, Integer>, String> nameColumn = new TableColumn<>("Product Name");
        nameColumn.setMaxWidth(200);
        nameColumn.setCellValueFactory(p -> new SimpleObjectProperty(p.getValue().getKey().getProductName()));


        //Price column
        TableColumn<Map.Entry<Good, Integer>, Double> priceColumn = new TableColumn<>("Price");
        priceColumn.setMaxWidth(200);
        priceColumn.setCellValueFactory(p -> new SimpleObjectProperty(p.getValue().getKey().getPrice()));

        //Quantity column
        TableColumn<Map.Entry<Good, Integer>, Double> quantityColumn = new TableColumn<>("Quantity");
        quantityColumn.setMaxWidth(200);
        quantityColumn.setCellValueFactory(p -> new SimpleObjectProperty(p.getValue().getValue()));

        //Total Amount of each products column
        /*
        TableColumn<Map.Entry<Good, Integer>, Double> amountColumn = new TableColumn<>("Total amount");
        amountColumn.setMaxWidth(200);
        quantityColumn.setCellValueFactory(p -> new SimpleObjectProperty((p.getValue().getValue()) * (p.getValue().getKey().getPrice())));
         */

        ObservableList<Map.Entry<Good, Integer>> items = FXCollections.observableArrayList(buyer.getCart().entrySet());
        cart = new TableView<>();
        cart.setItems(items);
        cart.getColumns().addAll(nameColumn, priceColumn, quantityColumn);

        VBox vBox = new VBox();

        HBox hBox = new HBox();
        hBox.setPadding(new Insets(20, 20, 20, 20));
        hBox.setSpacing(10);

        HBox hBox2 = new HBox();
        hBox2.setPadding(new Insets(20, 20, 20, 20));
        hBox2.setSpacing(10);

        addButton = new Button("Add");
        reduceButton = new Button("Reduce");
        purchase = new Button("Purchase");
        view = new Button("View selected product");

        hBox.getChildren().addAll(addButton, reduceButton, purchase, view);

        vBox.getChildren().addAll(hBox2, cart, hBox);

        addButtonFunctions();

        scene = new Scene(vBox, 500, 500);


    }

    public ShoppingCartGUI(Stage window, Scene headScene, User buyer) {

        //Number


        //Name column
        TableColumn<Map.Entry<Good, Integer>, String> nameColumn = new TableColumn<>("Product Name");
        nameColumn.setMaxWidth(200);
        nameColumn.setCellValueFactory(p -> new SimpleObjectProperty(p.getValue().getKey().getProductName()));


        //Price column
        TableColumn<Map.Entry<Good, Integer>, Double> priceColumn = new TableColumn<>("Price");
        priceColumn.setMaxWidth(200);
        priceColumn.setCellValueFactory(p -> new SimpleObjectProperty(p.getValue().getKey().getPrice()));

        //Quantity column
        TableColumn<Map.Entry<Good, Integer>, Double> quantityColumn = new TableColumn<>("Quantity");
        quantityColumn.setMaxWidth(200);
        quantityColumn.setCellValueFactory(p -> new SimpleObjectProperty(p.getValue().getValue()));

        //Total Amount of each products column
        /*
        TableColumn<Map.Entry<Good, Integer>, Double> amountColumn = new TableColumn<>("Total amount");
        amountColumn.setMaxWidth(200);
        quantityColumn.setCellValueFactory(p -> new SimpleObjectProperty((p.getValue().getValue()) * (p.getValue().getKey().getPrice())));
         */

        ObservableList<Map.Entry<Good, Integer>> items = FXCollections.observableArrayList(buyer.getCart().entrySet());
        cart = new TableView<>();
        cart.setItems(items);
        cart.getColumns().addAll(nameColumn, priceColumn, quantityColumn);

        VBox vBox = new VBox();

        HBox hBox2 = new HBox();
        hBox2.setPadding(new Insets(20, 20, 20, 20));
        hBox2.setSpacing(10);

        HBox hBox = new HBox();
        hBox.setPadding(new Insets(20, 20, 20, 20));
        hBox.setSpacing(10);

        addButton = new Button("Add");
        reduceButton = new Button("Reduce");
        purchase = new Button("Purchase");
        view = new Button("View product");

        hBox.getChildren().addAll(addButton, reduceButton, purchase, view);

        vBox.getChildren().addAll(hBox2, cart, hBox);

        addButtonFunctions2();

        scene = new Scene(vBox, 500, 500);

        window.setOnCloseRequest(e -> {
            window.setScene(headScene);
            window.show();
        });

    }

    private void addButtonFunctions(){
        ObservableList<Map.Entry<Good, Integer>> products,selectedProducts;
        selectedProducts = cart.getSelectionModel().getSelectedItems();

        addButton.setOnAction(e -> {
            selectedProducts.forEach(p -> buyer.increaseGoodInCart(p.getKey()));
            selectedProducts.forEach(p -> {
                cart.getItems().remove(p);
                cart.getItems().add(p);

            } );
        });

        reduceButton.setOnAction(e -> {
            selectedProducts.forEach(p -> buyer.decreaseGoodInCart(p.getKey()));
            selectedProducts.forEach(p -> {
                cart.getItems().remove(p);
                if(p.getValue() != 0 )
                    cart.getItems().add(p); // it does not work on last column
            } );
        });

        purchase.setOnAction(e -> {
            window.close();
            new PurchasePageGUI( buyer).display();
        });

        view.setOnAction(e -> {
            if(cart.getSelectionModel().isEmpty()){
                AlertBox.display("Error", "You must select a product first.");
            }
        });
    }

    private void addButtonFunctions2(){
        ObservableList<Map.Entry<Good, Integer>> products,selectedProducts;
        selectedProducts = cart.getSelectionModel().getSelectedItems();

        addButton.setOnAction(e -> {
            selectedProducts.forEach(p -> buyer.increaseGoodInCart(p.getKey()));
            selectedProducts.forEach(p -> {
                cart.getItems().remove(p);
                cart.getItems().add(p);

            } );
        });

        reduceButton.setOnAction(e -> {
            selectedProducts.forEach(p -> buyer.decreaseGoodInCart(p.getKey()));
            selectedProducts.forEach(p -> {
                cart.getItems().remove(p);
                if(p.getValue() != 0 )
                    cart.getItems().add(p); // it does not work on last column
            } );
        });

        purchase.setOnAction(e -> {
            new LoginAndSignUp();
            if(Controller.getCurrentAccount() instanceof Buyer) {
                window.close();
                new PurchasePageGUI((Buyer) Controller.getCurrentAccount());
            }
        });

        view.setOnAction(e -> {
            if(cart.getSelectionModel().isEmpty()){
                AlertBox.display("Error", "You must select a product first.");
            }
        });
    }

    public void display() {
        window.setScene(scene);
        window.show();
    }
}
