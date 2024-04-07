package org.example;

public class Person implements Comparable<Person> {
    private String name;
    private Integer age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public int compareTo(Person o) {
        return age.compareTo(o.age);
    }

    public String getName() { return name; }

    public Integer getAge() { return age; }
}
