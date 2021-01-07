# 5주차 - 이진트리
본 글은 [백기선님의 live-study](https://github.com/whiteship/live-study/issues)를 진행하며 작성한 자료입니다. 자료에서 사용한 **예제 코드**는 직접 만들었습니다.

<br/>

## ✔ 목표
이진트리에 대해 학습하세요.

<br/>

## ✔ 과제
* int 값을 가지고 있는 이진 트리를 나타내는 Node 라는 클래스를 정의하세요.
* int value, Node left, right를 가지고 있어야 합니다.
* BinrayTree라는 클래스를 정의하고 주어진 노드를 기준으로 출력하는 bfs(Node node)와 dfs(Node    node) 메소드를 구현하세요.
* DFS는 왼쪽, 루트, 오른쪽 순으로 순회하세요.

<br/>

## 💡 이진트리

### **코드**

```java
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
```

```java
public class BinaryTree {
    private List<Integer> bfsList = new ArrayList<>();
    private List<Integer> dfsList = new ArrayList<>();

    //큐 사용
    public void bfs(Node node){
        Queue<Node> q = new LinkedList<>();
        q.offer(node);
        while(!q.isEmpty()){
            Node tmp = q.poll();
            bfsList.add(tmp.getValue());
            if(tmp.getLeft() != null) q.offer(tmp.getLeft());
            if(tmp.getRight() != null) q.offer(tmp.getRight());
        }
    }

    //재귀 사용
    public void dfs(Node node){
        if(node.getLeft()==null) {
            dfsList.add(node.getValue()); //현재 노드(왼쪽)를 리스트에 저장
            return; //부모 노드로 돌아감
        }
        dfs(node.getLeft()); //왼쪽 자식 노드로 내려

        dfsList.add(node.getValue()); // 현재 노드(중간)를 리스트에 저장

        if(node.getRight() == null) {
            dfsList.add(node.getValue()); // 현재 노드(오른쪽)를 리스트에 저장
            return; //부모 노드로 돌아감
        }
        dfs(node.getRight()); //오른쪽 자식 노드로 내려감
    }

    public List<Integer> getDfsList(){ return dfsList;}
    public List<Integer> getBfsList(){ return bfsList;}
}
```

<br/>

### **테스트 코드**

```java
class BinaryTreeTest {
    BinaryTree tree = new BinaryTree();
    Node root;

    @BeforeEach
    void createTree(){
        root = new Node(1);
        Node l = root.addLeftNode(2);
        Node r = root.addRightNode(3);
        Node ll = l.addLeftNode(4);
        Node lr = l.addRightNode(5);
        Node rl = r.addLeftNode(6);
        Node rr = r.addRightNode(7);
    }

    @Test
    @DisplayName("트리 생성 테스트")
    void create(){
        assertEquals(2, root.getLeft().getValue());
        assertEquals(3, root.getRight().getValue());

        assertEquals(4, root.getLeft().getLeft().getValue());
        assertEquals(5, root.getLeft().getRight().getValue());

        assertEquals(6, root.getRight().getLeft().getValue());
        assertEquals(7, root.getRight().getRight().getValue());
    }

    @Test
    @DisplayName("bfs 테스트")
    void bfs() {
        List<Integer> ans = Arrays.asList(1,2,3,4,5,6,7);
        tree.bfs(root);
        assertArrayEquals(ans.toArray(), tree.getBfsList().toArray());
    }

    @Test
    @DisplayName("dfs 테스트")
    void dfs() {
        List<Integer> ans = Arrays.asList(4,2,5,1,6,3,7);
        tree.dfs(root);
        assertArrayEquals(ans.toArray(), tree.getDfsList().toArray());
    }
}
```

<br/>

# Github
* https://github.com/highright96/java_study/tree/main/live-study/week5/BinaryTree/src/main/java
* https://github.com/highright96/java_study/blob/main/live-study/week5/BinaryTree/src/test/java/BinaryTreeTest.java

<br/>

# 참고
* 열혈 자료구조 - 윤성우