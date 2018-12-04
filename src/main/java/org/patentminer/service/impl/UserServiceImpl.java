package org.patentminer.service.impl;

import org.patentminer.dao.UserRepository;
import org.patentminer.model.User;
import org.patentminer.service.UserService;
import org.patentminer.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Resource
    private MongoTemplate mongoTemplate;

    @Override
    public List<User> listAll(HttpServletRequest request, int pageNo, int pageSize) {
        return (List<User>) userRepository.findAll();
    }

    @Override
    public User findById(int id) {
//        return (User) CommonUtil.unboxOptional(userRepository.findById(id));
        Query query = new Query(Criteria.where("id").is(id));
        User user = mongoTemplate.findOne(query, User.class);
        return user;
    }
}
