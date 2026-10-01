package za.ac.cput.domain.valueObject;

import jakarta.persistence.Embeddable;

@Embeddable
public class Name {

    //  Variables
    private String firstName;
    private String middleName;
    private String lastName;

    //  Constructors
    protected Name(){
    }

    private Name(Builder builder){
        this.firstName = builder.firstName;
        this.middleName = builder.middleName;
        this.lastName = builder.lastName;
    }

    //  Getters
    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getLastName() {
        return lastName;
    }

    //  toString
    @Override
    public String toString() {
        return "Name{" +
                "firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

    public static class Builder{
        //  Variables
        private String firstName;
        private String middleName;
        private String lastName;

        //  Setters
        public Builder setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder setMiddleName(String middleName) {
            this.middleName = middleName;
            return this;
        }

        public Builder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder copy(Name name){
            this.firstName = name.firstName;
            this.middleName = name.middleName;
            this.lastName = name.lastName;

            return this;
        }

        public Name build(){
            return new Name(this);
        }
    }
}
