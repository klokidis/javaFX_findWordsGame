package com.example.dimitrisklokidisjavafx210049;

import javafx.collections.ObservableList;

import javafx.scene.control.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;


import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.*;


public class controller implements Initializable  {

        @FXML
        private Label L1;

        @FXML
        private Label L2;

        @FXML
        private Label L3;

        @FXML
        private Label L4;

        @FXML
        private Label L5;

        @FXML
        private Label L6;

        @FXML
        private Label L7;

        @FXML
        private Label L8;

        @FXML
        private Label L9;

        @FXML
        private TableView<words> TableOfWords;

        @FXML
        private TableColumn<words,String> tableColum;

        @FXML
        private TextField textField;

        @FXML
        private Label textOut;
        private char[] taken = new char[9];
        public char[] taken2 = new char[9];
        public List<String> inputtedWords = new ArrayList<>();


        //right screen
        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {
            tableColum.setCellValueFactory(new PropertyValueFactory<>("word"));
            setLetters();
        }

        @FXML
        public void submitButton() {
          String word =textField.getText().trim();
          words oneWord = new words(word);
          ObservableList<words> someWords = TableOfWords.getItems();
          if(checkWordInFile(word) && !wordIsAlreadyIn(word) && !word.isEmpty() && checkWordCharsIfExist(word) && word.length()>1 && checkRepeat(word)){
              someWords.add(oneWord);
              TableOfWords.setItems(someWords);
              textField.clear();
              textOut.setText("Well done!!:)");
              textOut.setTextFill(Color.GREEN);
              inputtedWords.add(word);
          }else {
              textOut.setText("invalid input :(");
              textOut.setTextFill(Color.RED);
          }

        }

        //left screen
        public void setLetters(){
            resetArray(taken);
            L1.setText(String.valueOf(generateRandomLetters()));
            setTaken(L1.getText().charAt(0),0);
            L2.setText(String.valueOf(generateRandomLetters()));
            setTaken(L2.getText().charAt(0),1);
            L3.setText(String.valueOf(generateRandomLetters()));
            setTaken(L3.getText().charAt(0),2);
            L4.setText(String.valueOf(generateRandomLetters()));
            setTaken(L4.getText().charAt(0),3);
            L5.setText(String.valueOf(generateRandomLetters()));
            setTaken(L5.getText().charAt(0),4);
            L6.setText(String.valueOf(generateRandomLetters()));
            setTaken(L6.getText().charAt(0),5);
            L7.setText(String.valueOf(generateRandomLetters()));
            setTaken(L7.getText().charAt(0),6);
            L8.setText(String.valueOf(generateRandomLetters()));
            setTaken(L8.getText().charAt(0),7);
            L9.setText(String.valueOf(generateRandomLetters()));
            setTaken(L9.getText().charAt(0),8);

            TableOfWords.getItems().clear();
            textField.clear();
            textOut.setTextFill(Color.WHITE);
            if (inputtedWords != null) {
                inputtedWords.clear();
            }
            textOut.setText("lets start finding words!:)");
        }

        public void resetArray(char[] A){
            Arrays.fill(A,' ' );
        }
        public char generateRandomLetters() {
            char letter;
            do {
                    Random rand = new Random();
                    letter = (char) (rand.nextInt(26) + 'A');
            } while(checkExistence(letter,taken));
            return letter;
        }

        public boolean checkExistence(char aChar, char[] taken){
              boolean b = false;
              for (char c : taken) {
                      if (Character.valueOf(aChar).equals(c)) {
                              b = true;
                              break;
                      }
              }
              return b;
        }

        public boolean checkWordInFile(String word){
           File file = new File("src/main/java/com/example/dimitrisklokidisjavafx210049/engWords.txt");
           try {
              Scanner scanner = new Scanner(file);
              while (scanner.hasNextLine()) {
                  String lineWord = scanner.nextLine();
                  if (lineWord.toLowerCase().contains(word.toLowerCase()) && lineWord.matches(".*\\b" + word.toLowerCase() + "\\b.*")) {
                    return true;
                  }
              }
           } catch (FileNotFoundException e) {
            e.printStackTrace();
           }
        return false;
        }

        public boolean checkWordCharsIfExist(String word){
            for (int i = 0; i < word.length(); i++) {
               char c = word.charAt(i);
                boolean found = false;
                for (int j = 0; j < taken2.length; j++) {
                     if (c == taken2[j]) {
                        found = true;
                        break;
                     }
                }
                if (!found) {
                   return false;
                }
            }
         return true;
        }

    public boolean wordIsAlreadyIn(String word) {
        for (int i = 0; i < inputtedWords.size(); i++) {
            for (String str : inputtedWords) {
                if (str.equals(word)) {
                    return true;
                }
            }
        }
        return false;
    }
    public boolean checkRepeat(String word){
            for (int i = 0; i < word.length(); i++) {
                for (int j = i + 1; j < word.length(); j++) {
                    if (word.charAt(i) == word.charAt(j)) {
                        return false; // Found a duplicate character
                    }
                }
            }
            return true; // No duplicates found
        }

    public void setTaken(char aChar,int i){
            taken[i] = aChar;
            taken2[i] = Character.toLowerCase(aChar);
    }

}
