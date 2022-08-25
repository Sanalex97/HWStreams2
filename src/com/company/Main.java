package com.company;

import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }

        System.out.println(getMinor(persons));
        System.out.println(getConscript(persons));
        System.out.println(getWorkable(persons));

    }

    public static long getMinor(Collection<Person> persons) {

        return persons.stream()
                .filter(person -> person.getAge() < 18)
                .count();
    }

    public static List<String> getConscript(Collection<Person> persons) {
        return persons.stream()
                .filter(person -> person.getAge() >= 18 && person.getAge() <= 27)
                .map(Person::getFamily)
                .collect(Collectors.toList());
    }

    public static List<Person> getWorkable(Collection<Person> persons) {
        return persons.stream()
                .filter(person -> person.getEducation() == Education.HIGHER)
                .filter(person -> person.getAge() >= 18)
                .filter(person -> {
                    if (person.getSex() == Sex.WOMAN) {
                        return person.getAge() < 60;
                    } else {
                        return person.getAge() < 65;
                    }
                })
                .sorted(Comparator.comparing(Person::getFamily))
                .collect(Collectors.toList());
    }
}
