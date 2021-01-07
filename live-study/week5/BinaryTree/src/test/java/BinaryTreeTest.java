import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BinaryTreeTest {

    BinaryTree tree = new BinaryTree();
    Node root;

    @BeforeEach
    void createTree(){
        root = new Node(1);
        Node l = root.addLeftNode(2);
        Node r = root.addRightNode(3);
        Node ll = l.addLeftNode(4);
        Node lr = l.addRightNode(5);
        Node rl = r.addLeftNode(6);
        Node rr = r.addRightNode(7);
    }

    @Test
    @DisplayName("트리 생성 테스트")
    void create(){
        assertEquals(2, root.getLeft().getValue());
        assertEquals(3, root.getRight().getValue());

        assertEquals(4, root.getLeft().getLeft().getValue());
        assertEquals(5, root.getLeft().getRight().getValue());

        assertEquals(6, root.getRight().getLeft().getValue());
        assertEquals(7, root.getRight().getRight().getValue());
    }

    @Test
    @DisplayName("bfs 테스트")
    void bfs() {
        List<Integer> ans = Arrays.asList(1,2,3,4,5,6,7);
        tree.bfs(root);
        assertArrayEquals(ans.toArray(), tree.getBfsList().toArray());
    }

    @Test
    @DisplayName("dfs 테스트")
    void dfs() {
        List<Integer> ans = Arrays.asList(4,2,5,1,6,3,7);
        tree.dfs(root);
        assertArrayEquals(ans.toArray(), tree.getDfsList().toArray());
    }
}