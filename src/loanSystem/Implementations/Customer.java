package loanSystem.Implementations;

import javafx.scene.control.Label;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Customer {
    private String priorityType;
    private String NIC;
    private String accountNumber;
    private String typeOfLoan;
    private final String reasonForLoan;
    private final String collateralDescription;
    private final int loanAmount;

    public Customer(String priorityType, String NIC,String typeOfLoan, String reasonForLoan, String collateralDescription,int loanAmount) {
        this.loanAmount = loanAmount;
        this.priorityType = priorityType;
        this.NIC = NIC;
        this.typeOfLoan = typeOfLoan;
        this.reasonForLoan = reasonForLoan;
        this.collateralDescription = collateralDescription;
    }

    // overload customer constructor
    public Customer(String accountNumber, String collateralDescription, String reasonForLoan, int loanAmount){
        this.accountNumber = accountNumber;
        this.collateralDescription = collateralDescription;
        this.reasonForLoan = reasonForLoan;
        this.loanAmount = loanAmount;
    }

    // Getters
    public String getPriorityType() {
        return priorityType;
    }

    public String getNIC() {
        return NIC;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getTypeOfLoan() {
        return typeOfLoan;
    }

    public String getReasonForLoan() {
        return reasonForLoan;
    }

    public String getCollateralDescription() {
        return collateralDescription;
    }

    public int getLoanAmount() {
        return loanAmount;
    }

    public void addNewCustomerToTextFile(String nic, String reasonForLoan, String collateralDescription, String priorityType, String loanType, int loanAmount, Label error){
        Customer customer = new Customer(priorityType, nic, loanType, reasonForLoan, collateralDescription, loanAmount);
        try {
            File file = new File("src/loanSystem/Assets/accounts.txt");
            FileWriter fileWriter = new FileWriter(file, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            if(customer.getPriorityType().equalsIgnoreCase("normal")||customer.getPriorityType().equalsIgnoreCase("high")||customer.getPriorityType().equalsIgnoreCase("inner")){
                bufferedWriter.write(customer.getNIC()+","+ generateAccountNumber()+","+ customer.getPriorityType()+","+ customer.getTypeOfLoan()+","+ customer.getReasonForLoan()+","+ customer.getCollateralDescription()+","+customer.getLoanAmount()+"\n");
            } else {
                error.setText("Please enter normal, high or inner for priority!");
            }
            bufferedWriter.close();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int generateAccountNumber(){
        try {
            Scanner scanner = new Scanner(new File("src/loanSystem/Assets/accounts.txt"));
            ArrayList<String> loanApplications = new ArrayList<>();
            while (scanner.hasNextLine()){
                loanApplications.add(scanner.nextLine());
            }
            scanner.close();
            String latestNumber = loanApplications.get(loanApplications.size()-1).split(",")[1];
            return Integer.parseInt(latestNumber)+1;
        } catch (FileNotFoundException e){
            System.out.println("Error: "+e);
        } catch (ArrayIndexOutOfBoundsException e){
            return 1;
        }
        return -1;
    }
}