package ui;

import java.io.IOException;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;


public class CustomCellView extends HBox {
    @FXML
    HBox hboxRoot;

    @FXML
    ImageView avatar;
    @FXML
    Label name;
    @FXML
    Label hastag;
//    @FXML
//    Button call, video;

    public CustomCellView()
    {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("CellView.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    HBox getHBoxRoot()
    {
        return hboxRoot;
    }

    void setName(String text)
    {
        name.setText(text);
    }

    void setHastag(String text)
    {
        hastag.setText(text);
    }

    void setAvatar(String ImageFileName)
    {
        Image image = new Image(HelloApplication.class.getResourceAsStream(ImageFileName));
        avatar.setImage(image);
    }

//    void setCallAction(EventHandler actionEvent)
//    {
//        call.setOnAction(actionEvent);
//    }
//
//    void setVideoAction(EventHandler actionEvent)
//    {
//        video.setOnAction(actionEvent);
//    }
}