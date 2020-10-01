package application.data.model;


import javax.persistence.*;
import javax.validation.constraints.Past;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "dbo_size")

public class Size {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "size_id")
    private int id;

    @Column(name = "size_name")
    private String name;

    @Column(name = "width")
    private double width;

    @Column(name = "height")
    private double height;

    @Column(name="shortDesc")
    private String shortDesc;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "size")
    private List<Item> itemLists=new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public String getShortDesc() {
        return shortDesc;
    }

    public void setShortDesc(String shortDesc) {
        this.shortDesc = shortDesc;
    }

    public List<Item> getItemLists() {
        return itemLists;
    }

    public void setItemLists(List<Item> itemLists) {
        this.itemLists = itemLists;
    }
}
