package com.xiaomai.supershopowner.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;



public abstract class BaseDaoImpl<T, KEY extends Serializable> extends MyBatisSupport implements BaseDao<T, KEY>{

	public static final String INSERT = "insert";
	public static final String DELETE = "delete";
	public static final String UPDATE = "update";
	public static final String FINDBYID = "findById";
	public static final String FINDLISTCOUNT = "findListCount";
	public static final String FINDLIST= "findList";
	public static final String FINDLISTALL = "findListAll";
	public static final String FINDLISTALLWITHMAP = "findListAllWithMap";

	private Class<T> persistentClass;

	@SuppressWarnings("unchecked")
	public BaseDaoImpl() {
		this.persistentClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

	public Class<T> getPersistentClass() {
		return persistentClass;
	}

	public void setPersistentClass(Class<T> persistentClass) {
		this.persistentClass = persistentClass;
	}

	/**
	 * 获取命名空前前缀
	 * 
	 * @return
	 */
	public abstract String getNameSpace();

	public String getStatement(String sql) {
		return getNameSpace() + "." + sql;
	};

	@Override
	public  int insert(Object parameter) throws SQLException {
		String statement = this.getStatement(INSERT);
		return getSqlSessionTemplate().insert(statement, parameter);
	}

	@Override
	public int delete(Object parameter) throws SQLException{
		String statement = this.getStatement(DELETE);
		return getSqlSessionTemplate().delete(statement, parameter);
	}

	@Override
	public int update(Object parameter) throws SQLException{
		String statement = this.getStatement(UPDATE);
		return getSqlSessionTemplate().update(statement, parameter);
	}
	public Integer findListCount(HashMap<String, Object> map) throws SQLException {
		return ((Number) selectOne(getStatement(FINDLISTCOUNT),map)).intValue();
	}
	
	@SuppressWarnings({ "unchecked", "hiding" })
	@Override
	public <T> List<T> selectList(Object parameter) throws SQLException{
		String statement = this.getStatement(FINDLIST);
		return (List<T>) getSqlSessionTemplate().selectList(statement,parameter);
	}
	
	public T findById(KEY id){
		return this.getSqlSessionTemplate().selectOne(this.getStatement(FINDBYID), id);
	}
	
	@SuppressWarnings("unchecked")
	public T selectOne(String statement)  {
		return (T) getSqlSessionTemplate().selectOne(statement);
	}
	
    public <T> List<T> selectList(String statement, Object parameter) throws SQLException{
        return (List<T>) getSqlSessionTemplate().selectList(statement,
                parameter);
    }
	@SuppressWarnings({ "unchecked", "hiding" })
	public <T> T selectOne(String statement, Object parameter) throws SQLException {
		return (T) getSqlSessionTemplate().selectOne(statement, parameter);
	}
	
	public List<T> findListAll() throws SQLException{
		return this.getSqlSessionTemplate().selectList(getStatement(FINDLISTALL));
	}
	
	public List<T> findListAllWithMap(Map<String,Object> paramsMap) throws SQLException{
		return this.getSqlSessionTemplate().selectList(getStatement(FINDLISTALLWITHMAP), paramsMap);
	}
	
	public List<T> findList(HashMap<String, Object> map) throws SQLException {
		return  selectList(getStatement(FINDLIST), map);
	}

	private Map<?, ?> toParameterMap(Object parameter) {
		if (parameter == null) {
			return new HashMap<Object, Object>();
		}
		if (parameter instanceof Map) {
			return (Map<?, ?>) parameter;
		} else {
			try {
				return PropertyUtils.describe(parameter);
			} catch (Exception e) {
				return null;
			}
		}
	}

}
