package loanSystem.Implementations;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class Utilities {
    public static Pane getPage(String fileName){
        Pane view = new Pane();
        try {
            URL fileUrl = Utilities.class.getResource(fileName);
            if(fileUrl == null){
                throw new java.io.FileNotFoundException("Fxml file can't be found");
            } new FXMLLoader();
            view = FXMLLoader.load(fileUrl);
        } catch (Exception e) {
            System.out.println(fileName+" not found.");
        } return view;
    }

    public static ArrayList<String> customersIntoArrayFromTextFIle(){
        try {
            Scanner scanner = new Scanner(new File("src/loanSystem/Assets/accounts.txt"));
            ArrayList<String> loanApplications = new ArrayList<>();
            while (scanner.hasNextLine()){
                loanApplications.add(scanner.nextLine());
            }
            scanner.close();
            return loanApplications;
        } catch (FileNotFoundException e){
            System.out.println(e+"");
        } return null;
    }

    public static void deleteRowFromTable(TableView<Customer> tableView){
        int getRowID = tableView.getSelectionModel().getSelectedIndex();
        tableView.getItems().remove(getRowID);
    }
}
