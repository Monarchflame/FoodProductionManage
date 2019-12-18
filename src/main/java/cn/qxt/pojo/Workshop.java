package cn.qxt.pojo;

public class Workshop {
    private Integer id;

    private String name;

    private Integer max_staff;

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

    public Integer getMax_staff() {
        return max_staff;
    }

    public void setMax_staff(Integer max_staff) {
        this.max_staff = max_staff;
    }
}