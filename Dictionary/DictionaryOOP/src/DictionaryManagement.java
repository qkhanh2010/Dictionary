import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DictionaryManagement {
    Dictionary dictionary = new Dictionary();

    public Dictionary getDictionary() {
        return dictionary;
    }

    public DictionaryManagement() {

    }

    File file = new File("C:/Tài liệu/Java/Dictionary/DictionaryOOP/dictionaries.txt");

    /**
     * doc du lieu tu file
     */
    public List<Word> insertFromFile() {
        List<Word> list = new ArrayList<>();
        
        try {
            Scanner sc=new Scanner(new FileInputStream(file));
            String line;
            while(sc.hasNextLine()) {
                line=sc.nextLine();
                String[] text=line.split("\\|");
                list.add(new Word(text[0],text[1]));
            }
        } catch(FileNotFoundException ex){
            ex.printStackTrace();
        }
        return list;
    }

    public String getString(String text) {
        String[] arr = text.split("\\-");
        String rs = arr[0];
        for (int i = 1; i < arr.length; i++) {
            rs += "\n-" + arr[i];
        }
        return rs;
    }
    
    /**
     * search
     */
    public List<Word> searchWord(String word_target, List<Word> list){
        List<Word> wordFind = new ArrayList<>();
        for(int i=0;i<list.size();i++){
            if(list.get(i).getWord_target().startsWith(word_target))
                wordFind.add(list.get(i));
        }
        return wordFind;
    }

    /**
     * add
     */
    public Word addWord(String word_target,String word_explain){
        Word word=new Word(word_target,word_explain);
        return word;
    }

    /**
     * delete
     */
    public void deleteWord(String word_target,List<Word> list){
        for(int i=0;i<list.size();i++){
            if(word_target.equals(list.get(i).getWord_target()))
                list.remove(i);
        }
    }

    /**
     * export to file
     */
    public void ExportToFile(List<Word> list){
        try {
            String word_target;
            String word_explain;
            FileWriter f = new FileWriter(file);
            for(int i=0;i<list.size();i++){
                word_target= list.get(i).getWord_target();
                f.write(word_target);
                f.write("|");
                word_explain=list.get(i).getWord_explain();
                f.write(word_explain);
                f.write("\n");
            }
            f.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }


}
