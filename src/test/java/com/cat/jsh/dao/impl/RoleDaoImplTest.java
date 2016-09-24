package com.cat.jsh.dao.impl;

import com.cat.jsh.common.Page;
import com.cat.jsh.common.Sort;
import com.cat.jsh.dao.InitTestDao;
import com.cat.jsh.dao.RoleDao;
import com.cat.jsh.entity.Role;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

public class RoleDaoImplTest extends InitTestDao {

	@Resource
	private RoleDao roleDao;
	@Resource
	private RoleDao roleDao;

	@Test
	public void save22() throws Exception {
		Role role = new Role(null, "yes", null, null);
		System.out.println(roleDao.save(role));
		System.out.println(role.getRoleId());
	}

	@Test
	public void save() throws Exception {
		Role role = new Role(null, "yes", null, null);
		System.out.println(roleDao.save(role));
		System.out.println(role.getRoleId());
	}

	@Test
	public void saves() throws Exception {
		List<Role> list = new ArrayList<>();
		for (int i = 1; i < 4; i++) {
			list.add(new Role(null, "role" + i, null, null));
		}
		System.out.println(roleDao.saves(list));
		list.forEach(System.out::println);
	}

	@Test
	public void deleteById() throws Exception {
		int r = roleDao.deleteById(1L);
		System.out.println(r);
		r = roleDao.deleteById(21L);
		System.out.println(r);
	}

	@Test
	public void deleteByEntity() throws Exception {
		Role role = roleDao.findById(2L);
		System.out.println(role);
		int r = roleDao.deleteByEntity(role);
		System.out.println(r);
	}

	@Test
	public void deleteByIds() throws Exception {
		int r = roleDao.deleteByIds(new Long[]{17L, 13L, 9L});
		System.out.println(r);
	}

	@Test
	public void deleteByIds1() throws Exception {
		List<Long> list = new ArrayList<>();
		list.add(8L);
		list.add(18L);
		list.add(3L);
		list.add(5L);
		int r = roleDao.deleteByIds(list);
		System.out.println(r);
	}

	@Test
	public void deleteByEntities() throws Exception {
		List<Role> list = roleDao.findList(null, Sort.of("roleId", Sort.DESC), Page.of(3, 3));
		list.forEach(System.out::println);

		int i = roleDao.deleteByEntities(list);
		System.out.println(i);
	}

	@Test
	public void update() throws Exception {
		Role role = roleDao.findById(3L);
		int r = roleDao.update(role);
		System.out.println(r);

		role = roleDao.findById(20L);
		if (role != null) {
			role.setName("a b c");
			r = roleDao.update(role);
			System.out.println(r);
		}
	}

	@Test
	public void updates() throws Exception {
		List<Role> list = roleDao.findList(null, Sort.of("roleId", Sort.DESC), Page.of(3, 3));
		list.forEach(role -> {
			System.out.println(role);
			role.setName("xyz,abc");
		});
		int r = roleDao.updates(list);
		System.out.println(r);
	}

	@Test
	public void merge() throws Exception {
		Role role = new Role();
		role.setRoleId(2L);
		role.setName("merge");
		int r = roleDao.merge(role, "roleId");
		System.out.println(r);
	}

	@Test
	public void merge1() throws Exception {

	}

	@Test
	public void findById() throws Exception {
		System.out.println(roleDao.findById(1L));
		System.out.println(roleDao.findById(14L));
	}

	@Test
	public void findList() throws Exception {
		List<Role> list = roleDao.findList(null, null, Page.of(3, 7));
		list.forEach(System.out::println);

		list = roleDao.findList(null, Sort.of("roleId", Sort.DESC), Page.of(3, 3));
		list.forEach(System.out::println);
	}

	@Test
	public void findList1() throws Exception {
		List<Role> list = roleDao.findList();
		list.forEach(System.out::println);

		list = roleDao.findList(null, null);
		list.forEach(System.out::println);

		list = roleDao.findList(null, null, null);
		list.forEach(System.out::println);
	}

	@Test
	public void findList2() throws Exception {
		List<Role> list = roleDao.findList("1", null, null);
		list.forEach(System.out::println);
		list = roleDao.findList("a", null, null);
		list.forEach(System.out::println);
		list = roleDao.findList("1", Sort.of("roleId", Sort.DESC), Page.of(1, 2));
		list.forEach(System.out::println);
		Page.of(3, 7);
	}

	@Test
	public void count() throws Exception {
		System.out.println(roleDao.count("1"));
		System.out.println(roleDao.count());
	}

}