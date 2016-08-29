package org.kezoo.store.service.impl;

import org.kezoo.store.dao.ProductDao;
import org.kezoo.store.service.DaoManager;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DaoManagerImpl implements DaoManager{

    private Map<Class, ProductDao> map = new HashMap<>();

    @Override
    public void register(ProductDao dao) {
        map.put(dao.getClazz(), dao);
    }

    @Override
    public ProductDao get(Class clazz) {
        return map.get(clazz);
    }

    @Override
    public Collection<ProductDao> getAllDao() {
        return map.values();
    }
}
