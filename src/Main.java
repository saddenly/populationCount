import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> people = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            people.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }

        Stream<Person> stream = people.stream();
        long numOfMinors = stream.filter(person -> person.getAge() < 18).count();
        Stream<Person> stream2 = people.stream();
        List<String> secondNames = stream2.filter(person -> person.getAge() >= 18 && person.getAge() < 27)
                .filter(person -> person.getSex() == Sex.MAN)
                .map(Person::getFamily)
                .collect(Collectors.toList());
        Stream<Person> stream3 = people.stream();
        List<Person> workingList = stream3.filter(person -> (person.getAge() >= 18 && person.getAge() < 60) && person.getSex() == Sex.WOMAN)
                .filter(person -> (person.getAge() >= 18 && person.getAge() < 65) && person.getSex() == Sex.MAN)
                .sorted(Comparator.comparing(Person::getFamily))
                .collect(Collectors.toList());
        System.out.println(numOfMinors);
        System.out.println(secondNames);
        System.out.println(workingList);
    }
}