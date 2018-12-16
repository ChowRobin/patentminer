package org.patentminer.service;

import org.patentminer.model.Patent;
import org.patentminer.model.PatentBO;
import org.patentminer.model.PatentDTO;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface PatentService {

    Page<PatentDTO> listByCondition(Map<String,Object> parameterMap, int pageNo, int pageSize);

    PatentBO findBOById(String id);

    String create(Patent patent);

    String update(Patent patent, String id);

    String delete(String id);

    Patent DTO2PO(PatentDTO patentDTO);

    PatentDTO PO2DTO(Patent patent);

    List<PatentDTO> listByCompanyId(String companyId);

    List<PatentDTO> listByInventorId(String inventorId);
}
