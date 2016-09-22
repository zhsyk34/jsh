package com.cat.jsh.dao.impl;

import com.cat.jsh.common.Page;
import com.cat.jsh.common.Sort;
import com.cat.jsh.dao.RoleDao;
import com.cat.jsh.dao.base.TemplateDao;
import com.cat.jsh.entity.Role;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

@Repository("roleDao2")
public class RoleDaoImpl2 implements RoleDao {

    @Resource
    private TemplateDao<Role, Long> templateDao;

    @Override
    public int save(Role role) {
        System.out.println(templateDao);
        templateDao.save(role);
        return 0;
    }

    @Override
    public int saves(Collection<Role> roles) {
        return 0;
    }

    @Override
    public int deleteById(Long aLong) {
        return 0;
    }

    @Override
    public int deleteByEntity(Role role) {
        return 0;
    }

    @Override
    public int deleteByIds(Long[] longs) {
        return 0;
    }

    @Override
    public int deleteByIds(Collection<Long> longs) {
        return 0;
    }

    @Override
    public int deleteByEntities(Collection<Role> roles) {
        return 0;
    }

    @Override
    public int update(Role role) {
        return 0;
    }

    @Override
    public int updates(Collection<Role> roles) {
        return 0;
    }

    @Override
    public int merge(Role role, String key) {
        return 0;
    }

    @Override
    public int merge(Role role) {
        return 0;
    }

    @Override
    public Role findById(Long aLong) {
        return null;
    }

    @Override
    public List<Role> findList(Sort sort, Page page) {
        return null;
    }

    @Override
    public List<Role> findList() {
        return null;
    }

    @Override
    public int count() {
        return 0;
    }

    @Override
    public List<Role> findList(String name, Sort sort, Page page) {
        return null;
    }

    @Override
    public int count(String name) {
        return 0;
    }
}
