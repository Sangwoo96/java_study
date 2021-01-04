# 4주차 - Queue
본 글은 [백기선님의 live-study](https://github.com/whiteship/live-study/issues)를 진행하며 작성한 자료입니다. 자료에서 사용한 **예제 코드**는 직접 만들었습니다.

<br/>

### 코드

```java
public class Queue {
    private int[] nums;

    Queue(){
        nums = new int[0];
    }

    public void push(int data){
        int[] tmp = new int[nums.length+1];
        System.arraycopy(nums, 0, tmp, 0, nums.length);
        tmp[nums.length] = data;
        nums = null;
        nums = tmp;
    }

    public int pop(){
        int result = 0;
        int[] tmp = new int[nums.length-1];
        System.arraycopy(nums, 1, tmp, 0, nums.length-1);
        result = nums[0];
        nums = null;
        nums = tmp;
        return result;
    }

    public int size(){
        return nums.length;
    }

    public String printStack(){
        String str = "";
        for(int num : nums){
            str += num + " ";
        }
        return str;
    }
}
```

<br/>

### 테스트 코드

```java
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QueueTest {

    @Test
    void pushTest() {
        Queue queue = new Queue();
        queue.push(1);
        queue.push(2);
        queue.push(3);

        assertEquals("1 2 3 ",queue.printStack());
    }

    @Test
    void popTest() {
        Queue queue = new Queue();
        queue.push(1);
        queue.push(2);
        queue.push(3);

        queue.pop();
        assertEquals("2 3 ",queue.printStack());

        queue.pop();
        assertEquals("3 ",queue.printStack());

        queue.pop();
        assertEquals("",queue.printStack());
    }
}
```

<br/>

# Github