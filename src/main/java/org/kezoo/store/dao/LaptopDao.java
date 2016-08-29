package org.kezoo.store.dao;

import org.kezoo.store.model.Laptop;
import org.springframework.stereotype.Repository;

@Repository
public class LaptopDao extends ProductDao<Laptop>{

    public LaptopDao() {
        super(Laptop.class);
    }
}
