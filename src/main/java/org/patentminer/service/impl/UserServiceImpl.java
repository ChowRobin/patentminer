package org.patentminer.service.impl;

import io.swagger.models.auth.In;
import org.patentminer.dao.UserRepository;
import org.patentminer.exception.CheckException;
import org.patentminer.model.User;
import org.patentminer.service.UserService;
import org.patentminer.util.JWTUtil;
import org.patentminer.util.MongoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Resource
    private MongoTemplate mongoTemplate;

    @Autowired
    private MongoUtil mongoUtil;

    @Override
    public List<User> listByCondition(Map<String, Object> map, int pageNo, int pageSize) {
        map.remove("pageNo");
        map.remove("pageSize");
        Query query = new Query();
        map.forEach((k, v) -> {
            if (k.equals("id")) {
                v = Integer.parseInt((String) v);
            }
            query.addCriteria(Criteria.where(k).is(v));
        });
        query.skip(pageNo - 1 * pageSize).limit(pageSize);
        return mongoTemplate.find(query, User.class);
    }

    private User findOneByCondition(String k, String v) {
        return mongoTemplate.findOne(new Query(Criteria.where(k).is(v)), User.class);
    }

    @Override
    public String login(User user) {
        User userInDB = findOneByCondition("userName", user.getUserName());
        if (userInDB == null) {
            throw new CheckException("user not exits!");
        } else if (!userInDB.getPassword().equals(user.getPassword())) {
            throw new CheckException("password is not current!");
        } else {
            return JWTUtil.sign(userInDB.getUserName(), userInDB.getPassword());
        }
    }

    @Override
    public Integer register(User user) {
        if (findOneByCondition("userName", user.getUserName()) != null) {
            throw new CheckException("user has exists!");
        } else {
            user.setId(mongoUtil.getNextSequence("users"));
            userRepository.save(user);
        }
        return user.getId();
    }
}
