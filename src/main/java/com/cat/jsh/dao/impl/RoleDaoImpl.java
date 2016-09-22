package com.cat.jsh.dao.impl;

import com.cat.jsh.common.Page;
import com.cat.jsh.common.Sort;
import com.cat.jsh.dao.CustomSession;
import com.cat.jsh.dao.RoleDao;
import com.cat.jsh.entity.Role;
import com.cat.jsh.kit.BeanKit;
import com.cat.jsh.kit.DaoKit;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class RoleDaoImpl implements RoleDao {

	@Resource
	private CustomSession session;

	@Override
	public int save(Role role) {
		return session.insert(role);
	}

	@Override
	public int saves(Collection<Role> roles) {
		return session.insert(roles);
	}

	@Override
	public int deleteById(Long id) {
		return session.delete(id);
	}

	@Override
	public int deleteByEntity(Role role) {
		return session.delete(role);
	}

	@Override
	public int deleteByIds(Long[] ids) {
//		Collection<Long> list = Arrays.asList(ids);
		return session.delete(ids);
	}

	@Override
	public int deleteByIds(Collection<Long> ids) {
		return session.delete(ids.toArray());//TODO
	}

	@Override
	public int deleteByEntities(Collection<Role> roles) {
		return session.delete(roles);
	}

	@Override
	public int update(Role role) {
		return session.update(role);
	}

	@Override
	public int updates(Collection<Role> roles) {
		int count = 0;
		for (Role role : roles) {
			//count += session.update(role);//TODO:间接调用，namespace错误
			count += this.update(role);
		}
		return count;
	}

	@Override
	public int merge(Role role, String key) {
		//TODO
		Object value = BeanKit.getFieldValue(role, key);
		System.out.println("primary key [" + key + "] value is:" + value);
		return DaoKit.hasPersistent(value) ? this.update(role) : this.save(role);
	}

	@Override
	public int merge(Role role) {
		return this.merge(role, "id");//TODO 默认主键
	}

	@Override
	public Role findById(Long id) {
		return session.selectOne(id);
	}

	@Override
	public List<Role> findList(String name, Sort sort, Page page) {
		Map<String, Object> map = new HashMap<>();
		if (!StringUtils.isEmpty(name)) {
			map.put("name", name);
		}
		return session.selectList(map, sort, page);
	}

	@Override
	public List<Role> findList(Sort sort, Page page) {
		return this.findList(null, sort, page);
	}

	@Override
	public List<Role> findList() {
		return this.findList(null, null);
	}

	@Override
	public int count(String name) {
		Map<String, Object> map = new HashMap<>();
		if (!StringUtils.isEmpty(name)) {
			map.put("name", name);
		}
		return session.selectOne(map);
	}

	@Override
	public int count() {
		return session.selectOne();
	}
}
