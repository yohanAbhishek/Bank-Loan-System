package loanSystem.Controllers;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import loanSystem.Implementations.ConfirmApplication;
import loanSystem.Implementations.Customer;
import loanSystem.Implementations.Utilities;


public class ConfirmController {
    @FXML private ChoiceBox<String> selectPriority1;
    @FXML private TableView<Customer> confirmApplicationTableView;
    @FXML private TableColumn<Customer, String> confirmAccNoColumn, confirmCollateralColumn, confirmReasonColumn, loanAmountColumn;
    @FXML private AnchorPane priorityAnchor1,passwordAnchor1;
    @FXML private Label errorMessage;

    @FXML public void handleLoadButton1(){
        selectPriority1.getItems().setAll("Normal", "High", "Inner");
    }

    @FXML public void handleConfirmNextButton() {
        ConfirmApplication confirm = new ConfirmApplication();
        try {
            confirm.getPriorityFromChoiceBox(selectPriority1.getValue());
            if (selectPriority1.getValue().equals("Inner")){
                Pane view = Utilities.getPage("../Scenes/passwordForConfirm.fxml");
                priorityAnchor1.getChildren().setAll(view);
            } else {
                Pane view = Utilities.getPage("../Scenes/confirmApplication.fxml");
                priorityAnchor1.getChildren().setAll(view);
            }
        } catch (NullPointerException e){
            errorMessage.setText("Please select an option!");
        }
    }

    @FXML public void handleLogInButtonForConfirm(){
        Pane view = Utilities.getPage("../Scenes/confirmApplication.fxml");
        passwordAnchor1.getChildren().setAll(view);
    }

    @FXML public void loadConfirmApplicationDataToTable() {
        confirmAccNoColumn.setCellValueFactory(new PropertyValueFactory<>("accountNumber"));
        confirmCollateralColumn.setCellValueFactory(new PropertyValueFactory<>("collateralDescription"));
        confirmReasonColumn.setCellValueFactory(new PropertyValueFactory<>("reasonForLoan"));
        loanAmountColumn.setCellValueFactory(new PropertyValueFactory<>("loanAmount"));

        ObservableList<Customer> customers = confirmApplicationTableView.getItems();
        ConfirmApplication confirm = new ConfirmApplication();
        try {
            String list = confirm.loadFrontKeyIntoTable();
            Customer customer = new Customer(list.split(",")[1], list.split(",")[5], list.split(",")[4], Integer.parseInt(list.split(",")[6]));
            customers.setAll(customer);
        } catch (ArrayIndexOutOfBoundsException e){
            errorMessage.setText("No applications to confirm");
        }
    }

    @FXML public void handleConfirmApplicationButton() {
        ConfirmApplication confirm = new ConfirmApplication();
        try {
            Utilities.deleteRowFromTable(confirmApplicationTableView);
            confirm.dequePriorityFromGetter();
            errorMessage.setText("");
        } catch (ArrayIndexOutOfBoundsException e){
            errorMessage.setText("Please select an application to confirm!");
        }
    }
}
