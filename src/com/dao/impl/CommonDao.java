package com.dao.impl;

import java.lang.reflect.Method;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.TransactionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bean.CommonBean;
import com.dao.ICommonDao;
import com.hql.HQLGenerator;

/**
 * 实现ICommonDao接口，并实现增、删、改以及设置SessionFactory方法，还提供了用于查询、获取session的方法
 * 
 * @author zwh
 *
 * @param <T>
 * @see ICommonDao
 */
@Repository
public abstract class CommonDao<T extends CommonBean> implements ICommonDao<T> {

    @Autowired
    protected SessionFactory sessionFactory;
    private static final String SAVE_METHOD = "save";
    private static final String DELETE_METHOD = "delete";
    private static final String UPDATE_METHOD = "update";
    protected final String CLASS_SIMPLENAME;
    protected final String ID_COLUMN_NAME;

    /**
     * 传入一个已经实体化的对象（非null对象），并用该对象初始化CLASS_SIMPLENAME、ID_COLUMN_NAME两个变量
     * 
     * @param entity
     */
    public CommonDao(T entity) {
	this.CLASS_SIMPLENAME = entity.getClassSimpleName();
	this.ID_COLUMN_NAME = entity.getIdColumnName();
    }

    /**
     * 获取开启事务后的session
     * 
     * @return
     */
    protected Session getCurrentSession() {
	Session session = this.sessionFactory.getCurrentSession();
	try {
	    session.beginTransaction();
	} catch (TransactionException e) {

	}
	return session;
    }

    /**
     * 传入hql语句进行单个列的值得查询，返回list
     * 
     * @param hql
     * @return
     */
    @SuppressWarnings("unchecked")
    protected List<T> queryList(String hql) {
	Session session = this.getCurrentSession();
	Query query = session.createQuery(hql);
	return query.list();
    }

    /**
     * 传入hql语句进行单个列的值得查询，如果查询不到结果，返回null
     * 
     * @param hql
     * @return
     */
    @SuppressWarnings("unchecked")
    protected T queryUnique(String hql) {
	Session session = this.getCurrentSession();
	Query query = session.createQuery(hql);
	return (T) query.uniqueResult();
    }

    @Override
    public void setSessionFactory(SessionFactory sessionFactory) {
	this.sessionFactory = sessionFactory;
    }

    private Method getMethod(String operation) throws Exception {
	return Session.class.getMethod(operation, Object.class);
    }

    /**
     * 执行增、删、改操作
     * 
     * @param oper
     * @param entity
     * @return
     */
    private boolean execute(String oper, T entity) {
	try {
	    Session session = this.getCurrentSession();
	    Method method = this.getMethod(oper);
	    method.invoke(session, entity);
	    session.getTransaction().commit();
	    return true;
	} catch (Exception e) {
	    e.printStackTrace();
	    return false;
	}
    }

    @Override
    public boolean save(T entity) {
	return this.execute(SAVE_METHOD, entity);
    }

    @Override
    public boolean delete(T entity) {
	return this.execute(DELETE_METHOD, entity);
    }

    @Override
    public boolean updateByID(T entity) {
	return this.execute(UPDATE_METHOD, entity);
    }

    @Override
    public T getByID(T entity) {
	return this.getByID(entity.getId());
    }

    @Override
    public T getByID(int id) {
	return this.queryUnique(HQLGenerator.generateSingleEqualQueryHql(this.CLASS_SIMPLENAME,
		this.ID_COLUMN_NAME, String.valueOf(id)));
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<T> getAll() {
	Session session = this.getCurrentSession();
	return session.createQuery(HQLGenerator.generateAllQuery(this.CLASS_SIMPLENAME)).list();
    }
}
