# 4주차 - LinkedList
본 글은 [백기선님의 live-study](https://github.com/whiteship/live-study/issues)를 진행하며 작성한 자료입니다. 자료에서 사용한 **예제 코드**는 직접 만들었습니다.

<br/>

## 목표
* LinkedList에 대해 공부하세요.
* 정수를 저장하는 ListNode 클래스를 구현하세요.
* ListNode add(ListNode head, ListNode nodeToAdd, int position)를 구현하세요.
* ListNode remove(ListNode head, int positionToRemove)를 구현하세요.
* boolean contains(ListNode head, ListNode nodeTocheck)를 구현하세요.

<br/>

### **LinkedList**
* node(vertex) 값value과 다음 노드의 위치next를 가지는 객체
* head LinkedList의 첫번째 node를 가리킨다.
* 각 node는 값value와 다음 노드로 연결되는 포인터(링크)next를 가진다.
* 노드의 종류는 head와 일반 원소가 되는 node가 있다.
* head는 값은 가지지 않으며 항상 LinkedList의 첫번째 node를 가리킨다.
* node는 다음 node가 없는 경우 null을 가리킨다.

<br/>

### 코드
```java
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
```

<br/>

### 테스트 코드
```java
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ListNodeTest {

    @Test
    @DisplayName("헤더, 노드 생성 테스트")
    void createTest(){
        ListNode head = new ListNode();
        ListNode node = new ListNode(1);
        //헤더
        assertAll(
                ()->assertEquals(true, head.getHead()),
                ()->assertNull(head.getNext()),
                ()->assertEquals(-1, head.getData())
        );
        //노드
        assertAll(
                ()->assertEquals(false, node.getHead()),
                ()->assertNull(node.getNext()),
                ()->assertEquals(1, node.getData())
        );

    }

    @Test
    @DisplayName("노드 추가 테스트")
    void addTest() {
        ListNode headNode = new ListNode();

        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        headNode.add(headNode, node1, 1);
        headNode.add(headNode, node2, 2);
        assertEquals("1 2 ", headNode.printForEach(headNode));

        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        //처음에 추가
        headNode.add(headNode, node3, 1);
        //중간에 추가
        headNode.add(headNode, node4, 2);
        //마지막에 추가
        headNode.add(headNode, node5, 5);
        assertEquals("3 4 1 2 5 ", headNode.printForEach(headNode));

        //범위 넘으면 null 반환
        ListNode node6 = new ListNode(6);
        ListNode node7 = new ListNode(7);
        assertNull(headNode.add(headNode, node6, -1));
        assertNull(headNode.add(headNode, node7, 7));
    }

    @Test
    @DisplayName("노드 삭제 테스트")
    void removeTest() {
        ListNode headNode = new ListNode();

        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);

        headNode.add(headNode, node1, 1);
        headNode.add(headNode, node2, 2);
        headNode.add(headNode, node3, 3);
        headNode.add(headNode, node4, 4);

        //처음에 삭제
        headNode.remove(headNode, 1);
        //중간에 삭제
        headNode.remove(headNode, 2);
        //마지막에 삭제
        headNode.remove(headNode, 2);
        assertEquals("2 ", headNode.printForEach(headNode));

        //범위 넘으면 null 반환
        assertNull(headNode.remove(headNode, -1));
        assertNull(headNode.remove(headNode, 2));
    }

    @Test
    @DisplayName("노드 포함여부 테스트")
    void containsTest() {
        ListNode headNode = new ListNode();

        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        headNode.add(headNode, node1, 1);

        assertTrue(headNode.contains(headNode, node1));
        assertFalse(headNode.contains(headNode, node2));
        
    }
}
```

<br/>

![2](https://user-images.githubusercontent.com/55661631/103481220-d8e6fe80-4e1c-11eb-92f3-c028810ab596.PNG)

<br/>

# Github
* https://github.com/highright96/java_study/blob/main/live-study/week4/LinkedList/src/main/java/ListNode.java
* https://github.com/highright96/java_study/blob/main/live-study/week4/LinkedList/src/test/java/ListNodeTest.java

<br/>

# 참고
* 열혈 자료구조 - 윤성우
