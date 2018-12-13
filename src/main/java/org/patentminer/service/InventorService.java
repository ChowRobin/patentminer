package org.patentminer.service;

import org.patentminer.model.Inventor;

public interface InventorService {

    Inventor findById(Object id);
}
