package org.patentminer.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.MongoClient;
import org.patentminer.config.MongoConfig;
import org.patentminer.model.Patent;
import org.patentminer.model.Topic;
import org.patentminer.service.PatentService;
import org.patentminer.service.TopicService;
import org.patentminer.service.impl.TopicServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;
import springfox.documentation.spring.web.json.Json;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

@Component
public class JSONUtil {

    private static ObjectMapper MAPPER = new ObjectMapper();

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private TopicService topicService;

    @Autowired
    private PatentService patentService;

    public static void main(String[] args) {
        JSONUtil jsonUtil = new JSONUtil();
        jsonUtil.importTopicJson();
    }

    public void importTopicJson() {
        try {
            FileReader fileReader = new FileReader(new File("/Users/robin/Downloads/PatentTopic15.json"));
            BufferedReader br = new BufferedReader(fileReader);
            String json = br.readLine();
            Map<String, Object> map = MAPPER.readValue(json, HashMap.class);
//            for (int i = 0; i <= 14; ++i) {
//                Topic topic = new Topic();
//                topic.setId(i);
//                topic.setName("topic" + i);
//                topic.setNameCN("主题" + i);
//                topicService.create(topic);
//            }
            Map<String, Object> cMap = new HashMap<>();
            map.forEach((k, v) -> {
//                cMap.put("publicationNumber", k);
                Query query = new Query(Criteria.where("publicationNumber").is(k));
                Patent patent = mongoTemplate.findOne(query, Patent.class);
                patent.setTopicId((Integer) v);
                patentService.update(patent, patent.getId());
            });
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
