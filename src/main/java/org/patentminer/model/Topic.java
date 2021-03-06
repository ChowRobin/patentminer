package org.patentminer.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Data
@Document(collection = "topics")
public class Topic {

    @Id
    Integer id;

    String name;

    @Field("name_cn")
    String nameCN;

    @Field("inventor_ids")
    List<String> inventorIds;

    @Field("company_ids")
    List<String> companyIds;

    @Field("patent_ids")
    List<String> patentIds;

    @Field("hot_words")
    List<Word> hotWords;
}
