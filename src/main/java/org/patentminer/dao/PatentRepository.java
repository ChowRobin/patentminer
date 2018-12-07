package org.patentminer.dao;

import org.patentminer.model.Patent;
import org.springframework.data.repository.CrudRepository;

public interface PatentRepository extends CrudRepository<Patent, String> {
}
