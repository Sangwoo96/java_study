public class Node {
    private int value;
    private Node left;
    private Node right;

    Node(int value){
        this.value = value;
        left = right = null;
    }

    public Node addRightNode(int value){
        Node node = new Node(value);
        right = node;
        return node;
    }

    public Node addLeftNode(int value){
        Node node = new Node(value);
        left = node;
        return node;
    }

    public Node getLeft(){
        return left;
    }

    public Node getRight(){
        return right;
    }

    public int getValue(){
        return value;
    }
}
