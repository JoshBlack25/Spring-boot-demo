package za.ac.cput.factory;

import za.ac.cput.domain.Person;
import za.ac.cput.domain.Student;
import za.ac.cput.util.Helper;

public class StudentFactory {
    public static Student createStudent(String id, Person person, double credits){

        if (Helper.isNullOrEmpty(id) || person == null || credits <= 0) {
            return null;
        }

        return new Student.Builder()
                .setStudentId(id)
                .setPerson(person)
                .setCredits(credits)
                .build();
    }
}
