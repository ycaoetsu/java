package com.cultivation.javaBasic.showYourIntelligence;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Objects;

@SuppressWarnings("unused")
public class PersonForEquals {
    private final String name;
    private final short yearOfBirth;

    public PersonForEquals(String name, short yearOfBirth) {
        if (name == null) {
            throw new IllegalArgumentException("name is mandatory.");
        }

        if (yearOfBirth <= 1900 || yearOfBirth >= 2019) {
            throw new IllegalArgumentException("year of birth is out of range.");
        }

        this.name = name;
        this.yearOfBirth = yearOfBirth;
    }


    public String getName() {
        return name;
    }

    public short getYearOfBirth() {
        return yearOfBirth;
    }

    @SuppressWarnings("Contract")
    @Override
    public boolean equals(Object obj) {
        // TODO: please modify the following code to pass the test
        // <--start
        if (this == obj) return true;
        if (obj instanceof PersonForEquals) {
            PersonForEquals anotherPerson = (PersonForEquals)obj;
            if (anotherPerson.getName().equals(this.name) && anotherPerson.getYearOfBirth() == this.yearOfBirth) return true;
        }
        return false;
        //throw new NotImplementedException();
        // --end-->
    }

    @Override
    public int hashCode() {
        // TODO: please modify the following code to pass the test
        // <--start
        int result = 17;
        result = result * 31 + name.hashCode();
        result = result * 31 + yearOfBirth;
        return result;
        //throw new NotImplementedException();
        // --end-->
    }
}
