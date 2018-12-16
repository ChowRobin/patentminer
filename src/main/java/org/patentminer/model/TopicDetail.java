package org.patentminer.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Data
public class TopicDetail {

    @Id
    Integer id;

    String name;

    @Field("name_cn")
    String nameCN;

    @Field("inventors")
    List<Inventor> inventors;

    @Field("companys")
    List<Company> companies;

    @Field("relevant_patents")
    List<PatentBO> relevantPatents;

    @Field("hot_words")
    List<Word> hotWords;
}
