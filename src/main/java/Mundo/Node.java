package Mundo;

import java.util.ArrayList;
import java.util.List;

public class Node {

    private Integer node;
    private Integer x;
    private Integer y;
    private List<String> items;

    public Node() {
        this(null, null, null);
    }

    public Node(Integer id, Integer x, Integer y) {
        this.node = id;
        this.x = x;
        this.y = y;
        this.items  = new ArrayList<String>();
    }

    public Integer getNode() {
        return node;
    }

    public void setNode(Integer node) {
        this.node = node;
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    public List<String> getItems() {
        return items;
    }

    public void setItems(List<String> items) {
        this.items = items;
    }

    
}
