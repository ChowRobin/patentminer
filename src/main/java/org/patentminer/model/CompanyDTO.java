package org.patentminer.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Data
public class CompanyDTO {

    public CompanyDTO() {}

    public CompanyDTO(Company company) {
        this.setId(company.getId());
        this.setName(company.getName());
        this.setNameCN(company.getNameCN());
    }

    String id;

    String name;

    @Field("name_cn")
    String nameCN;

    List<PatentDTO> patents;
}
