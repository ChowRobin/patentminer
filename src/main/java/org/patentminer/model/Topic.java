package org.patentminer.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Document(collection = "topics")
public class Topic {

    @Id
    String id;

    String name;

    @Field("name_cn")
    String nameCN;

}
