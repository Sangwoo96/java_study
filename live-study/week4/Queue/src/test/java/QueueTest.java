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