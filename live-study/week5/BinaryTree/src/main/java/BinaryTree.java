import java.util.ArrayList;
import java.util.List;

public class BinaryTree {
    private List<Integer> bfsList = new ArrayList<>();
    private List<Integer> dfsList = new ArrayList<>();

    public void bfs(Node node){
    }

    public void dfs(Node node){
        if(node==null) return;
        dfs(node.getLeft());
        dfsList.add(node.getValue());
        if(node.getRight() == null){
            dfsList.add(node.getValue());
        }
        dfs(node.getRight());
    }

    public List<Integer> getdfs(){ return dfsList;}
    public List<Integer> getbfs(){ return bfsList;}
}
