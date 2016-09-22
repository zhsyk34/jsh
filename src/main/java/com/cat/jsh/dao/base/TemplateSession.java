package com.cat.jsh.dao.base;

import com.cat.jsh.kit.DaoKit;
import org.springframework.stereotype.Repository;

@Repository
class TemplateSession extends CustomSession {
    @Override
    protected String namespace() {
        long begin = System.nanoTime();
        String namespace = DaoKit.findSpace(TemplateDao.class);
        long end = System.nanoTime();
        System.out.println("namespace is : [" + namespace + "],spend : " + (end - begin) / 1000 + " .us");
        return namespace;
    }
}