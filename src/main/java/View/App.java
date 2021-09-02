package View;

import Controller.Controller;
import Model.Account.*;
import Model.Good.Category;
import Model.Good.Comment;
import Model.Good.Good;
import Model.log.BuyLog;
import Model.log.SellLog;
import Model.log.ShipmentState;
import View.GUIMenu.LoginAndSignUp;
import View.GUIMenu.ShoppingCartGUI;
import javafx.application.Application;
import javafx.css.Size;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;

/**
 * JavaFX App
 */
public class App extends Application {
    Scene signScene,mainscene,offScene,productScene,createManager,compareScene , buyLogsScene ,sellLogsScene;
    String productId;
    final Controller controller = Controller.getInstance();
    Stage stageGlobal;
    GridPane goodGridPane=new GridPane();
    GridPane offGridPane=new GridPane();
    GridPane compareGrid=new GridPane();
    GridPane sellLogGridPane=new GridPane();
    GridPane buyLogGridPane=new GridPane();
    @Override
    public void start(Stage stage) throws FileNotFoundException {
        stageGlobal=stage;
        createSamples();
        checkManager();
        globalSetting();

    }
    private void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }
    private void showBuyLogs () {

        Text buyLogText = new Text ("buy Logs page") ;
        buyLogText.setFont(Font.font("Verdana",30));
        buyLogGridPane.add(buyLogText, 0, 0);
        Buyer buyer= (Buyer) Controller.getCurrentAccount() ;
        ArrayList <BuyLog> buyLogs = buyer.getBuyLog() ;
        int h=2;
        for (BuyLog buyLog : buyLogs) {

            Text textDate=new Text();

            textDate.setText(buyLog.getDate().toString());
            textDate.setFont(Font.font("Verdana",15));
            buyLogGridPane.add(textDate , 2 , h);

            Text textMoneyExchanged=new Text();

            textMoneyExchanged.setText(String.valueOf(buyLog.getMoneyExchanged()));
            textMoneyExchanged.setFont(Font.font("Verdana",15));
            buyLogGridPane.add(textMoneyExchanged , 3 , h);

            Text textGoodsExchanged=new Text();
            String goodsExchangedName = null;
            for (Good good : buyLog.getGoodsExchanged()){
                goodsExchangedName = goodsExchangedName + " "+ good.getProductName()   ;

            }
            textGoodsExchanged.setText(goodsExchangedName);
            textGoodsExchanged.setFont(Font.font("Verdana",15));
            buyLogGridPane.add(textGoodsExchanged , 4 , h);
            Text textDiscountAmount = new Text ();
            textDiscountAmount.setText(String.valueOf(buyLog.getDiscountAmount())) ;
            textDiscountAmount.setFont(Font.font("Verdana",15));
            buyLogGridPane.add(textDiscountAmount, 5, h);
            Text textShipmentState = new Text ( );
            ShipmentState shipmentState = buyLog.getShipmentState() ;
            String state = null ;
            if (shipmentState.equals(ShipmentState.PAID))
                state = " Paid." ;
            else if (shipmentState.equals(ShipmentState.READY_TO_BE_SENT))
                state = " Ready to be sent." ;
            else if (shipmentState.equals(ShipmentState.SENT))
                state = " Sent." ;
            else if (shipmentState.equals(ShipmentState.ARRIVED_TO_COUMER))
                state = " Arrived to coumer." ;
            textShipmentState.setText("Shipment state :" + state) ;
            textShipmentState.setFont(Font.font("Verdana",15));
            buyLogGridPane.add(textShipmentState,6, h) ;
            Text textBuyerName =new Text () ;
            textBuyerName.setText("Buyer :" + buyLog.getSellerName());
            textBuyerName.setFont(Font.font("Verdana",15));
            buyLogGridPane.add(textShipmentState,7, h) ;
            h++;

        }
        buyLogGridPane.setVgap(20);
        buyLogGridPane.setHgap(20);
        Text backText=new Text("back");
        backText.setFont(Font.font("Verdana",20));
        Button back1Button=new Button(backText.getText());
        buyLogGridPane.add(back1Button,1,1);
        back1Button.setOnAction(e-> stageGlobal.setScene(mainscene)); // back to user pannel

        buyLogGridPane.setMinSize(750,480);
        buyLogsScene=new Scene(buyLogGridPane);


    }
    private void showSellLogs () {

        Text sellLogText = new Text ("sell Logs page") ;
        sellLogText.setFont(Font.font("Verdana",30));
        sellLogGridPane.add(sellLogText, 0, 0);
        Seller seller = (Seller) Controller.getCurrentAccount() ;
        ArrayList <SellLog> sellLogs = seller.getSellLog() ;
        int h=2;
        for (SellLog sellLog : sellLogs) {

            Text textDate=new Text();

            textDate.setText(sellLog.getDate().toString());
            textDate.setFont(Font.font("Verdana",15));
            sellLogGridPane.add(textDate , 2 , h);

            Text textMoneyExchanged=new Text();

            textMoneyExchanged.setText(String.valueOf(sellLog.getMoneyExchanged()));
            textMoneyExchanged.setFont(Font.font("Verdana",15));
            sellLogGridPane.add(textMoneyExchanged , 3 , h);

            Text textGoodsExchanged=new Text();
            String goodsExchangedName = null;
            for (Good good : sellLog.getGoodsExchanged()){
                goodsExchangedName = goodsExchangedName + " "+ good.getProductName()   ;

            }
            textGoodsExchanged.setText(goodsExchangedName);
            textGoodsExchanged.setFont(Font.font("Verdana",15));
            sellLogGridPane.add(textGoodsExchanged , 4 , h);
            Text textDiscountAmount = new Text ();
            textDiscountAmount.setText(String.valueOf(sellLog.getDiscountAmount())) ;
            textDiscountAmount.setFont(Font.font("Verdana",15));
            sellLogGridPane.add(textDiscountAmount, 5, h);
            Text textShipmentState = new Text ( );
            ShipmentState shipmentState = sellLog.getShipmentState() ;
            String state = null ;
            if (shipmentState.equals(ShipmentState.PAID))
                state = " Paid." ;
            else if (shipmentState.equals(ShipmentState.READY_TO_BE_SENT))
                state = " Ready to be sent." ;
            else if (shipmentState.equals(ShipmentState.SENT))
                state = " Sent." ;
            else if (shipmentState.equals(ShipmentState.ARRIVED_TO_COUMER))
                state = " Arrived to coumer." ;
            textShipmentState.setText("Shipment state :" + state) ;
            textShipmentState.setFont(Font.font("Verdana",15));
            sellLogGridPane.add(textShipmentState,6, h) ;
            Text textBuyerName =new Text () ;
            textBuyerName.setText("Buyer :" + sellLog.getBuyerName());
            textBuyerName.setFont(Font.font("Verdana",15));
            sellLogGridPane.add(textShipmentState,7, h) ;
            h++;

        }
        sellLogGridPane.setVgap(20);
        sellLogGridPane.setHgap(20);
        Text backText=new Text("back");
        backText.setFont(Font.font("Verdana",20));
        Button back1Button=new Button(backText.getText());
        sellLogGridPane.add(back1Button,1,1);
        back1Button.setOnAction(e-> stageGlobal.setScene(mainscene)); // back to user pannel

        sellLogGridPane.setMinSize(750,480);
        sellLogsScene=new Scene(sellLogGridPane);


    }
    private void showProduct(){

        Good good=controller.getGood(productId);
        GridPane goodGridPane=new GridPane();
        FileInputStream input3 = null;
        try {
            input3 = new FileInputStream(good.getImageAddress());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Text backText=new Text("back");
        backText.setFont(Font.font("Verdana",20));
        Button backButton=new Button(backText.getText());
        goodGridPane.add(backButton,0,0);

        backButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                stageGlobal.setScene(mainscene);
            }

            });
        TextField username=new TextField();
        username.setPromptText("username");
        TextArea commentField=new TextArea();
        Button commentButton=new Button("Send");
        commentField.setMaxWidth(200);
        commentField.setPromptText("comment");
        goodGridPane.add(username,2, 8);
        goodGridPane.add(commentField,2,9);
        goodGridPane.add(commentButton,3,9);
        commentButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String userName=username.getText();
                String content=commentField.getText();
                Comment comment=new Comment(userName,content);
                controller.addComment(comment,good);
                username.clear();
                commentField.clear();
                showAlert(Alert.AlertType.INFORMATION, goodGridPane.getScene().getWindow(),
                        "Success Comment", "Good Luck");
                return;

            }

        });

        TextField rateInput=new TextField();
        Button rateButton=new Button("Send");
        rateInput.setMaxWidth(200);
        rateInput.setPromptText("rate");
        goodGridPane.add(rateInput,2,10);
        goodGridPane.add(rateButton,3,10);
        rateButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (controller.getCurrentAccount()==null){
                    showAlert(Alert.AlertType.ERROR, goodGridPane.getScene().getWindow(),
                            "not Login", "please first Login after that Rate");
                    return;
                }
                else {
                    if (controller.getCurrentAccount() instanceof Buyer && controller.isbuy(good,(Buyer)controller.getCurrentAccount())){
                        String rate=rateInput.getText();
                        double rate1=Double.parseDouble(rate);
                        controller.addRate(rate1,good);
                        showAlert(Alert.AlertType.INFORMATION, goodGridPane.getScene().getWindow(),
                                "rate success", "your rate has been saved");
                        return;
                    }
                    else {
                        showAlert(Alert.AlertType.ERROR, goodGridPane.getScene().getWindow(),
                                "not Valid", "you didnt buy this product");
                        return;
                    }
                }


            }

        });

        Button compareButton=new Button("Compare");
        goodGridPane.add(compareButton,3,2);
        compareButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                showCompare();
                stageGlobal.setScene(compareScene);
            }

        });
        Button buyButton=new Button("Buy");
        goodGridPane.add(buyButton,4,2);
        buyButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                new ShoppingCartGUI((Buyer)Controller.getCurrentAccount()).display();
            }

        });


        Image goodimage = new Image(input3);
        ImageView goodImageView=new ImageView(goodimage);
        goodImageView.setFitHeight(100);
        goodImageView.setFitWidth(150);
        goodGridPane.add(goodImageView,2,2);

        Text textName=new Text();
        textName.setText("Name : "+good.getProductName());
        textName.setFont(Font.font("Verdana" , 15));
        goodGridPane.add(textName , 2 , 3);

        Text textPrice=new Text();
        textPrice.setText("Price : "+String.valueOf(good.getPrice()));
        textPrice.setFont(Font.font("Verdana" , 15));
        goodGridPane.add(textPrice , 3 , 3);

        Text textCharacteristic=new Text();
        textCharacteristic.setText("Characteristic : "+good.getCharacteristics());
        textCharacteristic.setFont(Font.font("Verdana",15));
        goodGridPane.add(textCharacteristic , 2 , 5);

        Text offpercentText=new Text();
        offpercentText.setText("off percent : "+good.getOffpercent());
        offpercentText.setFont(Font.font("Verdana",15));
        goodGridPane.add(offpercentText , 2 , 4);

        Text commentsText=new Text();
        commentsText.setText("comments : "+good.getComments());
        commentsText.setFont(Font.font("Verdana",15));
        goodGridPane.add(commentsText , 2 , 6);

        Text averageRateText=new Text();
        averageRateText.setText("average Rate : "+good.getAverageRate());
        averageRateText.setFont(Font.font("Verdana",15));
        goodGridPane.add(averageRateText , 3 , 4);

        Text sellerText=new Text();
        sellerText.setText("sellers : "+good.getSellers());
        sellerText.setFont(Font.font("Verdana",15));
        goodGridPane.add(sellerText , 2 , 7);


        goodGridPane.setMinSize(600,700);
        goodGridPane.setVgap(20);
        goodGridPane.setHgap(5);
        productScene=new Scene(goodGridPane);
    }
    public static void main(String[] args) {

        launch();
    }
    public void createSamples(){
        /*                                           samples                              */
        AccountInformation accountInformation=new AccountInformation("fa" , "jdks" , "as","sadjk" , "033","13");
        Manager manager=new Manager(accountInformation,Role.MANAGER);
        Category sport=new Category("sport" , "sport vasile" , null,null,null);
        controller.addcategory(sport);
        Category book =new Category("book" , "lavazem tahrir vasile" , null,null,null);
        controller.addcategory(book);
        Category shirt=new Category("shirt" , "pooshak" , null,null,null);
        controller.addcategory(shirt);
        Good good1=new Good("book4",book,120,"book",null,null,"D:\\Maktab\\projects\\Project_team59team\\src\\main\\java\\Model\\Image\\book.jpg");
        good1.setOffpercent(10);
        controller.addGood(good1);
        Good good2=new Good("book2",book,120,"book",null,null,"D:\\Maktab\\projects\\Project_team59team\\src\\main\\java\\Model\\Image\\book.jpg");
        good2.setOffpercent(10);
        controller.addGood(good2);
        Good good3=new Good("book3",book,120,"book",null,null,"D:\\Maktab\\projects\\Project_team59team\\src\\main\\java\\Model\\Image\\book.jpg");
        good3.setOffpercent(20);
        controller.addGood(good3);
        controller.addGood(new Good("shoe",sport,12,"book",null,null,"D:\\Maktab\\projects\\Project_team59team\\src\\main\\java\\Model\\Image\\shoe.jpg"));
        controller.addGood(new Good("book2",book,1200,"book",null,null,"D:\\Maktab\\projects\\Project_team59team\\src\\main\\java\\Model\\Image\\book.jpg"));
        controller.addGood(new Good("shoe",sport,1200,"book",null,null,"D:\\Maktab\\projects\\Project_team59team\\src\\main\\java\\Model\\Image\\shoe.jpg"));
        /*controller.addGood(new Good("book3",1200,"book",null,null,"D:\\Maktab\\projects\\Project_team59team\\src\\main\\java\\Model\\Image\\book.jpg"));
        controller.addGood(new Good("shoe",1200,"book",null,null,"D:\\Maktab\\projects\\Project_team59team\\src\\main\\java\\Model\\Image\\shoe.jpg"));
       */ /*                                               end samples                       */

    }
    public void checkManager(){
        if (controller.isManager()){
            showMaineScene();
            stageGlobal.setScene(mainscene);
            stageGlobal.show();
        }
        else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");

            alert.setHeaderText("No Manager exists");
            alert.setContentText("please enter manager information!");

            alert.showAndWait();
            showCreateManager();
            stageGlobal.setScene(createManager);
            stageGlobal.show();
        }

    }
    public void showCompare(){
        TextField searchProduct=new TextField();
        searchProduct.setPromptText("productId");
        Button searchButton=new Button("compare");
        Button backButton=new Button("back");
        backButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                stageGlobal.setScene(productScene);
            }

        });
        searchButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String productId2=searchProduct.getText();
                Good good=controller.getGood(productId2);
                Good good1=controller.getGood(productId);
                showTwoGood(good ,good1 );

            }

        });


        compareGrid.setMinSize(700, 700);
        compareGrid.setVgap(20);
        compareGrid.setHgap(20);
        compareGrid.add(searchButton , 10 ,2);
        compareGrid.add(searchProduct , 9 ,2);
        compareGrid.add(backButton , 2 ,2);
        compareScene=new Scene(compareGrid);
    }
    public void showMaineScene(){
        /*                                                           main scene                                          */
        GridPane maingridPane=new GridPane();
        Button signInButton=new Button("sign in / sign up ");
        signInButton.setFont(Font.font("Verdana", FontPosture.ITALIC, 30));

        signInButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                signInShow();
                stageGlobal.setScene(signScene);


            }

        });
        Button goodButton=new Button(" Products List ");
        goodButton.setFont(Font.font("Verdana", FontPosture.ITALIC, 30));

        goodButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                stageGlobal.setScene(showProducts());


            }
        });
        Button offButton=new Button(" Offs List ");
        offButton.setFont(Font.font("Verdana", FontPosture.ITALIC, 30));
        offButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                showOffScene();
                stageGlobal.setScene(offScene);


            }
        });

        maingridPane.setMinSize(700,500);
        maingridPane.setAlignment(Pos.CENTER);
        maingridPane.setVgap(20);
        maingridPane.setHgap(20);

        BackgroundFill background_fill = new BackgroundFill(Color.LEMONCHIFFON,
                CornerRadii.EMPTY, Insets.EMPTY);
        Background background2 = new Background(background_fill);

        maingridPane.setBackground(background2);
        maingridPane.add(signInButton,2,3);
        maingridPane.add(offButton,1,4);
        maingridPane.add(goodButton,3,4);
        mainscene=new Scene(maingridPane);
    }
    public void showCreateManager(){
        Text usernameText=new Text("user name : ");
        usernameText.setFont(Font.font ("Verdana", 10));
        Label usernamelabel=new Label(usernameText.getText());
        TextField usernameField=new TextField();
        Text passText=new Text("pass word : ");
        passText.setFont(Font.font ("Verdana", 10));
        Label passlabel=new Label(passText.getText());
        PasswordField passField=new PasswordField();
        Text submitText=new Text("Submit");
        submitText.setFont(Font.font("Verdana" , 15));
        Button submitButton=new Button(submitText.getText());


        GridPane gridCreateManagr=new GridPane();
        gridCreateManagr.setMinSize(480 ,320);
        gridCreateManagr.add(usernamelabel , 0 , 0);
        gridCreateManagr.add(usernameField , 1 , 0);
        gridCreateManagr.add(passlabel , 0 , 1);
        gridCreateManagr.add(passField , 1 , 1);
        gridCreateManagr.add(submitButton, 1 , 2);

        gridCreateManagr.setAlignment(Pos.BASELINE_CENTER);
        gridCreateManagr.setVgap(20);
        gridCreateManagr.setHgap(20);
        gridCreateManagr.setPadding(new Insets(100 ));

        submitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(usernameField.getText().isEmpty()) {
                    showAlert(Alert.AlertType.ERROR, gridCreateManagr.getScene().getWindow(),
                            "Form Error!", "Please enter your name");
                    return;
                }
                if(passField.getText().isEmpty()) {
                    showAlert(Alert.AlertType.ERROR, gridCreateManagr.getScene().getWindow(),
                            "Form Error!", "Please enter a password");
                    return;
                }

                showAlert(Alert.AlertType.CONFIRMATION, gridCreateManagr.getScene().getWindow(),
                        "Registration Successful!", "Welcome " + usernameField.getText());
                controller.createManager(usernameField.getText(),passField.getText());
                showMaineScene();
                stageGlobal.setScene(mainscene);


            }
        });


        createManager=new Scene(gridCreateManagr);

    }
    public void showOffScene(){

        showOffProducts();
        offGridPane.setMinSize(700,480);
        offScene=new Scene(offGridPane);
        offGridPane.setVgap(20);
        offGridPane.setHgap(20);
    }
    public Scene showProducts(){

        Text goodText=new Text("Products Page");
        goodText.setFont(Font.font("Verdana",30));
        ComboBox sortcomboBox = new ComboBox();
        Text sortText=new Text("sort by");
        sortText.setFont(Font.font("Verdana",20));
        sortcomboBox.setPromptText(sortText.getText());
        Button searchButton=new Button("submit sort and category");
        Collection<Good> goods =controller.getgoods();
        sortcomboBox.getSelectionModel().select(0);




        Text pricedecText=new Text("Price DEC");
        pricedecText.setFont(Font.font("Arial" ,10));
        sortcomboBox.getItems().add(pricedecText);
        Text priceAscText=new Text("Price ASC");
        priceAscText.setFont(Font.font("Arial" , 10));
        sortcomboBox.getItems().add(priceAscText);
        Text visitSortText=new Text("visiting Times");
        visitSortText.setFont(Font.font("Arial" , 10));
        sortcomboBox.getItems().add(visitSortText);
        Text nothingSort=new Text("Nothing");
        nothingSort.setFont(Font.font("Arial",10));
        sortcomboBox.getItems().add(nothingSort);
        goodGridPane.add(sortcomboBox,8,1);
        goodGridPane.add(searchButton,6,1);


        ComboBox categorycomboBox = new ComboBox();
        Text categoryText=new Text("category");
        categoryText.setFont(Font.font("Verdana" , 20));
        categorycomboBox.setPromptText(categoryText.getText());
        Collection<Category>categories= controller.getCategories();
        for (Category category:categories) {
            Text catTex=new Text(category.getCategoryName());
            catTex.setFont(Font.font("Arial" , 10));
            categorycomboBox.getItems().add(catTex);
        }
        Text nothingCategory=new Text("Nothing");
        nothingCategory.setFont(Font.font("Arial",10));
        categorycomboBox.getItems().add(nothingCategory);

        searchButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String categoryValue = categorycomboBox.getValue().toString();
                String sortValue=sortcomboBox.getValue().toString();
                if (sortValue.contains("Price DEC")){
                    Collection<Good>goods1;
                    goods1=controller.category(goods,categoryValue);

                    goods1=controller.sortbyPrice(goods1);
                    showbriefGood(goods1);
                }
                else if (sortValue.contains("Price ASC")){
                    Collection<Good>goods1;
                    goods1=controller.category(goods,categoryValue);

                    goods1=controller.sortbyPrice(goods1);
                    showbriefGood(goods1);

                }
                else if (sortValue.contains("visiting Times")){
                    Collection<Good>goods1;
                    goods1=controller.category(goods,categoryValue);

                    goods1=controller.sortbyVisit(goods1);
                    showbriefGood(goods1);
                }
                else if (sortValue.contains("Nothing")){
                    Collection<Good>goods1;
                    if (categoryValue.contains("Nothing")){
                        goods1=goods;
                    }
                    else {
                        goods1=controller.category(goods,categoryValue);
                    }

                    showbriefGood(goods1);
                }
            }
        });

        goodGridPane.add(categorycomboBox,9,1);



        goodGridPane.setVgap(30);
        goodGridPane.setHgap(40);
        Text backText=new Text("back");
        backText.setFont(Font.font("Arial" , 20));
        Button backButton=new Button(backText.getText());
        goodGridPane.add(backButton,1,1);

        backButton.setOnAction(new EventHandler<ActionEvent>() {
              @Override
              public void handle(ActionEvent event) {
                    showMaineScene();
                    stageGlobal.setScene(mainscene);
               }
                });
        goodGridPane.add(goodText,3,1);

        TextField searchField=new TextField();
        searchField.setFont(Font.font("Verdana",17));
        searchField.setPromptText("search");
        searchField.setMaxWidth(120);

        Button searchBut=new Button("Search");
        goodGridPane.add(searchBut,11,1);
        searchBut.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String name=searchField.getText();
                Collection<Good>goods1=controller.getgoods();
                ArrayList<Good>result=new ArrayList<>();
                for (Good good:goods1) {
                    if (good.getProductName().contains(name)){
                        result.add(good);
                    }
                }
                showbriefGood(result);
            }

        });

        goodGridPane.add(searchField,10,1);

        goodGridPane.setMinSize(700,480);
        Scene goodsScene=new Scene(goodGridPane);
        return goodsScene;
    }
    public void signInShow(){
        new LoginAndSignUp().display();
    }
    public void showbriefGood(Collection<Good> goods1){
        int i=2;
        for (Good good:goods1) {
            FileInputStream input2 = null;
            try {
                input2 = new FileInputStream(good.getImageAddress());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            Image goodimage = new Image(input2);
            ImageView goodImageView=new ImageView(goodimage);
            goodImageView.setFitHeight(60);
            goodImageView.setFitWidth(60);
            goodGridPane.add(goodImageView,i,2);

            Text textName=new Text();
            textName.setText("Name : "+good.getProductName());
            textName.setFont(Font.font("Verdana" , 15));
            goodGridPane.add(textName , i , 3);

            Text textPrice=new Text();
            textPrice.setText("Price : "+String.valueOf(good.getPrice()));
            textPrice.setFont(Font.font("Verdana" , 15));
            goodGridPane.add(textPrice , i , 4);

            Text textAverageRate=new Text();
            textAverageRate.setText("Average Rate : "+good.getAverageRate());
            textAverageRate.setFont(Font.font("Verdana",15));
            goodGridPane.add(textAverageRate , i , 5);
            Text moreText=new Text("more");
            moreText.setFont(Font.font("Arial" , 15));
            Button moreButton=new Button(moreText.getText());
            goodGridPane.add(moreButton,i,6);
            moreButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    productId=good.getProductId();
                    showProduct();
                    stageGlobal.setScene(productScene);

                }
            });
            i++;
        }
    }
    public void globalSetting() throws FileNotFoundException {
        Text text=new Text("Digi Kala");
        text.setFont(new Font("Helvetica" ,20));
        stageGlobal.setTitle(text.getText());
        FileInputStream input = new FileInputStream("D:\\Maktab\\projects\\Project_team59team\\src\\main\\java\\Model\\Image\\background.png");
        Image image = new Image(input);
        stageGlobal.getIcons().add(image);

    }
    public void showOffProducts(){
        Collection<Good> goods =controller.getgoods();
        Text backText=new Text("back");
        backText.setFont(Font.font("Arial" , 20));
        Button backButton=new Button(backText.getText());
        offGridPane.add(backButton,1,1);
        backButton.setOnAction(e->stageGlobal.setScene(mainscene));
        int i=2;
        for (Good good:goods) {

            if (good.getOffpercent()!=0){

                    FileInputStream input2 = null;
                    try {
                        input2 = new FileInputStream(good.getImageAddress());
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    Image goodimage = new Image(input2);
                    ImageView goodImageView=new ImageView(goodimage);
                    goodImageView.setFitHeight(60);
                    goodImageView.setFitWidth(60);
                    offGridPane.add(goodImageView,i,2);

                    Text textName=new Text();
                    textName.setText("Name : "+good.getProductName());
                    textName.setFont(Font.font("Verdana" , 15));
                    offGridPane.add(textName , i , 3);

                    Text textPrice=new Text();
                    textPrice.setText("Price : "+String.valueOf(good.getPrice()));
                    textPrice.setFont(Font.font("Verdana" , 15));
                    offGridPane.add(textPrice , i , 4);

                    Text textTimeOff=new Text();
                    textTimeOff.setText("Remaind off Time : "+String.valueOf(good.getTimestoUse()));
                    textTimeOff.setFont(Font.font("Verdana" , 15));
                    offGridPane.add(textTimeOff , i , 5);

                    Text textAmountOff=new Text();
                    textAmountOff.setText("off percent : "+String.valueOf(good.getOffpercent()));
                    textAmountOff.setFont(Font.font("Verdana" , 15));
                    offGridPane.add(textAmountOff , i , 6);

                    Text textCharacteristic=new Text();
                    textCharacteristic.setText("Characteristic : "+good.getCharacteristics());
                    textCharacteristic.setFont(Font.font("Verdana",15));
                    offGridPane.add(textCharacteristic , i , 7);
                    Text moreText=new Text("more");
                    moreText.setFont(Font.font("Arial" , 15));
                    Button moreButton=new Button(moreText.getText());
                    offGridPane.add(moreButton,i,8);
                    moreButton.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            productId=good.getProductId();
                            showProduct();
                            stageGlobal.setScene(productScene);

                        }
                    });
                    i++;
            }
        }

    }
    public void showTwoGood(Good good,Good good1){
        Text goodName=new Text(good.getProductName());
        Text goodName1=new Text(good1.getProductName());
        compareGrid.add(new Text("Name : ") , 2,4);
        compareGrid.add(goodName,3,4);
        compareGrid.add(goodName1,6,4);
        FileInputStream input = null;
        FileInputStream input1 = null;

        try {
            input = new FileInputStream(good.getImageAddress());
            input1 = new FileInputStream(good1.getImageAddress());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Image goodimage=new Image(input);
        Image goodimage1=new Image(input1);
        ImageView imageView=new ImageView(goodimage);
        ImageView imageView1=new ImageView(goodimage1);
        imageView.setFitWidth(120);
        imageView1.setFitWidth(120);


        compareGrid.add(imageView,3,3);
        compareGrid.add(imageView1,6,3);

        Text goodAvRate=new Text(String.valueOf(good.getAverageRate()));
        Text goodAvRate1=new Text(String.valueOf(good1.getAverageRate()));
        compareGrid.add(new Text("Average Rate : ") , 2,5);
        compareGrid.add(goodAvRate,3,5);
        compareGrid.add(goodAvRate1,4,5);

        Text goodCharac=new Text(String.valueOf(good.getCharacteristics()));
        Text goodCharac1=new Text(String.valueOf(good1.getCharacteristics()));
        compareGrid.add(new Text("Characteristic : ") , 2,6);
        compareGrid.add(goodCharac,3,6);
        compareGrid.add(goodCharac1,4,6);

        Text goodOffPercent=new Text(String.valueOf(good.getOffpercent()));
        Text goodOffPercent1=new Text(String.valueOf(good1.getOffpercent()));
        compareGrid.add(new Text("off percent : ") , 2,7);
        compareGrid.add(goodOffPercent,3,7);
        compareGrid.add(goodOffPercent1,4,7);



    }
}