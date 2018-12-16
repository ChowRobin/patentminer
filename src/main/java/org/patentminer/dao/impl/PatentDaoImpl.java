package org.patentminer.dao.impl;

import org.patentminer.bean.PageableImpl;
import org.patentminer.dao.PatentDao;
import org.patentminer.model.Patent;
import org.patentminer.model.PatentDTO;
import org.patentminer.service.PatentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class PatentDaoImpl implements PatentDao {

    @Resource
    private MongoTemplate mongoTemplate;

    @Autowired
    private PatentService patentService;

    @Override
    public Page<PatentDTO> paginationList(Query query, int pageNo, int pageSize) {
        Pageable pageable = new PageableImpl(pageNo, pageSize);
        List<Sort.Order> orders = new ArrayList<Sort.Order>();  //排序
        orders.add(new Sort.Order(Sort.Direction.ASC, "id"));
        Sort sort = new Sort(orders);
        ((PageableImpl) pageable).setSort(sort);

        Long totalRecord = mongoTemplate.count(query, Patent.class);


        return new PageImpl<PatentDTO>(mongoTemplate.find(query.with(pageable), Patent.class)
                .stream().map(p -> patentService.PO2DTO(p)).collect(Collectors.toList()),
                pageable, totalRecord);
//        return new PageImpl<>(mongoTemplate.find(query.with(pageable), PatentDTO.class),
//                pageable, totalRecord);
    }
}
