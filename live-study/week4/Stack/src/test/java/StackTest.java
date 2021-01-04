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