package org.patentminer.service;

import org.patentminer.model.User;
import org.springframework.data.domain.Page;

import java.util.Map;

public interface UserService {

    Page<User> listByCondition(Map<String, Object> map, int pageNo, int pageSize);

    String getPasswordByUserName(String userName);

    String login(String userName, String password);

    Integer register(User user);

    Integer update(Integer id, User user);

    Integer delete( Integer id);

    String getRole(String userName);
}
