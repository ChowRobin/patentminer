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

    @Field("applicants")
    List<Applicant> applicants;

    @Field("invention-title")
    String inventionTitle;

    @Field("application-date")
    String applicationDate;

    @Field("publication-date")
    String publicationDate;

}
