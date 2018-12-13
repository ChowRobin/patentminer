package org.patentminer.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Document(collection = "patents")
public class Patent {

    public Patent() {}

    public Patent(PatentDTO patentDTO) {
        this.id = patentDTO.getId();
        this.abstractStr = patentDTO.getAbstractStr();
        this.abstractCn = patentDTO.getAbstractCn();
        this.inventionTitle = patentDTO.getInventionTitle();
        this.inventionTitleCn = patentDTO.getInventionTitleCn();
        this.applicationDate = patentDTO.getApplicationDate();
        this.publicationDate = patentDTO.getPublicationDate();
        this.applicationNumber = patentDTO.getApplicationNumber();
        this.publicationNumber = patentDTO.getPublicationNumber();
        this.ipcs = patentDTO.getIpcs();

        this.inventorIds = patentDTO.getInventors().stream().map(p -> p.getId()).collect(Collectors.toList());
        this.companyIds = patentDTO.getCompanies().stream().map(p -> p.getId()).collect(Collectors.toList());
    }

    @Id
    String id;

    @Field("abstract")
    String abstractStr;

    @Field("abstract_cn")
    String abstractCn;

//    String description;

//    @Field("applicants")
//    List<Applicant> applicants;

    @Field("invention_titles")
    List<String> inventionTitle;

    @Field("invention_titles_cn")
    List<String> inventionTitleCn;

    @Field("application_date")
    String applicationDate;

    @Field("publication_date")
    String publicationDate;

    @Field("inventor_ids")
    List<String> inventorIds;

    @Field("company_ids")
    List<String> companyIds;

    @Field("application_number")
    String applicationNumber;

    @Field("publication_number")
    String publicationNumber;

    @Field("ipc")
    List<String> ipcs;

}

