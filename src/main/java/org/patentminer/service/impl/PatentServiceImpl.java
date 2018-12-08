package org.patentminer.service.impl;

import org.patentminer.dao.PatentRepository;
import org.patentminer.exception.CheckException;
import org.patentminer.model.Patent;
import org.patentminer.service.PatentService;
import org.patentminer.util.CommonUtil;
import org.patentminer.util.MongoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class PatentServiceImpl implements PatentService {

    @Autowired
    private PatentRepository patentRepository;

    @Resource
    private MongoTemplate mongoTemplate;

    @Autowired
    private MongoUtil mongoUtil;

    @Override
    public List<Patent> listByCondition(Map<String, Object> parameterMap, int pageNo, int pageSize) {
        parameterMap.remove("pageNo");
        parameterMap.remove("pageSize");
        Query query = new Query();
        parameterMap.forEach((k, v)->{
            if (v instanceof Integer) {
                v = Integer.parseInt((String) v);
            }
            query.addCriteria(Criteria.where(k).is(v));
        });
        query.skip(pageNo - 1).limit(pageSize);
        return mongoTemplate.find(query, Patent.class);
    }

    @Override
    public String create(Patent patent) {
        return patentRepository.save(patent).getId();
    }

    private Update getUpdate(Patent patent) {
        Update update = new Update();
        String abstractStr, applicationDate, inventionTitle, publicationDate;
        if ((abstractStr = patent.getAbstractStr()) != null) {
            update.set("abstractStr", abstractStr);
        }
        if ((applicationDate = patent.getApplicationDate()) != null) {
            update.set("applicationDate", applicationDate);
        }
        if ((publicationDate = patent.getPublicationDate()) != null) {
            update.set("publicationDate", publicationDate);
        }
        if ((inventionTitle = patent.getInventionTitle()) != null) {
            update.set("inventionTitle", inventionTitle);
        }
        return update;
    }

    @Override
    public String update(Patent patent, String id) {
        Query query = new Query(Criteria.where("id").is(id));
        if (mongoTemplate.findOne(query, Patent.class) == null) {
            throw new CheckException("This patent not exists.");
        } else {
            mongoTemplate.updateFirst(query, getUpdate(patent), Patent.class);
        }
        return id;
    }

    @Override
    public String delete(String id) {
        if (CommonUtil.unboxOptional(patentRepository.findById(id)) == null) {
            throw new CheckException("patent not exists.");
        } else {
            patentRepository.deleteById(id);
        }
        return id;
    }

}
