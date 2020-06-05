package Elements;

public class Node {
    
    public String token;
    public Node left, right;
    public boolean isDummy, isUnit;
    
    public Node(String token){
        this.token = token;
        left = null; right = null;
        isDummy = false; isUnit = false;
    }
}
