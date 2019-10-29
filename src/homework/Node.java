package homework;

public class Node implements Comparable<Node> {

    private String key;
    private String value;
    private Node left;
    private Node right;

    public Node(String key, String value) {
        this.key = key;
        this.value = value;
        this.left = null;
        this.right = null;
    }
    public String getKey() {
        return key;
    }
    public void setKey(String key) {
        this.key = key;
    }
    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value;
    }
    public Node getLeft() {
        return left;
    }
    public void setLeft(Node left) {
        this.left = left;
    }
    public Node getRight() {
        return right;
    }
    public void setRight(Node right) {
        this.right = right;
    }
    @Override
    public String toString() {
        return String.format("%s\n%s", this.key, this.value);
    }
    @Override
    public int compareTo(Node that) {
        return this.key.compareTo(that.key);
    }
}