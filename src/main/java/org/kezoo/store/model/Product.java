package org.kezoo.store.model;

import org.codehaus.jackson.annotate.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Inheritance(strategy= InheritanceType.JOINED)
public abstract class Product implements Serializable{

    private static final long serialVersionUID = 4087840673758184892L;

    @Id
    private String serial;

    @Version
    @JsonIgnore
    private Long version;

    private String manufacturer;

    private BigDecimal price;

    private Long quantity;

    public Product() {};

    public Product(String serial, String manufacturer, BigDecimal price, Long quantity) {
        this.serial = serial;
        this.manufacturer = manufacturer;
        this.price = price;
        this.quantity = quantity;
    }


    public Long getVersion() {
        return version;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }
}
