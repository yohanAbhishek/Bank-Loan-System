# Bank-Loan-System
This a bank loan system to process loan applications in an order

I was presented with a problem to create an application that can process loan applications in order. Following was the criteria to this applicatiion:
In a bank, the customers are categorized into two categories.
1) Normal customers 
2) High Priority customers. 

Inner circle employees are experienced employees who handle Inner Circle clients. Internet Banking Services provided by this bank includes Application of Loans. The customer will submit a Loan application. The Loan module can handle any number of Loan
Applications. The application will contain the (1)NIC of the applicant, (2)account number of the applicant, (3)type of Loan, (4)reason for Loan application, and a (5)description of the collateral/s. 

The loan applications will be processed in order and must be approved by an Employee. The approved application will be sent to manager in the order of approval for confirmation. If it is a loan application by a High Priority customer, it will be kept separate list and all Inner Circle loans will be processed in order. The loan application has to be approved by an Inner Circle
Employee. The approved application will be sent to Inner Circle Manager of the Main Branch in the order of approval for confirmation.

Using the above information it was decided that the most suitable data structure for this scenario is a Queue of a linked list, because there can be any number of applications(Linked list), and applications are processed in order(Queue), as the queue method follows the first in first out(FIFO) principle.

A csv file was used to store the information. I have implemented the GUI using JavaFX. Since it is a banking system, a login was implemented as below;

![image](https://user-images.githubusercontent.com/100549603/219416623-c0cca107-5c4e-45b4-bbc0-fe9dd297fa71.png)

Once the user is logged in, the user will be displayed will 3 options to manipulate the loan applications as "NEW", "CONFIRM" and "APPROVE".
![image](https://user-images.githubusercontent.com/100549603/219417125-5b0558b5-3076-4d71-87cc-360e5b7eea64.png)
