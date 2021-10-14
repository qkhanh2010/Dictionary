import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class Controller implements Initializable{
    @FXML
    TextField word_target;

    @FXML
    TextArea word_explain;

    @FXML
    ListView listView;

    @FXML
    Button buttonSearch;

    @FXML
    Button buttonAdd;

    @FXML
    Button buttonDelete;

    @FXML
    Button buttonChange;

    
    public static List<Word> listWord = new ArrayList<>();
    List<Word> wordSearch= new ArrayList<>();
    DictionaryManagement dictionarymanagement= new DictionaryManagement();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        listWord = dictionarymanagement.insertFromFile();
    }
    
    public void buttonSearch_click() {
        wordSearch=dictionarymanagement.searchWord(word_target.getText(), listWord);
        listView.getItems().clear();
        for(int i=0;i<wordSearch.size();i++)
            listView.getItems().add(wordSearch.get(i).getWord_target());
    }

    public void listView_click() {
        try {
            String target = wordSearch.get(listView.getSelectionModel().getSelectedIndex()).getWord_target();
            String explain = wordSearch.get(listView.getSelectionModel().getSelectedIndex()).getWord_explain();
            word_target.setText(dictionarymanagement.getString(target));
            word_explain.setText(dictionarymanagement.getString(explain));
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void autoSearch() {
        wordSearch=dictionarymanagement.searchWord(word_target.getText(), listWord);
        listView.getItems().clear();
        for(int i=0;i<wordSearch.size();i++)
            listView.getItems().add(wordSearch.get(i).getWord_target());
        if(listWord.size()>0)
            word_explain.setText(dictionarymanagement.getString(""));
    }

    static Stage stage=new Stage();
    Alert alert = new Alert(AlertType.INFORMATION);

    public void buttonAdd_click() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("add.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("Thêm từ");
        stage.setScene(scene);
        stage.show();
    }

    public void buttonDelete_click() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("delete.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("Xóa từ");
        stage.setScene(scene);
        stage.show();
    }

    Word newWord = new Word();

    public void buttonChange_click() throws IOException{
        dictionarymanagement.deleteWord(word_target.getText(), listWord);
        newWord=dictionarymanagement.addWord(word_target.getText(), word_explain.getText());
        listWord.add(newWord);
        dictionarymanagement.ExportToFile(listWord);
        alert.setContentText("Đã sửa từ !!!");
        alert.showAndWait();
    }

}