package com.cat.jsh.dao.base;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 为SqlSessionTemplate常用方法提供默认statement
 * statement 为调用者的接口包名+方法名
 */
abstract class CustomSession {

	@Resource
	protected SqlSessionTemplate session;

	public SqlSession session() {
		return session;
	}

	protected abstract String namespace();

	public <T> T selectOne() {
		return session.selectOne(namespace());
	}

	public <T> T selectOne(Object parameter) {
		return session.selectOne(namespace(), parameter);
	}

	public <E> List<E> selectList() {
		return session.selectList(namespace());
	}

	public <E> List<E> selectList(Object parameter) {
		return session.selectList(namespace(), parameter);
	}

	public <E> List<E> selectList(Object parameter, RowBounds rowBounds) {
		return session.selectList(namespace(), parameter, rowBounds);
	}

	public <K, V> Map<K, V> selectMap(String mapKey) {
		return session.selectMap(namespace(), mapKey);
	}

	public <K, V> Map<K, V> selectMap(Object parameter, String mapKey) {
		return session.selectMap(namespace(), parameter, mapKey);
	}

	public <K, V> Map<K, V> selectMap(Object parameter, String mapKey, RowBounds rowBounds) {
		return session.selectMap(namespace(), parameter, mapKey, rowBounds);
	}

    /*
	public <T> Cursor<T> selectCursor() {
        return null;
    }

    public <T> Cursor<T> selectCursor(Object parameter) {
        return null;
    }

    public <T> Cursor<T> selectCursor(Object parameter, RowBounds rowBounds) {
        return null;
    }

    public void select(Object parameter, ResultHandler handler) {

    }

    public void select(ResultHandler handler) {

    }

    public void select(Object parameter, RowBounds rowBounds, ResultHandler handler) {

    }
    */

	public int insert() {
		return session.insert(namespace());
	}

	public int insert(Object parameter) {
		return session.insert(namespace(), parameter);
	}

	public int update() {
		return session.update(namespace());
	}

	public int update(Object parameter) {
		return session.update(namespace(), parameter);
	}

	public int delete() {
		return session.delete(namespace());
	}

	public int delete(Object parameter) {
		return session.delete(namespace(), parameter);
	}

	/*
	public void commit() {

	}

	public void commit(boolean force) {

	}

	public void rollback() {

	}

	public void rollback(boolean force) {

	}

	public List<BatchResult> flushStatements() {
		return null;
	}

	public void close() {

	}

	public void clearCache() {

	}

	public Configuration getConfiguration() {
		return null;
	}

	public <T> T getMapper(Class<T> type) {
		return null;
	}

	public Connection getConnection() {
		return null;
	}
	*/
}
