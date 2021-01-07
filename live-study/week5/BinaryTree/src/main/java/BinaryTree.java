import java.awt.desktop.SystemSleepEvent;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BinaryTree {
    private List<Integer> bfsList = new ArrayList<>();
    private List<Integer> dfsList = new ArrayList<>();

    public void bfs(Node node){
        Queue<Node> q = new LinkedList<>();
        q.offer(node);
        while(!q.isEmpty()){
            Node tmp = q.poll();
            bfsList.add(tmp.getValue());
            if(tmp.getLeft() != null) q.offer(tmp.getLeft());
            if(tmp.getRight() != null) q.offer(tmp.getRight());
        }
    }

    public void dfs(Node node){
        if(node.getLeft()==null) {
            dfsList.add(node.getValue()); //현재 노드(왼쪽)를 리스트에 저장
            return; //부모 노드로 돌아감
        }
        dfs(node.getLeft()); //왼쪽 자식 노드로 내려

        dfsList.add(node.getValue()); // 현재 노드(중간)를 리스트에 저장

        if(node.getRight() == null) {
            dfsList.add(node.getValue()); // 현재 노드(오른쪽)를 리스트에 저장
            return; //부모 노드로 돌아감
        }
        dfs(node.getRight()); //오른쪽 자식 노드로 내려감
    }

    public List<Integer> getDfsList(){ return dfsList;}
    public List<Integer> getBfsList(){ return bfsList;}
}
