package cn.qxt.pojo;

public class Material {
    private Integer id;

    private String name;

    private Integer shelf_life;

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
}