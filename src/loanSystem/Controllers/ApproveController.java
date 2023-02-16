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
import loanSystem.Implementations.ApproveApplication;
import loanSystem.Implementations.Customer;
import loanSystem.Implementations.Utilities;

public class ApproveController {
    @FXML private Label errorMessage;
    @FXML private ChoiceBox<String> selectPriority;
    @FXML private AnchorPane priorityAnchor, passwordAnchor2;
    @FXML private TableView<Customer> approveApplicationTableView;
    @FXML private TableColumn<Customer, String> approveAccNoColumn, approveCollateralColumn, approveReasonColumn, loanAmountColumn;


    @FXML public void handleLoadButton2(){
        selectPriority.getItems().setAll("Normal", "High", "Inner");
    }

    @FXML public void handleApproveNextButton(){
        ApproveApplication approve = new ApproveApplication();
        try {
            approve.getPriorityFromChoiceBox(selectPriority.getValue());
            if (selectPriority.getValue().equals("Inner")){
                Pane view = Utilities.getPage("../Scenes/passwordForApproval.fxml");
                priorityAnchor.getChildren().setAll(view);
            } else {
                Pane view = Utilities.getPage("../Scenes/approveApplication.fxml"); // if priority of customer is normal grant access without password
                priorityAnchor.getChildren().setAll(view);
            }
        } catch (NullPointerException e){
            errorMessage.setText("Please select an option!");
        }
    }

    @FXML public void handleLogInButtonForApproval(){
            Pane view = Utilities.getPage("../Scenes/approveApplication.fxml");
            passwordAnchor2.getChildren().setAll(view);
    }

    @FXML public void loadApproveApplicationDataToTable() {
        approveAccNoColumn.setCellValueFactory(new PropertyValueFactory<>("accountNumber"));
        approveCollateralColumn.setCellValueFactory(new PropertyValueFactory<>("collateralDescription"));
        approveReasonColumn.setCellValueFactory(new PropertyValueFactory<>("reasonForLoan"));
        loanAmountColumn.setCellValueFactory(new PropertyValueFactory<>("loanAmount"));
        ObservableList<Customer> customers = approveApplicationTableView.getItems();
        ApproveApplication approve = new ApproveApplication();
        try {
            String list = approve.loadFrontKey();
            Customer customer = new Customer(list.split(",")[1], list.split(",")[5], list.split(",")[4], Integer.parseInt(list.split(",")[6]));
            customers.setAll(customer);
        } catch (NullPointerException e){
            errorMessage.setText("No applications to approve");
        }
    }

    @FXML public void handleApproveApplicationButton(){
        try {
            ApproveApplication approve = new ApproveApplication();
            Utilities.deleteRowFromTable(approveApplicationTableView);
            approve.dequePriority();
        } catch (ArrayIndexOutOfBoundsException e){
            errorMessage.setText("Please select an application to approve! ");
        }
    }
}
