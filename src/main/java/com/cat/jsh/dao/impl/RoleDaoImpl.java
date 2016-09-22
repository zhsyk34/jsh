package com.cat.jsh.dao.impl;

import com.cat.jsh.common.Page;
import com.cat.jsh.common.Sort;
import com.cat.jsh.dao.base.CustomSession;
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

@Repository("roleDao")
public class RoleDaoImpl implements RoleDao {

    @Resource
    private CustomSession customSession;

    @Override
    public int save(Role role) {
        return customSession.insert(role);
    }

    @Override
    public int saves(Collection<Role> roles) {
        return customSession.insert(roles);
    }

    @Override
    public int deleteById(Long id) {
        return customSession.delete(id);
    }

    @Override
    public int deleteByEntity(Role role) {
        return customSession.delete(role);
    }

    @Override
    public int deleteByIds(Long[] ids) {
//		Collection<Long> list = Arrays.asList(ids);
        return customSession.delete(ids);
    }

    @Override
    public int deleteByIds(Collection<Long> ids) {
        return customSession.delete(ids.toArray());//TODO
    }

    @Override
    public int deleteByEntities(Collection<Role> roles) {
        return customSession.delete(roles);
    }

    @Override
    public int update(Role role) {
        return customSession.update(role);
    }

    @Override
    public int updates(Collection<Role> roles) {
        int count = 0;
        for (Role role : roles) {
            //count += customSession.update(role);//TODO:间接调用，namespace错误
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
        return customSession.selectOne(id);
    }

    @Override
    public List<Role> findList(String name, Sort sort, Page page) {
        Map<String, Object> map = new HashMap<>();
        if (!StringUtils.isEmpty(name)) {
            map.put("name", name);
        }
        return customSession.selectList(map, sort, page);
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
        return customSession.selectOne(map);
    }

    @Override
    public int count() {
        return customSession.selectOne();
    }
}
