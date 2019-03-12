package Models;

public class Image {
    
    private Integer identifier;
    private String path;

    public Image() {
    }

    public Image(Integer identifier, String path) {
        this.identifier = identifier;
        this.path = path;
    }

    public Integer getIdentifier() {
        return identifier;
    }

    public void setIdentifier(Integer identifier) {
        this.identifier = identifier;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
    
    
}
