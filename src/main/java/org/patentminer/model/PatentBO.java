package org.patentminer.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Data
public class PatentBO {

    @Id
    String id;

    @Field("invention_titles")
    List<String> inventionTitle;

    @Field("invention_titles_cn")
    List<String> inventionTitleCN;

}
