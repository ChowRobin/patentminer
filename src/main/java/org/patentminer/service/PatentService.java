package org.patentminer.service;

import org.patentminer.model.Patent;

import java.util.List;
import java.util.Map;

public interface PatentService {

    List<Patent> listByCondition(Map<String,Object> parameterMap, int pageNo, int pageSize);

    String create(Patent patent);

    String update(Patent patent, String id);

    String delete(String id);
}
