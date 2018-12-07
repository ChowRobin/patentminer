package org.patentminer.service.impl;

import org.patentminer.dao.PatentRepository;
import org.patentminer.model.Patent;
import org.patentminer.service.PatentService;
import org.patentminer.util.MongoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
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
}
