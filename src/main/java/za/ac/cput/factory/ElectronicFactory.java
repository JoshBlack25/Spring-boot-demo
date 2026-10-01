package za.ac.cput.factory;

import za.ac.cput.domain.Customer;
import za.ac.cput.domain.Electronic;
import za.ac.cput.util.Helper;

public class ElectronicFactory {

    public static Electronic buildElectronic(
            String brand, double price, Customer customer, int voltage
    ){
        if (Helper.isNullOrEmpty(brand)) return null;
        if (!Helper.isValidDouble(price)) return null;
        if (!Helper.isValidObject(customer)) return null;
        if (!Helper.isValidInt(voltage)) return null;

        return new Electronic.Builder()
                .setBrand(brand)
                .setPrice(price)
                .setCustomer(customer)
                .setVoltage(voltage)
                .build();
    }
}
