package cn.qxt.pojo;

public class Product {
    private Integer id;

    private String name;

    private Integer shelf_life;

    private Double unit_price;

    private String specification;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getShelf_life() {
        return shelf_life;
    }

    public void setShelf_life(Integer shelf_life) {
        this.shelf_life = shelf_life;
    }

    public Double getUnit_price() {
        return unit_price;
    }

    public void setUnit_price(Double unit_price) {
        this.unit_price = unit_price;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification == null ? null : specification.trim();
    }
}