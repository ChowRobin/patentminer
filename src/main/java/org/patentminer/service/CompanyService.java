package org.patentminer.service;

import org.patentminer.model.Company;
import org.patentminer.model.CompanyDTO;

public interface CompanyService {

    CompanyDTO findByName(String name);

    Company findById(Object id);

    String create(Company company);

    String update(Company company, String id);

    String delete(String id);
}
