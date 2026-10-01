package za.ac.cput.domain;


import jakarta.persistence.*;
import za.ac.cput.domain.valueObject.Name;

@Entity
public class Customer {

    //  Variables
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int customerId;

    @Embedded
    private Name name;

    private String email;
    private String mobile;

    //  Constructors
    protected Customer(){
    }

    private Customer(Builder builder){
        this.customerId = builder.customerId;
        this.name = builder.name;
        this.email = builder.email;
        this.mobile = builder.mobile;
    }

    //  Getters
    public int getCustomerId() {
        return customerId;
    }

    public Name getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getMobile() {
        return mobile;
    }

    //  toString
    @Override
    public String toString() {
        return "Customer{" +
                "customerId=" + customerId +
                ", name=" + name +
                ", email='" + email + '\'' +
                ", mobile='" + mobile + '\'' +
                '}';
    }

    public static class Builder{
        private int customerId;
        private Name name;
        private String email;
        private String mobile;

        public Builder setCustomerId(int customerId) {
            this.customerId = customerId;
            return this;
        }

        public Builder setName(Name name) {
            this.name = name;
            return this;
        }

        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder setMobile(String mobile) {
            this.mobile = mobile;
            return this;
        }

        //  copy method
        public Builder copy(Customer customer){
            this.customerId = customer.customerId;
            this.name = customer.name;
            this.email = customer.email;
            this.mobile = customer.mobile;

            return this;
        }

        public Customer build(){
            return new Customer(this);
        }
    }
}
