package org.patentminer.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
public class Applicant {

    @Field("id")
    String id;

    @Field("addressbook")
    AddressBook addressBook;

    @Data
    class AddressBook {

        @Field("first_name")
        String firstName;

        @Field("middle_name")
        String middleName;

        @Field("last_name")
        String lastName;

        Address address;
    }

    String city;

    String country;

    String name;

    String alias;

    Nationality nationality;

    Residence residence;

    String state;

    @Data
    class Nationality {
        String country;
    }

    @Data
    class Residence {

        String country;

    }

}
