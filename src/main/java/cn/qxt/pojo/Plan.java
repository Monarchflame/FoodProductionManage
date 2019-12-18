package cn.qxt.pojo;

import java.util.Date;

public class Plan {
    private Integer id;

    private Integer product_id;

    private Integer quantity;

    private Date finish_time;

    private String status;

    private Date create_time;

    private Integer workshop_id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Integer product_id) {
        this.product_id = product_id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Date getFinish_time() {
        return finish_time;
    }

    public void setFinish_time(Date finish_time) {
        this.finish_time = finish_time;
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

    public Integer getWorkshop_id() {
        return workshop_id;
    }

    public void setWorkshop_id(Integer workshop_id) {
        this.workshop_id = workshop_id;
    }
}