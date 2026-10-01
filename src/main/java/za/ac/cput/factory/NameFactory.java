package za.ac.cput.factory;

import za.ac.cput.domain.valueObject.Name;
import za.ac.cput.util.Helper;

public class NameFactory {

    public static Name buildName(
            String firstName, String middleName, String lastName
    ){
        if(Helper.isNullOrEmpty(firstName)) return null;
        if(Helper.isNullOrEmpty(lastName)) return null;

        return new Name.Builder()
                .setFirstName(firstName)
                .setMiddleName(middleName)
                .setLastName(lastName)
                .build();
    }
}
