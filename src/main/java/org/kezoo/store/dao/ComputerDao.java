package org.kezoo.store.dao;

import org.kezoo.store.model.Computer;
import org.kezoo.store.model.Monitor;
import org.springframework.stereotype.Repository;

@Repository
public class ComputerDao extends ProductDao<Computer>{

    public ComputerDao() {
        super(Computer.class);
    }
}
