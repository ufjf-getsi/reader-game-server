package Mundo;

public class Item {
    private Integer id;
    private String data;
    private Integer node;

    public Item() {
    }

    public Item(Integer id, String data, Integer node) {
        this.id = id;
        this.data = data;
        this.node = node;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Integer getNode() {
        return node;
    }

    public void setNode(Integer node) {
        this.node = node;
    }
    
    
}
