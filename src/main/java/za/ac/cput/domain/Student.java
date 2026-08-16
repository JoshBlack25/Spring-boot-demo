package za.ac.cput.domain;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "student")
public class Student {

    @Id
    private String studentId;

    @Embedded
    private Person person;

    private double credits;

    protected Student(){}

    private Student(Builder builder){
        this.studentId = builder.studentId;
        this.person = builder.person;
        this.credits = builder.credits;
    }

    public String getStudentId() {
        return studentId;
    }

    public Person getPerson() {
        return person;
    }

    public double getCredits() {
        return credits;
    }

    public static class Builder{
        private String studentId;
        private Person person;
        private double credits;

        public Builder setStudentId(String studentId) {
            this.studentId = studentId;
            return this;
        }

        public Builder setPerson(Person person) {
            this.person = person;
            return this;
        }

        public Builder setCredits(double credits) {
            this.credits = credits;
            return this;
        }

        public Student build(){
            if(studentId == null || studentId.isEmpty())
                return null;
            if(person == null)
                return null;
            if (credits <= 0)
                return null;
            return new Student(this);
        }
    }
}
