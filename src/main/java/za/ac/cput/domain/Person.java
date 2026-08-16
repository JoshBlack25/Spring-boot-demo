package za.ac.cput.domain;

import jakarta.persistence.Embeddable;

@Embeddable
public class Person {

    private String firstName;
    private String lastName;

    protected Person(){}

    private Person(Builder builder){
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastname() {
        return lastName;
    }
    public static class Builder{
        private String firstName;
        private String lastName;

        public Builder setLastName(String lastname) {
            this.lastName = lastname;
            return this;
        }

        public Builder setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Person build(){
            if(firstName == null || firstName.isEmpty())
                return null;
            if (lastName == null || lastName.isEmpty())
                return null;
            return new Person(this);
        }
    }
}
