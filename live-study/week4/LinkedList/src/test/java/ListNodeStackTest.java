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