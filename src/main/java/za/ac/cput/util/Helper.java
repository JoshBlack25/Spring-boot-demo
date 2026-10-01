package za.ac.cput.util;

import org.apache.commons.validator.routines.EmailValidator;

import java.util.Objects;

public class Helper {

    //  Constructor
    private Helper(){

    }

    //  isNullOrEmpty
    public static boolean isNullOrEmpty(String value){
        return value == null || value.trim().isEmpty();
    }

    //  isValidEmail
    public static boolean isValidEmail(String email){

        if(isNullOrEmpty(email)){
            return false;
        }

        return EmailValidator.getInstance().isValid(email);
    }

    //  isValidMobile
    public static boolean isValidMobile(String mobile){

        if(isNullOrEmpty(mobile)){
            return false;
        }

        return mobile.matches("\\d{10}");
    }

    //  isValidInt
    public static boolean isValidInt(int value){
        return value > 0;
    }

    //  isValidDouble
    public static boolean isValidDouble(double value){
        return value > 0;
    }

    //  isValidObject
    public static boolean isValidObject(Object object){
        return object != null;
    }
}
