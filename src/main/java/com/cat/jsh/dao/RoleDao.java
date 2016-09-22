package com.cat.jsh.dao;

import com.cat.jsh.common.CommonDao;
import com.cat.jsh.common.Page;
import com.cat.jsh.common.Sort;
import com.cat.jsh.entity.Role;

import java.util.List;

public interface RoleDao extends CommonDao<Role, Long> {

	List<Role> findList(String name, Sort sort, Page page);

	int count(String name);
}
