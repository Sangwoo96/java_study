import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BinaryTreeTest {

    BinaryTree tree;
    Node root;

    @BeforeEach
    void create(){
        root = new Node(1);
        Node l = root.addLeftNode(2);
        Node r = root.addRightNode(3);
        Node ll = l.addLeftNode(4);
        Node lr = l.addRightNode(5);
        Node rl = r.addLeftNode(6);
        Node rr = r.addRightNode(7);
    }

    @Test
    void dfs() {
        List<Integer> ans = Arrays.asList(4,2,5,1,6,3,7);
        tree.dfs(root);
        assertArrayEquals(ans.toArray(), tree.getdfs().toArray());
    }
}