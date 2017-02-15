package com.dao;

import java.util.List;

import org.hibernate.SessionFactory;

import com.bean.CommonBean;

/**
 * 提供获取、添加、删除、修改4种方法
 * 
 * @author zwh
 *
 * @param <T
 *            extends CommonBean>
 * @see CommonBean
 */
public interface ICommonDao<T extends CommonBean> {
    boolean save(T entity);

    boolean delete(T entity);

    boolean updateByID(T entity);

    T getByID(T entity);

    T getByID(int id);

    List<T> getAll();

    void setSessionFactory(SessionFactory sessionFactory);
}
