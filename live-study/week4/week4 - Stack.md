# 4주차 - Stack
본 글은 [백기선님의 live-study](https://github.com/whiteship/live-study/issues)를 진행하며 작성한 자료입니다. 자료에서 사용한 **예제 코드**는 직접 만들었습니다.

<br/>

## 목표
* int 배열을 사용해서 정수를 저장하는 Stack을 구현하세요.
* void push(int data)를 구현하세요.
* int pop()을 구현하세요.

<br/>

### 코드

```java
public class Stack {
    private int[] nums;

    Stack(){
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
        System.arraycopy(nums, 0, tmp, 0, nums.length-1);
        result = nums[nums.length-1];
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

class StackTest {
    @Test
    void createTest(){
        Stack stack = new Stack();

        assertEquals(0, stack.size());
    }

    @Test
    void pushTest() {
        Stack stack = new Stack();
        stack.push(1);
        stack.push(2);
        stack.push(3);

        assertEquals("1 2 3 ",stack.printStack());
    }

    @Test
    void popTest() {
        Stack stack = new Stack();
        stack.push(1);
        stack.push(2);
        stack.push(3);

        stack.pop();
        assertEquals("1 2 ",stack.printStack());

        stack.pop();
        assertEquals("1 ",stack.printStack());

        stack.pop();
        assertEquals("",stack.printStack());
    }
}
```

![3](https://user-images.githubusercontent.com/55661631/103541131-460e9880-4ede-11eb-9b86-ab5dcd53c8ee.PNG)

<br/>

# Github
https://github.com/highright96/java_study/tree/main/live-study/week4/Stack
