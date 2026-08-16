package za.ac.cput.domain;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="customer")
public class Customer {

    @Id
    private String customerId;

    @Embedded
    private Person person;

    private double credit;

    protected Customer() {
    }

    private Customer(Builder builder){
        this.customerId = builder.customerId;
        this.person = builder.person;
        this.credit = builder.credit;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerId='" + customerId + '\'' +
                ", person=" + person +
                ", credit=" + credit +
                '}';
    }

    public Person getPerson() {
        return person;
    }

    public double getCredit() {
        return credit;
    }

    public static class Builder{
        private String customerId;
        private Person person;
        private double credit;

        public Builder setCustomerId(String customerId) {
            this.customerId = customerId;
            return this;
        }

        public Builder setPerson(Person person) {
            this.person = person;
            return this;
        }

        public Builder setCredit(double credit) {
            this.credit = credit;
            return this;
        }

        public Customer build(){
            return new Customer(this);
        }
    }
}
