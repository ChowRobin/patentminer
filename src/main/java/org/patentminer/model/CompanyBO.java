package org.patentminer.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
public class CompanyBO {

    String id;

    String name;

    @Field("name_cn")
    String nameCN;
}
