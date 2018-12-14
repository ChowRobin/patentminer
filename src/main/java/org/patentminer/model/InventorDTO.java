package org.patentminer.model;

import lombok.Data;

import java.util.List;

@Data
public class InventorDTO {

    public InventorDTO(Inventor inventor) {
        this.setId(inventor.getId());
        this.setName(inventor.getName());
        this.setNameCN(inventor.getNameCN());
    }

    String id;

    String name;

    String nameCN;

    List<PatentDTO> patents;
}
