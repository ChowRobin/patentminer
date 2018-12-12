package org.patentminer.dao;

import org.patentminer.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.mongodb.core.query.Query;

public interface UserDao {

    Page<User> paginationList(Query query, int pageNo, int pageSize);
}
