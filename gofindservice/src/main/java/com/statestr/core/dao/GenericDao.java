package com.statestr.core.dao;

import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.hibernate.criterion.DetachedCriteria;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * Created by ruantianbo on 2017/3/12.
 */
public interface GenericDao<T extends Serializable,PK extends Serializable>{
    /**get entity by its primark_key
     * @param id primary_key
     * @return Entity or null if not exist
     * */
    public T get(PK id);
    /**
     * get entity by its primar_key and lock it
     * @param id primary_key;
     * @return Entity or null if not exist
     * */
    public T getWithLock(PK id,LockMode lockMode);
    /**
     * load entity by its primark_key
     * @param id primary_key
     * @return Entity or null if not exist
     * */
    public T load(PK id);
    /**
     * load entity by its primark_key and lock it
     * */
    public T loadWithLock(PK id,LockMode lockMode);

    /***/
    public void update(T entity);

    /***/
    public void updateWithLock(T entity,LockMode lockMode);

    /***/
    public List<T> loadAll();

    /***/
    public void save(T entity);

    /***/
    public void saveOrUpdate(T entity);

    /***/
    //public void saveOrUpdateAll(List<T> entity);

    /***/
    public void delete(T entity);

    /***/
    public void deleteWithLock(T entity,LockMode lockMode);

    /***/
    public void deleteByKey(PK id);

    /***/
    public void deleteByKeyWithLock(PK id,LockMode lockMode);

    /**删除集合中的全部实体**/
    public void deleteAll(Collection<T> entities);

    /*-----------------------HSQL------------------------------*/

    /** add,delete,update by HSQL */
    public int bulkUpdate(String queryString);

    /** add,delete,update by HSQL with params*/
    public int bulkUpdate(String queryString,Object... params);

    /***/
    public List<T> find(String queryString);

    /***/
    public List<T> find(String queryString, Object[] values);

    // 使用命名的HSQL语句检索数据
    public List<T> findByNamedQuery(String queryName);

    // 使用带参数的命名HSQL语句检索数据
    public List<T> findByNamedQuery(String queryName, Object[] values);

    public List<T> findByNamedQueryAndNamedParam(String queryName,
                                              String[] paramNames, Object[] values);

    // 使用HSQL语句检索数据，返回 Iterator
    public Iterator iterate(String queryString);

    // 使用带参数HSQL语句检索数据，返回 Iterator
    public Iterator iterate(String queryString, Object[] values);

    // 关闭检索返回的 Iterator
    public void closeIterator(Iterator it);



    /*-------------------Criteria----------------------------*/
    // 创建与会话无关的检索标准对象
    public DetachedCriteria createDetachedCriteria();

    // 创建与会话绑定的检索标准对象
    public Criteria createCriteria();

    // 使用指定的检索标准检索数据
    public List<T> findByCriteria(DetachedCriteria criteria);

    // 使用指定的检索标准检索数据，返回部分记录
    public List<T> findByCriteria(DetachedCriteria criteria, int firstResult,
                               int maxResults);

    // 使用指定的实体及属性检索（满足除主键外属性＝实体值）数据
    public List<T> findEqualByEntity(T entity, String[] propertyNames);

    // 使用指定的实体及属性(非主键)检索（满足属性 like 串实体值）数据
    public List<T> findLikeByEntity(T entity, String[] propertyNames);

    // 使用指定的检索标准检索数据，返回指定范围的记录
    public Integer getRowCount(DetachedCriteria criteria);

    // 使用指定的检索标准检索数据，返回指定统计值
    public Object getStatValue(DetachedCriteria criteria, String propertyName,
                               String StatName);

    // 加锁指定的实体
    public void lock(T entity, LockMode lockMode);

    // 强制初始化指定的实体
    public void initialize(Object proxy);

    // 强制立即更新缓冲数据到数据库（否则仅在事务提交时才更新）
    public void flush();

}
