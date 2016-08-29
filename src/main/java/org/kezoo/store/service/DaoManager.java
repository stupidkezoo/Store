package org.kezoo.store.service;

import org.kezoo.store.dao.ProductDao;

import java.util.Collection;

public interface DaoManager {
    void register(ProductDao productDao);
    ProductDao get(Class clazz);
    Collection<ProductDao> getAllDao();
}
