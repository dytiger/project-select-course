package org.forten;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.Query;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class HibernateDao {
    private SessionFactory sessionFactory;

    private static final int BATCH_SIZE = 30;

    /**
     * 保存实体对象
     *
     * @param entities 被保存的实体对象
     */
    @SuppressWarnings("unchecked")
    public <T> void save(T... entities) {
        Session session = getSession();
        int i = 1;
        for (T t : entities) {
            session.save(t);
            if (i % BATCH_SIZE == 0) {
                session.flush();
                session.clear();
            }
            i++;
        }
    }

    /**
     * 保存实体对象
     *
     * @param entities 被保存的实体对象
     */
    @SuppressWarnings("unchecked")
    public <T> void persist(T... entities) {
        Session session = getSession();
        int i = 1;
        for (T t : entities) {
            session.persist(t);
            if (i % BATCH_SIZE == 0) {
                session.flush();
                session.clear();
            }
            i++;
        }
    }

    /**
     * 更新实体对象
     *
     * @param entities 要更新的实体对象
     */
    @SuppressWarnings("unchecked")
    public <T> void update(T... entities) {
        Session session = getSession();
        int i = 1;
        for (T t : entities) {
            session.update(t);
            if (i % BATCH_SIZE == 0) {
                session.flush();
                session.clear();
            }
            i++;
        }
    }

    /**
     * 保存或更新实体对象
     *
     * @param entities 待持久化的实体对象
     */
    @SuppressWarnings("unchecked")
    public <T> void saveOrUpdate(T... entities) {
        Session session = getSession();
        int i = 1;
        for (T t : entities) {
            session.saveOrUpdate(t);
            if (i % BATCH_SIZE == 0) {
                session.flush();
                session.clear();
            }
            i++;
        }
    }

    /**
     * 保存或更新实体对象
     *
     * @param entities 待持久化的实体对象 与saveOrUpdate不同的是，marge方法会创建实体对象的一个副本，对副本进行状态切换
     */
    @SuppressWarnings("unchecked")
    public <T> void merge(T... entities) {
        Session session = getSession();
        int i = 1;
        for (T t : entities) {
            session.merge(t);
            if (i % BATCH_SIZE == 0) {
                session.flush();
                session.clear();
            }
            i++;
        }
    }

    /**
     * 删除实体对象
     *
     * @param entities 准备删除的实体对象
     */
    @SuppressWarnings("unchecked")
    public <T> void delete(T... entities) {
        Session session = getSession();
        int i = 1;
        for (T t : entities) {
            session.delete(t);
            if (i % BATCH_SIZE == 0) {
                session.flush();
                session.clear();
            }
            i++;
        }
    }

    /**
     * 使用get按id进行单个实体对象的查询
     *
     * @param clazz 查询的实体对象类型
     * @param id    实体对象的id
     * @return 查询出的实体对象
     */
    public <T> T getById(Class<T> clazz, Serializable id) {
        return getSession().get(clazz, id);
    }

    /**
     * 使用load按id进行单个实体对象的查询
     *
     * @param clazz 查询的实体对象类型
     * @param id    实体对象的id
     * @return 查询出的实体对象
     */
    public <T> T loadById(Class<T> clazz, Serializable id) {
        return getSession().load(clazz, id);
    }

    /**
     * 查询某一类实体的所有实体对象
     *
     * @param clazz 实体对象的类型
     * @return 实体对象的集合
     */
    @SuppressWarnings("unchecked")
    public <T> List<T> findAll(Class<T> clazz) {
        String hql = "FROM " + clazz.getName();
        Query query = getSession().createQuery(hql);
        return query.getResultList();
    }

    /**
     * 复合条件查询
     *
     * @param hql    要执行的查询语句
     * @param params 参数名值对，注意，这里一定要使用命名参数的绑定形式
     * @return 查询结果集合
     */
    @SuppressWarnings("unchecked")
    public <T> List<T> findBy(String hql, Map<String, Object> params) {
        Query query = getSession().createQuery(hql);
        for (Entry<String, Object> param : params.entrySet()) {
            query.setParameter(param.getKey(), param.getValue());
        }
        return query.getResultList();
    }

    public <T> List<T> findBy(String hql) {
        Query query = getSession().createQuery(hql);
        return query.getResultList();
    }

    /**
     * 分页复合条件查询
     *
     * @param hql         要执行的查询语句
     * @param params      参数名值对，注意，这里一定要使用命名参数的绑定形式
     * @param firstResult 分页首行数据行号（第一行为0）
     * @param maxResults  每页的数据量
     * @return 查询结果集合中的某一段数据
     */
    @SuppressWarnings("unchecked")
    public <T> List<T> findBy(String hql, Map<String, Object> params, int firstResult, int maxResults) {
        Query query = getSession().createQuery(hql);
        for (Entry<String, Object> param : params.entrySet()) {
            query.setParameter(param.getKey(), param.getValue());
        }
        // Hibernate中的分页查询的两个关键方法
        // 设置从第几条数据开始查询
        query.setFirstResult(firstResult);
        // 设置一次查询多少条
        query.setMaxResults(maxResults);
        return query.getResultList();
    }

    /**
     * 复合条件查询单条数据
     *
     * @param hql    要执行的查询语句
     * @param params 参数名值对，注意，这里一定要使用命名参数的绑定形式
     * @return 单条符合查询条件的数据
     */
    @SuppressWarnings("unchecked")
    public <T> T findOneBy(String hql, Map<String, Object> params) {
        Query query = getSession().createQuery(hql);
        for (Entry<String, Object> param : params.entrySet()) {
            query.setParameter(param.getKey(), param.getValue());
        }
        return (T) query.getSingleResult();
    }

    public <T> T findOneBy(String hql) {
        Query query = getSession().createQuery(hql);
        return (T) query.getSingleResult();
    }

    /**
     * 执行条件写操作（通常指的是update和delete）
     *
     * @param hql    要执行的update或delete语句
     * @param params 参数名值对，注意，这里一定要使用命名参数的绑定形式
     * @return 本次操作所影响的数据量
     */
    public int executeUpdate(String hql, Map<String, Object> params) {
        Query query = getSession().createQuery(hql);
        for (Entry<String, Object> param : params.entrySet()) {
            query.setParameter(param.getKey(), param.getValue());
        }
        return query.executeUpdate();
    }

    public int executeUpdate(String hql) {
        Query query = getSession().createQuery(hql);
        return query.executeUpdate();
    }

    public <T> int delete(Class<T> clazz, Serializable id) {
        String hql = "DELETE FROM " + clazz.getName() + " WHERE id=:id";
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        return executeUpdate(hql, params);
    }

    /**
     * 通过Spring的ApplicationContext中配置的sessionFactory Bean得到当前线程上的一个Session对象
     *
     * @return 当前线程上的一个Session对象
     */
    public Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    /**
     * 通过Spring的ApplicationContext中配置的sessionFactory Bean打开一个新的Session对象
     *
     * @return 新的Session对象
     */
    public Session openSession() {
        return sessionFactory.openSession();
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
