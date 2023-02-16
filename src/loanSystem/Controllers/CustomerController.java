package loanSystem.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import loanSystem.Implementations.Customer;
import loanSystem.Implementations.Utilities;

public class CustomerController {
    @FXML
    private BorderPane loanSysBorderPane;
    @FXML
    private TextField nic, priorityType, loanTypeField, loanReasonField, collateralDescField, amountField;
    @FXML
    private Label errorMessage, headerLabel;

    @FXML public void handleNewApplicationButton(){
        Pane view = Utilities.getPage("../Scenes/newApplication.fxml");
        loanSysBorderPane.setCenter(view);
        headerLabel.setText("NEW APPLICATION");
    }

    @FXML public void handleSubmitButton() {
        String nicText = nic.getText();
        String priorityTypeText = priorityType.getText();
        String loanTypeText = loanTypeField.getText();
        String loanReasonText = loanReasonField.getText();
        String collateralDescText = collateralDescField.getText();
        int loanAmountText;
        try {
            loanAmountText = Integer.parseInt(amountField.getText());
            if (validateForm()){
                Customer customer = new Customer(priorityTypeText, nicText, loanTypeText, loanReasonText, collateralDescText,loanAmountText);
                customer.addNewCustomerToTextFile(nicText, loanReasonText, collateralDescText, priorityTypeText, loanTypeText, loanAmountText, errorMessage);
                clearFields();
            }
        } catch (NumberFormatException e) {
            errorMessage.setText("Please enter valid amount!");
        }
    }

    public void clearFields(){
        nic.setText(null);
        priorityType.setText(null);
        loanTypeField.setText(null);
        loanReasonField.setText(null);
        collateralDescField.setText(null);
        amountField.setText(null);
        errorMessage.setText(null);
    }

    @FXML
    public void handleConfirmButton(){
        Pane view = Utilities.getPage("../Scenes/selectPriorityForConfirm.fxml");
        loanSysBorderPane.setCenter(view);
        headerLabel.setText("CONFIRM APPLICATION");
    }

    @FXML
    public void handleApproveButton(){
        Pane view = Utilities.getPage("../Scenes/selectPriorityForApproval.fxml");
        loanSysBorderPane.setCenter(view);
        headerLabel.setText("APPROVE APPLICATION");
    }

    public boolean validateForm(){
        if (nic.getText()==null){
            errorMessage.setText("Please enter ID!");
            return false;
        } if (priorityType.getText()==null){
            errorMessage.setText("Please enter priority type!");
            return false;
        } if (loanTypeField.getText()==null ){
            errorMessage.setText("Please enter loan type!");
            return false;
        } if (loanReasonField.getText()==null ){
            errorMessage.setText("Please enter loan reason!");
            return false;
        } if (amountField.getText()==null ){
            errorMessage.setText("Please enter amount!");
            return false;
        } if (collateralDescField.getText()==null ){
            errorMessage.setText("Please enter collateral!");
            return false;
        } if (!(priorityType.getText().equalsIgnoreCase("high") || priorityType.getText().equalsIgnoreCase("inner") || priorityType.getText().equalsIgnoreCase("normal"))){
            errorMessage.setText("Please enter high, inner or normal for priority type!");
            return false;
        }return true;
    }
}
