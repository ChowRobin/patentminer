package org.patentminer.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Data
public class TopicDTO {

    @Id
    Integer id;

    String name;

    @Field("name_cn")
    String nameCN;

    @Field("inventors")
    List<Inventor> inventors;

    @Field("companys")
    List<Company> companies;

}
