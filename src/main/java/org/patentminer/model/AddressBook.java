package org.patentminer.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
public class AddressBook {

    @Field("first-name")
    String firstName;

    @Field("middle-name")
    String middleName;

    @Field("last-name")
    String lastName;

    Address address;
}
