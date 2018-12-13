package org.patentminer.model;

import lombok.Data;

import java.util.List;

@Data
public class PatentDTO {

    public PatentDTO() {}

    public PatentDTO(Patent patent) {
        this.id = patent.getId();
        this.abstractStr = patent.getAbstractStr();
        this.abstractCn = patent.getAbstractCn();
        this.inventionTitle = patent.getInventionTitle();
        this.inventionTitleCn = patent.getInventionTitleCn();
        this.applicationDate = patent.getApplicationDate();
        this.publicationDate = patent.getPublicationDate();
        this.applicationNumber = patent.getApplicationNumber();
        this.publicationNumber = patent.getPublicationNumber();
        this.ipcs = patent.getIpcs();
    }

    String id;

    String abstractStr;

    String abstractCn;

    List<String> inventionTitle;

    List<String> inventionTitleCn;

    String applicationDate;

    String publicationDate;

    List<Inventor> inventors;

    List<Company> companies;

    String applicationNumber;

    String publicationNumber;

    List<String> ipcs;
}
