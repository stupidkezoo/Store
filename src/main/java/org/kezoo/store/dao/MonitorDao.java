package org.kezoo.store.dao;

import org.kezoo.store.model.Monitor;
import org.springframework.stereotype.Repository;

@Repository
public class MonitorDao extends ProductDao<Monitor>{

    public MonitorDao() {
        super(Monitor.class);
    }
}
