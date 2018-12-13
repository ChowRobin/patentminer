package org.patentminer.service.impl;

import org.patentminer.model.Inventor;
import org.patentminer.service.InventorService;
import org.springframework.data.mongodb.core.MongoTemplate;
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
}
