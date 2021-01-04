# 4주차 - ListNodeStack
본 글은 [백기선님의 live-study](https://github.com/whiteship/live-study/issues)를 진행하며 작성한 자료입니다. 자료에서 사용한 **예제 코드**는 직접 만들었습니다.

<br/>

## 목표
* ListNode head를 가지고 있는 ListNodeStack 클래스를 구현하세요.
* void push(int data)를 구현하세요.
* int pop()을 구현하세요

<br/>

### 코드

```java
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
```

<br/>

### 테스트 코드

```java
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ListNodeStackTest {
    @Test
    void pushTest(){
        ListNodeStack listNodeStack = new ListNodeStack();
        listNodeStack.push(1);
        listNodeStack.push(2);
        listNodeStack.push(3);

        assertEquals("1 2 3 ", listNodeStack.printStack());
    }

    @Test
    void popTest(){
        ListNodeStack listNodeStack = new ListNodeStack();
        listNodeStack.push(1);
        listNodeStack.push(2);
        listNodeStack.push(3);

        assertEquals(3, listNodeStack.pop());
        assertEquals(2, listNodeStack.pop());
        assertEquals(1, listNodeStack.pop());
        assertEquals(-1, listNodeStack.pop());
    }
}
```
![4](https://user-images.githubusercontent.com/55661631/103541560-f381ac00-4ede-11eb-9e18-93a9803d83ca.PNG)

<br/>

# Github
* https://github.com/highright96/java_study/blob/main/live-study/week4/LinkedList/src/main/java/ListNodeStack.java
* https://github.com/highright96/java_study/blob/main/live-study/week4/LinkedList/src/test/java/ListNodeStackTest.java
