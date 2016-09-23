package com.cat.jsh.dao.base;

import com.cat.jsh.common.CommonDao;
import com.cat.jsh.common.Page;
import com.cat.jsh.common.Sort;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

@Repository
public class TemplateDao<Entity, Id> implements CommonDao<Entity, Id> {

	@Resource
	private TemplateSession templateSession;

	@Override
	public int save(Entity entity) {
		return templateSession.insert(entity);
	}

	@Override
	public int saves(Collection<Entity> entities) {
		return 0;
	}

	@Override
	public int deleteById(Id id) {
		return 0;
	}

	@Override
	public int deleteByEntity(Entity entity) {
		return 0;
	}

	@Override
	public int deleteByIds(Id[] ids) {
		return 0;
	}

	@Override
	public int deleteByIds(Collection<Id> ids) {
		return 0;
	}

	@Override
	public int deleteByEntities(Collection<Entity> entities) {
		return 0;
	}

	@Override
	public int update(Entity entity) {
		return 0;
	}

	@Override
	public int updates(Collection<Entity> entities) {
		return 0;
	}

	@Override
	public int merge(Entity entity, String key) {
		return 0;
	}

	@Override
	public int merge(Entity entity) {
		return 0;
	}

	@Override
	public Entity findById(Id id) {
		return null;
	}

	@Override
	public List<Entity> findList(Sort sort, Page page) {
		return null;
	}

	@Override
	public List<Entity> findList() {
		return null;
	}

	@Override
	public int count() {
		return 0;
	}
}


