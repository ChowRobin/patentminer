package org.patentminer.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Data
@Document(collection = "inventors")
public class Inventor {

    @Id
    String id;

    String name;

    @Field("name_cn")
    String nameCn;

//    @Field("company_id")
//    String companyId;

//    @Field("patent_ids")
//    List<String> patentIds;
}
