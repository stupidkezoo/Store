package org.kezoo.store.dao;

import org.kezoo.store.model.Hdd;
import org.springframework.stereotype.Repository;

@Repository
public class HddDao extends ProductDao<Hdd>{

    public HddDao() {
        super(Hdd.class);
    }
}
