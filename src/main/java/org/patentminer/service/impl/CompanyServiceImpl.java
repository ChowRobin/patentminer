package org.patentminer.service.impl;

import org.patentminer.exception.CheckException;
import org.patentminer.model.Company;
import org.patentminer.service.CompanyService;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Resource
    private MongoTemplate mongoTemplate;

    @Override
    public Company findById(Object id) {
        return mongoTemplate.findById(id, Company.class);
    }

    @Override
    public String create(Company company) {
        return mongoTemplate.save(company).getId();
    }

    private Update getUpdate(Company company) {
        Update update = new Update();
        String name, nameCN;
        if ((name = company.getName()) != null) {
            update.set("name", name);
        } else if ((nameCN = company.getNameCN()) != null) {
            update.set("nameCN", nameCN);
        }
        return update;
    }

    @Override
    public String update(Company company, String id) {
        Query query = new Query(Criteria.where("id").is(id));
        if (mongoTemplate.findOne(query, Company.class) == null) {
            throw new CheckException("This Company is not exists");
        } else {
            mongoTemplate.updateFirst(query, getUpdate(company), Company.class);
        }
        return id;
    }

    @Override
    public String delete(String id) {
        Company company;
        if ((company = mongoTemplate.findById(id, Company.class)) == null) {
            throw new CheckException("Company is not exists");
        } else {
            mongoTemplate.remove(company);
        }
        return id;
    }
}
