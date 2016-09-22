package com.cat.jsh.dao.impl;

import com.cat.jsh.dao.InitTestDao;
import com.cat.jsh.dao.RoleDao;
import com.cat.jsh.entity.Role;
import org.junit.Test;

import javax.annotation.Resource;

public class RoleDaoImpl2Test extends InitTestDao {

    @Resource
    private RoleDao roleDao2;

    @Test
    public void save() throws Exception {
        System.out.println(roleDao2);
        roleDao2.save(new Role(null, "gm", null, null));
    }

}