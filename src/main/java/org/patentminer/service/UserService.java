package org.patentminer.service;

import io.swagger.models.auth.In;
import org.patentminer.model.User;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface UserService {

    List<User> listByCondition(Map<String, Object> map, int pageNo, int pageSize);

    String getPasswordByUserName(String userName);

    String login(String userName, String password);

    Integer register(User user);

    Integer update(Integer id, User user);

    Integer delete(Integer id);

    String getRole(String userName);
}
