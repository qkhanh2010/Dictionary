import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class DeleteController implements Initializable{
    @FXML
    TextField target;

    @FXML
    Button buttonOk;

    @FXML
    Button buttonCancel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
    
    DictionaryManagement dictionarymanagement = new DictionaryManagement();
    Alert alert = new Alert(AlertType.INFORMATION);

    public void buttonOk_click() {
        dictionarymanagement.deleteWord(target.getText(), Controller.listWord);
        dictionarymanagement.ExportToFile(Controller.listWord);
        alert.setContentText("Đã xóa từ !!!");
        alert.showAndWait();
        Controller.stage.close();
    }

    public void buttonCancel_click() {
        Controller.stage.close();
    }

}
