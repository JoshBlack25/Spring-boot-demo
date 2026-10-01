package za.ac.cput.factory;

import za.ac.cput.domain.Customer;
import za.ac.cput.domain.valueObject.Name;
import za.ac.cput.util.Helper;

public class CustomerFactory {

    public static Customer buildCustomer(
            Name name, String email, String mobile
    ){

        if(!Helper.isValidObject(name)) return null;
        if(!Helper.isValidEmail(email)) return null;
        if(!Helper.isValidMobile(mobile)) return null;

        return new Customer.Builder()
                .setName(name)
                .setEmail(email)
                .setMobile(mobile)
                .build();
    }
}
