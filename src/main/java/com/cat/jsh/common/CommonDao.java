package com.cat.jsh.common;

import java.util.Collection;
import java.util.List;

public interface CommonDao<Entity, Id> {

	int save(Entity entity);

	int saves(Collection<Entity> entities);

	int deleteById(Id id);

	int deleteByEntity(Entity entity);

	int deleteByIds(Id[] ids);

	int deleteByIds(Collection<Id> ids);

	int deleteByEntities(Collection<Entity> entities);

	int update(Entity entity);

	int updates(Collection<Entity> entities);

	int merge(Entity entity, String key);

	int merge(Entity entity);

	Entity findById(Id id);

	//List<Entity> findList(Map<String, Object> query, Sort sort, Page page);

	List<Entity> findList(Sort sort, Page page);

	List<Entity> findList();

	//int count(Map<String, Object> query);

	int count();

}
