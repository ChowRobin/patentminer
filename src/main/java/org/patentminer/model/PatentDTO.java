package org.patentminer.model;

import lombok.Data;

import java.util.List;

@Data
public class PatentDTO {

    public PatentDTO() {}

    public PatentDTO(Patent patent) {
        this.id = patent.getId();
        this.abstractStr = patent.getAbstractStr();
        this.abstractCN = patent.getAbstractCN();
        this.inventionTitle = patent.getInventionTitle();
        this.inventionTitleCN = patent.getInventionTitleCN();
        this.applicationDate = patent.getApplicationDate();
        this.publicationDate = patent.getPublicationDate();
        this.applicationNumber = patent.getApplicationNumber();
        this.publicationNumber = patent.getPublicationNumber();
        this.ipcs = patent.getIpcs();
    }

    String id;

    String abstractStr;

    String abstractCN;

    List<String> inventionTitle;

    List<String> inventionTitleCN;

    String applicationDate;

    String publicationDate;

    List<Inventor> inventors;

    List<Company> companies;

    String applicationNumber;

    String publicationNumber;

    List<String> ipcs;
}
