package loanSystem.Implementations;

class QueueNode {
    int value;
    QueueNode next;

    public QueueNode(int key) {
        this.value = key;
        this.next = null;
    }
}

class Queue {
    QueueNode front, rear;
    public Queue() {
        this.front = this.rear = null;
    }

    void enqueue(int key){
        QueueNode queueNode = new QueueNode(key);
        if(this.rear == null){
            this.front = this.rear = queueNode;
            return;
        }
        this.rear.next = queueNode;
        this.rear = queueNode;
    }

    void dequeue() {
        if(this.front == null){
            return;
        }
        this.front = this.front.next;
        if(this.front == null)
            this.rear = null;
    }
}
