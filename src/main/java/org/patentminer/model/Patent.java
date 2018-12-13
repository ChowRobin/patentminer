package org.patentminer.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Data
@Document(collection = "patents")
public class Patent {

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

    @Field("inventors")
    List<Inventor> inventors;

    @Field("companies")
    List<Company> companies;

    @Field("application_number")
    String applicationNumber;

    @Field("publication_number")
    String publicationNumber;

    @Field("ipc")
    List<String> ipcList;

}

