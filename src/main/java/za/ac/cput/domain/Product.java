package za.ac.cput.domain;

import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Product {

    //  Variables
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int productId;

    protected String brand;
    protected double price;

    @ManyToOne
    @JoinColumn(name = "customerId")
    protected Customer customer;

    //  Constructors
    protected Product(){
    }

    protected Product(Builder<?> builder){
        this.productId = builder.productId;
        this.brand = builder.brand;
        this.price = builder.price;
        this.customer = builder.customer;
    }

    //  Getters
    public int getProductId() {
        return productId;
    }

    public String getBrand() {
        return brand;
    }

    public double getPrice() {
        return price;
    }

    public Customer getCustomer() {
        return customer;
    }

    //  toString
    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", brand='" + brand + '\'' +
                ", price=" + price +
                ", customer=" + customer +
                '}';
    }

    public static abstract class Builder <T extends Builder<T>>{
        private int productId;
        private String brand;
        private double price;
        private Customer customer;

        public T setProductId(int productId) {
            this.productId = productId;
            return self();
        }

        public T setBrand(String brand) {
            this.brand = brand;
            return self();
        }

        public T setPrice(double price) {
            this.price = price;
            return self();
        }

        public T setCustomer(Customer customer) {
            this.customer = customer;
            return self();
        }

        public T copy(Product product){
            this.productId = product.productId;
            this.brand = product.brand;
            this.price = product.price;
            this.customer = product.customer;

            return self();
        }

        protected abstract T self();
    }
}
