package org.patentminer.model;

import lombok.Data;

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

    String nameCN;

    List<PatentDTO> patents;
}
