package org.patentminer.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
public class Applicant {

    @Field("id")
    String id;

    @Field("addressbook")
    AddressBook addressBook;

    String city;

    String country;

    String name;

    String alias;

    Nationality nationality;

    Residence residence;

    String state;
}
