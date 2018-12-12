package org.patentminer.service;

import org.patentminer.model.Patent;
import org.springframework.data.domain.Page;

import java.util.Map;

public interface PatentService {

    Page<Patent> listByCondition(Map<String,Object> parameterMap, int pageNo, int pageSize);

    String create(Patent patent);

    String update(Patent patent, String id);

    String delete(String id);
}
