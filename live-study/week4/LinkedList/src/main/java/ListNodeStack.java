public class ListNodeStack {
    private ListNode head;

    //head
    ListNodeStack(){
        head = new ListNode();
    }

    public void push(int data){
        int size = head.size(head);
        ListNode newNode = new ListNode(data);
        head.add(head, newNode, size+1);
    }

    public int pop(){
        int size = head.size(head);
        try{
            return head.remove(head, size).getData();
        }catch (NullPointerException e){
            return -1;
        }
    }

    public String printStack(){
        return head.printForEach(head);
    }
}
