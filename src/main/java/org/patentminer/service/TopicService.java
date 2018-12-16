package org.patentminer.service;

import org.patentminer.model.Topic;
import org.patentminer.model.TopicDTO;
import org.patentminer.model.TopicDetail;

import java.util.List;

public interface TopicService {

    Topic findByName(String name);

    Integer create(Topic topic);

    Integer update(Topic topic, int id);

    Integer delete(Integer id);

    List<TopicDTO> listAll();

    TopicDetail findDetailById(int id);
}
