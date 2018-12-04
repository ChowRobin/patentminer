package org.patentminer.service;

import org.patentminer.model.User;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface UserService {

    List<User> listAll(HttpServletRequest request, int pageNo, int pageSize);

    User findById(int id);
}
