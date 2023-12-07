package ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Callback;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.*;

public class HelloController implements Initializable {
    @FXML
    private AnchorPane MessageView;

    @FXML
    private ListView<String> contactList;
    private ArrayList<String> myContact = new ArrayList<String>();

    @FXML
    private TextField SearchBar;

    private AnchorPane myMessage;
    private MyMessageController myMessageController;

    @FXML
    void SearchInput(KeyEvent event) throws IOException {
        if (event.getSource() == SearchBar) {
            String searchText = SearchBar.getText();
            ShowList(searchText);
//            if (event.getCode() == KeyCode.ENTER) {
//                // làm gì đó khi Enter
//            }
        }
    }

    @FXML
    void SelectItem(MouseEvent event) throws IOException {
        String tmp = contactList.getSelectionModel().getSelectedItem();
        // thao tác với searchText
        // Bật tab mới
        if (tmp != null || !tmp.equals("")) {
            MessageView.getChildren().clear();
            MessageView.getChildren().add(myMessage);
        } else {
            MessageView.getChildren().clear();
        }
    }

    public void ShowList(String target) {
        myContact.clear();
        ArrayList<String> namefromFile = new ArrayList<String>();
        readFile(namefromFile,"src/main/resources/ui/contactNameList.txt");

        if (target.isEmpty()) {
            myContact.addAll(namefromFile);
        } else {
            myContact.clear();
            ArrayList<String> temp = Trie.search(target);
            for (int i = namefromFile.size() - 1; i >= 0; i--) {
                String name = namefromFile.get(i);
                String check = name.substring(0, Math.min(name.length(), target.length()));
                if (check.compareToIgnoreCase(target) == 0) {
                    myContact.add(namefromFile.get(i));
                    for (int j = 0; j < temp.size(); j++) {
                        if (temp.get(j).equals(namefromFile.get(i))) {
                            temp.remove(j); break;
                        }
                    }
                }
            }
            myContact.addAll(temp);
        }

        ObservableList<String> items = FXCollections.observableArrayList(myContact);
        Collections.sort(items);
        contactList.setItems(items);

        // set avatar
        contactList.setCellFactory(param -> new ListCell<String>() {
            private ImageView imageView = new ImageView();

            @Override
            public void updateItem(String name, boolean empty) {
                super.updateItem(name, empty);
                if (empty) {
                    setText(null);
                    setGraphic(null);
                } else {
                    Image image;
                    try {
                        image = new Image(HelloApplication.class.getResourceAsStream(name + ".png"));
                    } catch (Exception e) {
                        image = new Image(HelloApplication.class.getResourceAsStream("default.png"));
                    }
                    Image result = getRoundedImage(image);

                    imageView.setImage(result);
                    imageView.setFitWidth(50);
                    imageView.setFitHeight(50);

                    setGraphic(imageView);
                    setText(name);
                }
            }
        });
    }

    // Khởi tạo
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Khởi tạo MessageView
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("myMessage.fxml"));
            myMessage = fxmlLoader.load();
            myMessageController = fxmlLoader.getController();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ShowList("");
    }

    private Image getRoundedImage(Image image) {
        Circle clip = new Circle(image.getWidth() / 2, image.getHeight() / 2, image.getWidth() / 2);
        ImageView imageView = new ImageView(image);
        imageView.setClip(clip);
        SnapshotParameters parameters = new SnapshotParameters();
        parameters.setFill(Color.TRANSPARENT);
        return imageView.snapshot(parameters, null);
    }

    //hàm trả về arraylist string chứa các dòng đọc từ một file .txt
    private void readFile(ArrayList<String> name, String path) {
        try {
            FileReader fr = new FileReader(path);
            BufferedReader br = new BufferedReader(fr);
            String line = br.readLine();
            while (line != null) {
                name.add(line);
                line = br.readLine();
            }
            br.close();
        } catch (IOException e) {
            System.out.println("Cannot find the file!\n");
        } catch (Exception e) {
            System.out.println("Something went wrong: " + e);
        }

    }
}
