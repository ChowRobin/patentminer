package org.patentminer.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Document(collection = "patents_view")
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

    @Field("abstract")
    String abstractStr;

    @Field("abstract_cn")
    String abstractCN;

    @Field("invention_titles")
    List<String> inventionTitle;

    @Field("invention_titles_cn")
    List<String> inventionTitleCN;

    @Field("application_date")
    String applicationDate;

    @Field("publication_date")
    String publicationDate;

    List<Inventor> inventors;

    List<Company> companies;

    @Field("application_number")
    String applicationNumber;

    @Field("publication_number")
    String publicationNumber;

    @Field("ipc")
    List<String> ipcs;
}
