package org.patentminer.service;

import org.patentminer.model.Inventor;
import org.patentminer.model.InventorDTO;

import java.util.List;

public interface InventorService {

    Inventor findById(Object id);

    String create(Inventor inventor);

    String update(Inventor inventor, String id);

    String delete(String id);

    InventorDTO findByName(String name);
}
