package org.patentminer.service.impl;

import org.patentminer.exception.CheckException;
import org.patentminer.model.Inventor;
import org.patentminer.model.Topic;
import org.patentminer.model.TopicDTO;
import org.patentminer.model.TopicDetail;
import org.patentminer.service.CompanyService;
import org.patentminer.service.InventorService;
import org.patentminer.service.PatentService;
import org.patentminer.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TopicServiceImpl implements TopicService {

    @Resource
    private MongoTemplate mongoTemplate;

    @Autowired
    private PatentService patentService;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private InventorService inventorService;

    @Override
    public List<TopicDTO> listAll() {
        return mongoTemplate.findAll(Topic.class).stream().map(topic -> PO2DTO(topic)).collect(Collectors.toList());
    }

    private TopicDTO PO2DTO(Topic topic) {
        TopicDTO topicDTO = new TopicDTO();
        topicDTO.setId(topic.getId());
        topicDTO.setName(topic.getName());
        topicDTO.setNameCN(topic.getNameCN());
        if (topic.getCompanyIds() != null) {
            topicDTO.setCompanies(topic.getCompanyIds()
                    .stream().map(id -> companyService.findById(id)).collect(Collectors.toList()));
        }
        if (topic.getInventorIds() != null) {
            topicDTO.setInventors(topic.getInventorIds()
                    .stream().map(id -> inventorService.findById(id)).collect(Collectors.toList()));
        }
        return topicDTO;
    }

    @Override
    public Topic findByName(String name) {
        Query query = new Query();
        if (name != null) {
            query.addCriteria(
                    new Criteria().orOperator(
                            Criteria.where("name").regex(".*?" + name + ".*"),
                            Criteria.where("nameCN").regex(".*?" + name + ".*")
                    )
            );
        }
        Topic topic = mongoTemplate.findOne(query, Topic.class);
        return topic;
    }

    @Override
    public TopicDetail findDetailById(int id) {
        return PO2Detail(mongoTemplate.findById(id, Topic.class));
    }

    private TopicDetail PO2Detail(Topic topic) {
        if (topic == null) return null;
        TopicDetail topicDetail = new TopicDetail();
        topicDetail.setId(topic.getId());
        topicDetail.setName(topic.getName());
        topicDetail.setNameCN(topic.getNameCN());
        topicDetail.setHotWords(topic.getHotWords());
        if (topic.getCompanyIds() != null) {
            topicDetail.setCompanies(topic.getCompanyIds()
                    .stream().map(id -> companyService.findById(id)).collect(Collectors.toList()));
        }
        if (topic.getInventorIds() != null) {
            topicDetail.setInventors(topic.getInventorIds()
                    .stream().map(id -> inventorService.findById(id)).collect(Collectors.toList()));
        }
        if (topic.getPatentIds() != null) {
            topicDetail.setRelevantPatents(topic.getPatentIds()
                    .stream().map(id -> patentService.findBOById(id)).collect(Collectors.toList()));
        }

        return topicDetail;
    }

    @Override
    public Integer create(Topic topic) {
        return mongoTemplate.save(topic).getId();
    }

    private Update getUpdate(Topic topic) {
        Update update = new Update();
        String name, nameCN;
        if ((name = topic.getName()) != null) {
            update.set("name", name);
        } else if ((nameCN = topic.getNameCN()) != null) {
            update.set("nameCN", nameCN);
        }
        return update;
    }

    @Override
    public Integer update(Topic topic, int id) {
        Query query = new Query(Criteria.where("id").is(id));
        if (mongoTemplate.findOne(query, Topic.class) == null) {
            throw new CheckException("This topic is not exists");
        } else {
            mongoTemplate.updateFirst(query, getUpdate(topic), Topic.class);
        }
        return id;
    }

    @Override
    public Integer delete(Integer id) {
        Topic topic;
        if ((topic = mongoTemplate.findById(id, Topic.class)) == null) {
            throw new CheckException("The topic is not exists");
        } else {
            mongoTemplate.remove(topic);
        }
        return id;
    }
}
