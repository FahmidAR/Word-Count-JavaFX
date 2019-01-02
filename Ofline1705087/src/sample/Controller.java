package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLOutput;

import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.scene.control.ListView;

import sample.TextWordProperty;


public class Controller {
    @FXML
    private Button selectFile;
    @FXML
    private ListView listView;
    @FXML
    private TableView<TextWordProperty> table;
    @FXML
    private TableColumn wordColumn;

    @FXML
    private TableColumn freqColumn;

    private ObservableList<TextWordProperty> data = FXCollections.observableArrayList();


    public void ButtonAction(ActionEvent event)
    {

        // File chooser Handling
        FileChooser fc=new FileChooser();
        //fc.setInitialDirectory(new File("C:\\Users\\FAHMID\\Desktop\\offline"));
        fc.getExtensionFilters().addAll(
                new ExtensionFilter("Text Files","*.txt")
            );
        File selectedFile=fc.showOpenDialog(null);

        if(selectedFile!=null)
        {
            listView.getItems().add(selectedFile.getName());
        }
        else
        {
            System.out.println("File is not selected !!!");
        }


        //adding

        wordColumn.setCellValueFactory(new PropertyValueFactory<>("word"));
        freqColumn.setCellValueFactory(new PropertyValueFactory<>("count"));


        try
        {
            FileInputStream input = new FileInputStream(selectedFile);
            byte[] bufByte = new byte[input.available()];
            input.read(bufByte);
            String buffer = new String(bufByte);
            buffer = buffer.replaceAll("\n|\t.", " ");
            buffer=buffer.replaceAll("\\."," ");
            buffer=buffer.replaceAll(","," ");
            buffer=buffer.replaceAll("\\?"," ");
            buffer=buffer.replaceAll("\\!"," ");
            buffer = buffer.trim();
            String[] word = buffer.split(" ");

            for(int i=0;i<word.length;i++)
            {
               word[i]= word[i].trim();
               //System.out.println("word = "+word[i]+"  length = "+word[i].length());
            }

            for(int i=0;i<word.length;i++)
            {
                //System.out.println(i+" "+word[i]);
                if(word[i] != null && !word[i].isEmpty()) {


                        //System.out.println(word[i]);
                        int count = 1;

                        for (int j = i + 1; j < word.length; j++) {
                            if (word[i].equalsIgnoreCase(word[j])) {
                                word[j] = "";
                                count++;
                            }

                        }

                        data.add(new TextWordProperty(word[i], count));

                }
            }


        }
        catch (Exception e)
        {
            System.out.println("Failed to operate !!! ");
        }

        table.setItems(data);

    }

}