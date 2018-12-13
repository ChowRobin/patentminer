package org.patentminer.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Data
@Document(collection = "companies")
public class Company {

    @Id
    String id;

    String name;

    @Field("name_cn")
    String nameCn;

//    @Field("inventor_ids")
//    List<String> inventorIds;

//    @Field("patent_ids")
//    List<String> patentIds;
}
