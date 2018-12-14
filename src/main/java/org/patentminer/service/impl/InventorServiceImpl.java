package org.patentminer.service.impl;

import org.patentminer.exception.CheckException;
import org.patentminer.model.Inventor;
import org.patentminer.service.InventorService;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class InventorServiceImpl implements InventorService {

    @Resource
    private MongoTemplate mongoTemplate;

    @Override
    public Inventor findById(Object id) {
        return mongoTemplate.findById(id, Inventor.class);
    }

    @Override
    public String create(Inventor inventor) {
        return mongoTemplate.save(inventor).getId();
    }

    private Update getUpdate(Inventor inventor) {
        Update update = new Update();
        String name, nameCN;
        if ((name = inventor.getName()) != null) {
            update.set("name", name);
        } else if ((nameCN = inventor.getNameCN()) != null) {
            update.set("nameCN", nameCN);
        }
        return update;
    }

    @Override
    public String update(Inventor inventor, String id) {
        Query query = new Query(Criteria.where("id").is(id));
        if (mongoTemplate.findOne(query, Inventor.class) == null) {
            throw new CheckException("This inventor is not exists");
        } else {
            mongoTemplate.updateFirst(query, getUpdate(inventor), Inventor.class);
        }
        return id;
    }

    @Override
    public String delete(String id) {
        Inventor inventor;
        if ((inventor = mongoTemplate.findById(id, Inventor.class)) == null) {
            throw new CheckException("The inventor is not exists");
        } else {
            mongoTemplate.remove(inventor);
        }
        return id;
    }
}
