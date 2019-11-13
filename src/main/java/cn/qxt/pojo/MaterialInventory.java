package cn.qxt.pojo;

import java.util.Date;

public class MaterialInventory {
    private Integer id;

    private Integer material_id;

    private Integer quantity;

    private Date expiration_time;

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

    public Date getExpiration_time() {
        return expiration_time;
    }

    public void setExpiration_time(Date expiration_time) {
        this.expiration_time = expiration_time;
    }
}