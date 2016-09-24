package com.cat.jsh.dao.base;

import org.apache.ibatis.cursor.Cursor;
import org.apache.ibatis.executor.BatchResult;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.sql.Connection;
import java.util.List;
import java.util.Map;

@Repository
public class ReboundSession {

	@Resource
	private SqlSessionTemplate session;

	public <T> T selectOne(String statement) {
		return session.selectOne(statement);
	}

	public <T> T selectOne(String statement, Object parameter) {
		return null;
	}

	public <E> List<E> selectList(String statement) {
		return null;
	}

	public <E> List<E> selectList(String statement, Object parameter) {
		return null;
	}

	public <E> List<E> selectList(String statement, Object parameter, RowBounds rowBounds) {
		return null;
	}

	public <K, V> Map<K, V> selectMap(String statement, String mapKey) {
		return null;
	}

	public <K, V> Map<K, V> selectMap(String statement, Object parameter, String mapKey) {
		return null;
	}

	public <K, V> Map<K, V> selectMap(String statement, Object parameter, String mapKey, RowBounds rowBounds) {
		return null;
	}

	public <T> Cursor<T> selectCursor(String statement) {
		return null;
	}

	public <T> Cursor<T> selectCursor(String statement, Object parameter) {
		return null;
	}

	public <T> Cursor<T> selectCursor(String statement, Object parameter, RowBounds rowBounds) {
		return null;
	}

	public void select(String statement, Object parameter, ResultHandler handler) {

	}

	public void select(String statement, ResultHandler handler) {

	}

	public void select(String statement, Object parameter, RowBounds rowBounds, ResultHandler handler) {

	}

	public int insert(String statement) {
		return session.insert(statement);
	}

	public int insert(String statement, Object parameter) {
		return session.insert(statement, parameter);
	}

	public int update(String statement) {
		return 0;
	}

	public int update(String statement, Object parameter) {
		return 0;
	}

	public int delete(String statement) {
		return 0;
	}

	public int delete(String statement, Object parameter) {
		return 0;
	}

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
}
