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