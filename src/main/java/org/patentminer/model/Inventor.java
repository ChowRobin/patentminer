package org.patentminer.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
public class Inventor {

    String name;

    @Field("name_cn")
    String nameCn;
}
