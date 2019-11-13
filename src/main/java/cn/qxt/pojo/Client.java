package cn.qxt.pojo;

public class Client {
    private String id;

    private String password;

    private String name;

    private String type;

    private Integer creditrating;

    private Double accountbalance;

    private String address;

    private String phone;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public Integer getCreditrating() {
        return creditrating;
    }

    public void setCreditrating(Integer creditrating) {
        this.creditrating = creditrating;
    }

    public Double getAccountbalance() {
        return accountbalance;
    }

    public void setAccountbalance(Double accountbalance) {
        this.accountbalance = accountbalance;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

}