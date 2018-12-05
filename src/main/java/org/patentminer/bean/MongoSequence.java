package org.patentminer.bean;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class MongoSequence {

    @Id
    private String id;
    private int seq;
}
