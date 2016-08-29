package org.kezoo.store.dao;

import org.hibernate.SessionFactory;
import org.kezoo.store.model.Product;
import org.kezoo.store.service.DaoManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.List;

@Repository
public abstract class ProductDao<T extends Product> {

    @Autowired
    private DaoManager daoManager;

    private HibernateTemplate hibernateTemplate;

    private Class<T> clazz;

    ProductDao(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Autowired
    public void setSessionFactory(@Qualifier("sessionFactory") SessionFactory sessionFactory) {
        hibernateTemplate = new HibernateTemplate(sessionFactory);
    }

    public Class<T> getClazz() {
        return clazz;
    }

    public List<T> getAll() {
        return hibernateTemplate.loadAll(clazz);
    }

    public void update(T t) {
        hibernateTemplate.update(t);
    }

    public T getById(String id) {
        return hibernateTemplate.get(clazz, id);
    }

    public void save(T product) {
        hibernateTemplate.save(product);
    }

    @PostConstruct
    private void postConstruct() {
        daoManager.register(this);
    }
}
