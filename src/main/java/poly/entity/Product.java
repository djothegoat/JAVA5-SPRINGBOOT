package poly.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Product {
    private Integer id;
    private String name;
    private String detail;
    private Integer price;
    private Integer quantity;
    private String images;
    private Boolean status;
    private Integer discount;
    private Category categoryByCategory;

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "Name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "Detail")
    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    @Basic
    @Column(name = "Price")
    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @Basic
    @Column(name = "Quantity")
    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Basic
    @Column(name = "Images")
    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    @Basic
    @Column(name = "Status")
    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Basic
    @Column(name = "Discount")
    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        if (id != null ? !id.equals(product.id) : product.id != null) return false;
        if (name != null ? !name.equals(product.name) : product.name != null) return false;
        if (detail != null ? !detail.equals(product.detail) : product.detail != null) return false;
        if (price != null ? !price.equals(product.price) : product.price != null) return false;
        if (quantity != null ? !quantity.equals(product.quantity) : product.quantity != null) return false;
        if (images != null ? !images.equals(product.images) : product.images != null) return false;
        if (status != null ? !status.equals(product.status) : product.status != null) return false;
        if (discount != null ? !discount.equals(product.discount) : product.discount != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (detail != null ? detail.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (quantity != null ? quantity.hashCode() : 0);
        result = 31 * result + (images != null ? images.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (discount != null ? discount.hashCode() : 0);
        return result;
    }



    @ManyToOne
    @JoinColumn(name = "Category", referencedColumnName = "ID", nullable = false)
    public Category getCategoryByCategory() {
        return categoryByCategory;
    }

    public void setCategoryByCategory(Category categoryByCategory) {
        this.categoryByCategory = categoryByCategory;
    }
}
