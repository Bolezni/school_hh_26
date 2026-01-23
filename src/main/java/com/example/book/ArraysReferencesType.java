package com.example.book;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class ArraysReferencesType {
    public static void main(String[] args) {
        PersonArrays personArrays = new PersonArrays(5);
        personArrays.addPerson(new Person("John", "Smith", 20));
        personArrays.addPerson(new Person("Jane", "Doe", 19));
        personArrays.addPerson(new Person("Dima", "Pereira", 18));
        personArrays.addPerson(new Person("Anna", "Daily", 20));
        personArrays.addPerson(new Person("Will", "Smith", 18));

        System.out.println(personArrays);
        System.out.println(personArrays.getSize());
        personArrays.sort();
        System.out.println(personArrays);
    }
}

class PersonArrays{
    private final Person[] persons;
    private int size;

    public PersonArrays(int size) {
        persons = new Person[size];
        size = 0;
    }

    public int getSize() {
        return size;
    }

    public Person findByLastName(String lastName) {
        for (int i = 0; i < persons.length; i++) {
            if (persons[i].getLastname().equals(lastName))
                return persons[i];
        }
        return null;
    }

    public void addPerson(Person person) {
        persons[size] = person;
        size++;
    }

    public boolean delete(String searchName) {
        int i;
        for (i = 0; i < size; i++) {
            if (persons[i].getLastname().equals(searchName)) {
                break;
            }
        }
        if (i == size)
            return false;
        else {
            for (int k = i; k < size - 1; k++)
                persons[k] = persons[k + 1];
            size--;
            return true;
        }
    }

    public void sort() {
        for (int i = 1; i < size; i++) {
             Person PersonMin = persons[i];
             int j = i - 1;
             while (j >= 0 && persons[j].getLastname()
                     .compareTo(PersonMin.getLastname()) > 0) {
                 persons[j + 1] = persons[j];
                 j = j - 1;
             }
             persons[j + 1] = PersonMin;
        }
    }

    @Override
    public String toString() {
        return "PersonArrays{" +
                "persons=" + Arrays.toString(persons) +
                ", size=" + size +
                '}';
    }
}

class Person {
    private String name;
    private String lastname;
    private int age;

    public Person(String name, String lastname, int age) {
        this.name = name;
        this.lastname = lastname;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                ", age=" + age +
                '}';
    }
}
