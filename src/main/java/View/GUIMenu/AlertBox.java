package View.GUIMenu;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Reflection;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AlertBox {
    public static void display(String title , String message){
        Stage window = new Stage();
        window.setTitle(title);

        window.initModality(Modality.APPLICATION_MODAL);
        window.setMinWidth(250);

        Label label = new Label();
        label.setText(message);
        Button closeButton = new Button("close button");
        closeButton.setOnAction(e -> window.close());

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label , closeButton);
        layout.setAlignment(Pos.CENTER);

        Reflection r = new Reflection();
        r.setFraction(0.7f);
        layout.setEffect(r);

        DropShadow dropShadow = new DropShadow();
        dropShadow.setOffsetX(5);
        dropShadow.setOffsetY(5);

        closeButton.setStyle("-fx-background-radius: 30, 30, 29, 28;\n" +
                "    -fx-padding: 3px 10px 3px 10px;\n" +
                "    -fx-background-color: linear-gradient(orange, orangered );");
        label.setStyle("-fx-fill:  linear-gradient(orange , orangered);");

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
    }
}
