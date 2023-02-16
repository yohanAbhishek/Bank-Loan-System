package loanSystem.Implementations;

import java.util.ArrayList;
import java.util.Objects;

public class ConfirmApplication {
    private static String priority;
    static ArrayList<String> customerList = Utilities.customersIntoArrayFromTextFIle();

    static Queue normalCustomers = new Queue();
    public void sortNormalCustomerIntoQueue() {
        for (String s : customerList) {
            if (s.split(",")[2].equals("normal")) {
                normalCustomers.enqueue(Integer.parseInt(s.split(",")[1]));
            }
        }
    }

    static Queue highPriorityCustomers = new Queue();
    public void sortHighCustomerIntoQueue() {
        for (String application : customerList) {
            if (Objects.equals(application.split(",")[2], "high")){
                highPriorityCustomers.enqueue(Integer.parseInt(application.split(",")[1]));
            }
        }
    }

    static Queue innerCircleCustomers = new Queue();
    public void sortInnerCustomerIntoQueue() {
        for (String application : customerList) {
            if (Objects.equals(application.split(",")[2], "inner")){
                innerCircleCustomers.enqueue(Integer.parseInt(application.split(",")[1]));
            }
        }
    }

    public void getPriorityFromChoiceBox(String priorityType) {
        priority = priorityType;
    }

    public String initializePriorityFromGetter(){
        ConfirmApplication confirm = new ConfirmApplication();
        confirm.sortInnerCustomerIntoQueue();
        confirm.sortNormalCustomerIntoQueue();
        confirm.sortHighCustomerIntoQueue();
        if (priority.equals("Normal")){
            return String.valueOf(normalCustomers.front.value);
        } else if (priority.equals("High")){
            return String.valueOf(highPriorityCustomers.front.value);
        } else {
            return String.valueOf(innerCircleCustomers.front.value);
        }
    }

    public String loadFrontKeyIntoTable(){
        ApproveApplication approve = new ApproveApplication();
        for (String s : customerList) {
            if (s.split(",")[1].equals(initializePriorityFromGetter())) {
                approve.transferConfirmedApplicationToApprove(Integer.parseInt(s.split(",")[1]), s.split(",")[2]);
                return s;
            }
        }
        return "-1";
    }

    public void dequePriorityFromGetter() {
        switch (priority) {
            case "Normal":
                normalCustomers.dequeue();
            case "High":
                highPriorityCustomers.dequeue();
            case "Inner":
                innerCircleCustomers.dequeue();
        }
    }
}
