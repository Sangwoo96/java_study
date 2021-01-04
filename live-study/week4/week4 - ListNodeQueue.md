# 4주차 - ListNodeQueue
본 글은 [백기선님의 live-study](https://github.com/whiteship/live-study/issues)를 진행하며 작성한 자료입니다. 자료에서 사용한 **예제 코드**는 직접 만들었습니다.

<br/>

### 코드

```java
public class ListNodeQueue {
    private ListNode head;

    //head
    ListNodeQueue(){
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
            return head.remove(head, 1).getData();
        }catch (NullPointerException e){
            return -1;
        }
    }

    public String printStack(){
        return head.printForEach(head);
    }

}

```

<br/>

### 테스트 코드

```java
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ListNodeQueueTest {

    @Test
    void pushTest() {
        ListNodeQueue listNodeQueue = new ListNodeQueue();
        listNodeQueue.push(1);
        listNodeQueue.push(2);
        listNodeQueue.push(3);

        assertEquals("1 2 3 ", listNodeQueue.printStack());
    }

    @Test
    void popTest() {
        ListNodeQueue listNodeQueue = new ListNodeQueue();
        listNodeQueue.push(1);
        listNodeQueue.push(2);
        listNodeQueue.push(3);

        assertEquals(1, listNodeQueue.pop());
        assertEquals(2, listNodeQueue.pop());
        assertEquals(3, listNodeQueue.pop());
        assertEquals(-1, listNodeQueue.pop());
    }
}
```

<br/>

# Github