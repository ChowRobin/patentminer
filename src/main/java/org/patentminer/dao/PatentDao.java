package org.patentminer.dao;

import org.patentminer.model.PatentDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public interface PatentDao {

    Page<PatentDTO> paginationList(Query query, int pageNo, int pageSize);

}
