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