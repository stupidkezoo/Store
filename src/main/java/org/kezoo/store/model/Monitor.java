package org.kezoo.store.model;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import java.math.BigDecimal;

@Entity
@PrimaryKeyJoinColumn(name="id")
public class Monitor extends Product {

    private String diagonal;

    public String getDiagonal() {
        return diagonal;
    }

    public void setDiagonal(String diagonal) {
        this.diagonal = diagonal;
    }
}