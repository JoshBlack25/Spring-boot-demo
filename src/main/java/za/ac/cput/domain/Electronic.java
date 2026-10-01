package za.ac.cput.domain;

import jakarta.persistence.Entity;

@Entity
public class Electronic extends Product {

    //  Variables
    private int voltage;

    //  Constructors
    protected Electronic(){}

    private Electronic(Builder builder){
        super(builder);
        this.voltage = builder.voltage;
    }

    //  Getters
    public int getVoltage() {
        return voltage;
    }

    //  toString

    @Override
    public String toString() {
        return "Electronic{" +
                "voltage=" + voltage +
                '}' + super.toString();
    }

    public static class Builder extends Product.Builder<Builder>{
        private int voltage;

        public Builder setVoltage(int voltage) {
            this.voltage = voltage;
            return this;
        }

        public Builder copy(Electronic electronic){
            super.copy(electronic);
            this.voltage = electronic.voltage;

            return this;
        }

        @Override
        protected Builder self() {
            return this;
        }

        public Electronic build(){
            return new Electronic(this);
        }
    }
}
