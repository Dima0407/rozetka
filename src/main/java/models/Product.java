package models;

public class Product {

    private String id;
    private String name;
    private String gTag;
    private long price;

    public Product(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGTag() {
        return gTag;
    }

    public void setGTag(String gTag) {
        this.gTag = gTag;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }
}
