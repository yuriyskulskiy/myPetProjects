package domain.products;

/**
 * Created by ��� on 19.06.2016.
 */
public class Product {
    private long Id;
    private String name;

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
