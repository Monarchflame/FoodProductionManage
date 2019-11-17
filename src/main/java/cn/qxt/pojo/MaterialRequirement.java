package cn.qxt.pojo;

import java.util.Date;

public class MaterialRequirement {
    private Integer id;

    private Integer material_id;

    private Integer quantity;

    private String status;

    private Date create_time;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMaterial_id() {
        return material_id;
    }

    public void setMaterial_id(Integer material_id) {
        this.material_id = material_id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }
}