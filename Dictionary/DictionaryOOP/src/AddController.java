import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class AddController implements Initializable{

    @FXML
    TextField target;

    @FXML
    TextArea explain;

    @FXML
    Button buttonOk;

    @FXML
    Button buttonCancel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
    
    DictionaryManagement dictionarymanagement = new DictionaryManagement();
    Word newWord= new Word();
    Alert alert = new Alert(AlertType.INFORMATION);

    public void buttonOk_click() {
        newWord=dictionarymanagement.addWord(target.getText(), explain.getText());
        Controller.listWord.add(newWord);
        dictionarymanagement.ExportToFile(Controller.listWord);
        alert.setContentText("Đã thêm từ mới!!!");
        alert.showAndWait();
        Controller.stage.close();
    }
    
    public void buttonCancel_click() {
        Controller.stage.close();
    }
}
