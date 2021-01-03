public class ListNode {
    private int data;
    private ListNode next;
    private boolean head;

    //head
    ListNode() {
        head = true;
        next = null;
        data = -1;
    }

    //node
    ListNode(int data) {
        head = false;
        next = null;
        this.data = data;
    }

    //getter
    public int getData(){ return data; }
    public ListNode getNext(){ return next; }
    public boolean getHead(){ return head; }

    //setter
    public void setData(int data) { this.data = data; }
    public void setNext(ListNode next) { this.next = next; }
    public void setHead(boolean head) { this.head = head; }

    // position과 리스트 사이즈 비교
    public ListNode add(ListNode head, ListNode nodeToAdd, int position){
        if(position < 1 || position > this.size(head) + 1) return null;

        ListNode cur = head;
        //맨처음에 추가
        if(position == 1){
            nodeToAdd.setNext(cur.getNext());
            cur.setNext(nodeToAdd);
        }
        //중간에 추가
        else if(position > 1){
            for(int i=0; i<position-1; i++){
                cur = cur.getNext();
            }
            nodeToAdd.setNext(cur.getNext());
            cur.setNext(nodeToAdd);
        }
        return nodeToAdd;
    }

    public ListNode remove(ListNode head, int positionToRemove){
        if(positionToRemove < 1 || positionToRemove > this.size(head)) return null;

        ListNode cur = head.getNext();

        if(positionToRemove == 1){
            head.setNext(cur.getNext());
        }
        else{
            ListNode prev = null;
            for(int i = 1; i < positionToRemove; i++) {
                prev = cur;
                cur = cur.getNext();
            }
            prev.setNext(cur.getNext());
        }
        return cur;
    }

    public boolean contains(ListNode head, ListNode nodeToCheck){
        ListNode cur = head.getNext();
        for(int i = 1; i <= this.size(head); i++){
            if(cur.equals(nodeToCheck)) {
                return true;
            }
        }
        return false;
    }

    public int size(ListNode head){
        ListNode cur = head;
        int size = 0;
        while(cur.getNext() != null){
            cur = cur.getNext();
            ++size;
        }
        return size;
    }

    public String printForEach(ListNode head){
        String result = "";
        ListNode cur = head.getNext();
        for(int i = 1; i<= this.size(head); i++){
            result += cur.getData() + " ";
            cur = cur.getNext();
        }
        return result;
    }
}

