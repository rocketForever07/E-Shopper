package application.data.model;

import javax.persistence.*;

@Entity(name = "dbo_item")
public class Item {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "item_id")
    @Id
    private int id;


    @ManyToOne(optional = true,fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "product_id",insertable = false,updatable = false)
    private int productId;

    @ManyToOne(optional = true,fetch = FetchType.LAZY)
    @JoinColumn(name="size_id")
    private Size size;

    @Column(name = "size_id",insertable = false,updatable = false)
    private int sizeId;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSizeId() {
        return sizeId;
    }

    public void setSizeId(int sizeId) {
        this.sizeId = sizeId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }
}
