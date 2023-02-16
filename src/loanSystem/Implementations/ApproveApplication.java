package loanSystem.Implementations;

import java.util.ArrayList;

public class ApproveApplication {

    private static String priority;
    static ArrayList<String> customerList = Utilities.customersIntoArrayFromTextFIle();

    static Queue normalApprovalList = new Queue();
    static Queue highApprovalList = new Queue();
    static Queue innerApprovalList = new Queue();

    public void getPriorityFromChoiceBox(String priorityType) {
        priority = priorityType;
    }

    public String initializePriority(){
        if (priority.equals("Normal")){
            return String.valueOf(normalApprovalList.front.value);
        } else if (priority.equals("High")){
            return String.valueOf(highApprovalList.front.value);
        } else {
            return String.valueOf(innerApprovalList.front.value);
        }
    }

    public String loadFrontKey() {
        for (String s : customerList) {
            if (s.split(",")[1].equals(initializePriority())) {
                return s;
            }
        }
        return "-1";
    }

    public void transferConfirmedApplicationToApprove(int key, String priority){
        if (priority.equalsIgnoreCase("normal")) {
            normalApprovalList.enqueue(key);
        } else if (priority.equalsIgnoreCase("high")) {
            highApprovalList.enqueue(key);
        } else if (priority.equalsIgnoreCase("inner")) {
            innerApprovalList.enqueue(key);
        }
    }

    public void dequePriority() {
        switch (priority) {
            case "Normal":
                normalApprovalList.dequeue();
            case "High":
                highApprovalList.dequeue();
            case "Inner":
                innerApprovalList.dequeue();
        }
    }
}
