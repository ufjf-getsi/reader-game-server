package Mundo;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.openide.util.Exceptions;

public class Item {

    private Integer id;
    private String data;
    private Integer node;
    private Map<String, String> dataMap;

    public Item() {
        this(null, "{}", null);
    }

    public Item(Integer id, String data, Integer node) {
        this.id = id;
        this.node = node;
        this.dataMap = new HashMap<>();
        this.setData(data);
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
        this.dataMap = new HashMap<>();
        ObjectMapper mapper = new ObjectMapper();
        try {
            this.dataMap = mapper.readValue(this.data, Map.class);
        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
        }

    }

    public Integer getNode() {
        return node;
    }

    public void setNode(Integer node) {
        this.node = node;
    }

    public Map<String, String> getDataMap() {
        return dataMap;
    }

}
