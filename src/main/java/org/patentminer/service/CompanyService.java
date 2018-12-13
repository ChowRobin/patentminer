package org.patentminer.service;

import org.patentminer.model.Company;
import org.springframework.stereotype.Service;

public interface CompanyService {

    Company findById(Object id);
}
