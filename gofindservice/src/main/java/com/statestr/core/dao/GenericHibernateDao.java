package com.statestr.core.dao;

import org.apache.commons.beanutils.PropertyUtils;
import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.*;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

import javax.annotation.Resource;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * Created by ruantianbo on 2017/3/12.
 */
public class GenericHibernateDao<T extends Serializable, PK extends Serializable> extends HibernateDaoSupport implements GenericDao<T, PK> {

    private Class<T> entityClass;

    public GenericHibernateDao() {
        this.entityClass = null;
        Class clz = getClass();
        Type t = clz.getGenericSuperclass();
        if (t instanceof ParameterizedType) {
            Type[] p = ((ParameterizedType) t).getActualTypeArguments();
            this.entityClass = (Class<T>) p[0];
        }
    }

    public T get(PK id) {
        return (T)getTemplate().get(entityClass,id);
    }

    public T getWithLock(PK id, LockMode lockMode) {
        T t = (T) getHibernateTemplate().get(entityClass, id, lockMode);
        if(t != null){
            this.flush();
        }
        return t;
    }

    public T load(PK id) {
        return (T) getHibernateTemplate().load(entityClass, id);
    }

    public T loadWithLock(PK id,LockMode lockMode) {
        T t = (T) getHibernateTemplate().load(entityClass, id, lockMode);
        if(t != null){
            this.flush();
        }
        return t;
    }

    public List<T> loadAll() {
        return (List<T>) getHibernateTemplate().loadAll(entityClass);
    }

    public void update(T entity) {
        getTemplate().update(entity);
    }

    public void updateWithLock(T entity, LockMode lockMode) {
        getHibernateTemplate().update(entity, lockMode);
        this.flush(); // 立即刷新，否则锁不会生效。
    }

    public void save(T entity) {
        getTemplate().save(entity);
    }

    public void saveOrUpdate(T entity) {
        getTemplate().saveOrUpdate(entity);
    }


    public void delete(T entity) {
        getTemplate().delete(entity);
    }

    public void deleteWithLock(T entity, LockMode lockMode) {
        getTemplate().delete(entity,lockMode);
        this.flush();
    }

    public void deleteByKey(PK id) {
        delete(this.load(id));
    }

    public void deleteByKeyWithLock(PK id, LockMode lockMode) {
        deleteWithLock(this.load(id),lockMode);
    }

    public void deleteAll(Collection<T> entities) {
        getTemplate().deleteAll(entities);
    }

    public int bulkUpdate(String queryString) {
        return getTemplate().bulkUpdate(queryString);
    }

    public int bulkUpdate(String queryString, Object... params) {
        return getTemplate().bulkUpdate(queryString,params);
    }

    public List<T> find(String queryString) {
        return (List<T>)getTemplate().find(queryString);
    }

    public List<T> find(String queryString, Object[] values) {
        return (List<T>) getTemplate().find(queryString,values);
    }

    public List<T> findByNamedQuery(String queryName) {
        return (List<T>) getTemplate().findByNamedQuery(queryName);
    }

    public List<T> findByNamedQuery(String queryName, Object[] values) {
        return (List<T>) getTemplate().findByNamedQuery(queryName,values);
    }

    public List<T> findByNamedQueryAndNamedParam(String queryName, String[] paramNames, Object[] values) {
        return (List<T>)getHibernateTemplate().findByNamedQueryAndNamedParam(queryName,
                paramNames, values);
    }

    public Iterator iterate(String queryString) {
        return getHibernateTemplate().iterate(queryString);
    }

    public Iterator iterate(String queryString, Object[] values) {
        return getHibernateTemplate().iterate(queryString, values);
    }

    public void closeIterator(Iterator it) {
        getHibernateTemplate().closeIterator(it);
    }

    public DetachedCriteria createDetachedCriteria() {
        return DetachedCriteria.forClass(this.entityClass);
    }

    public Criteria createCriteria() {
        return this.createDetachedCriteria().getExecutableCriteria(getTemplate().getSessionFactory().getCurrentSession());
    }

    public List<T> findByCriteria(DetachedCriteria criteria) {
        return (List<T>)getTemplate().findByCriteria(criteria);
    }

    public List<T> findByCriteria(DetachedCriteria criteria, int firstResult, int maxResults) {
        return (List<T>)getTemplate().findByCriteria(criteria,firstResult,maxResults);
    }

    public List<T> findEqualByEntity(T entity, String[] propertyNames) {
        Criteria criteria = this.createCriteria();
        Example example = Example.create(entity);
        example.excludeZeroes();
        String[] defPropertys = getSessionFactory().getClassMetadata(
                entityClass).getPropertyNames();
        for (String defProperty : defPropertys) {
            int i = 0;
            for(i = 0;i<propertyNames.length;++i){
                if (defProperty.equals(propertyNames[i])) {
                    criteria.addOrder(Order.asc(defProperty));
                    break;
                }
            }
            if(i == propertyNames.length){
                example.excludeProperty(defProperty);
            }
        }
        criteria.add(example);
        return (List<T>)criteria.list();
    }

    public List<T> findLikeByEntity(T entity, String[] propertyNames) {
        Criteria criteria = this.createCriteria();
        for(String propertyItem : propertyNames){
            try{
                Object value = PropertyUtils.getProperty(entity,propertyItem);
                if (value instanceof String) {
                    criteria.add(Restrictions.like(propertyItem, (String) value,
                            MatchMode.ANYWHERE));
                    criteria.addOrder(Order.asc(propertyItem));
                } else {
                    criteria.add(Restrictions.eq(propertyItem, value));
                    criteria.addOrder(Order.asc(propertyItem));
                }
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
        return criteria.list();
    }

    public Integer getRowCount(DetachedCriteria criteria) {
        criteria.setProjection(Projections.rowCount());
        List list = this.findByCriteria(criteria, 0, 1);
        return (Integer) list.get(0);
    }

    public Object getStatValue(DetachedCriteria criteria, String propertyName, String StatName) {
        if (StatName.toLowerCase().equals("max"))
            criteria.setProjection(Projections.max(propertyName));
        else if (StatName.toLowerCase().equals("min"))
            criteria.setProjection(Projections.min(propertyName));
        else if (StatName.toLowerCase().equals("avg"))
            criteria.setProjection(Projections.avg(propertyName));
        else if (StatName.toLowerCase().equals("sum"))
            criteria.setProjection(Projections.sum(propertyName));
        else
            return null;
        List list = this.findByCriteria(criteria, 0, 1);
        return list.get(0);
    }

    public void lock(T entity, LockMode lockMode) {
        getHibernateTemplate().lock(entity, lockMode);
    }

    public void initialize(Object proxy) {
        getHibernateTemplate().initialize(proxy);
    }

    public void flush() {
        getHibernateTemplate().flush();
    }

    private HibernateTemplate getTemplate(){
        return this.getHibernateTemplate();
    }

    @Resource
    public void setSessionFacotry(SessionFactory sessionFacotry) {
        super.setSessionFactory(sessionFacotry);
    }
}
