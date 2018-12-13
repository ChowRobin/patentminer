package org.patentminer.service.impl;

import org.patentminer.model.Company;
import org.patentminer.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public Company findById(Object id) {
        return mongoTemplate.findById(id, Company.class);
    }
}
