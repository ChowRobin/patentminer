package org.patentminer.dao;

import org.patentminer.model.PatentDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.mongodb.core.query.Query;

public interface PatentDao {

    Page<PatentDTO> paginationList(Query query, int pageNo, int pageSize);
}
