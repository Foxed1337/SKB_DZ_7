package ru.zenkov.skb_dz_7.repositories;

import org.springframework.stereotype.Component;
import ru.zenkov.skb_dz_7.models.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PersonRepository {
    private final List<Person> personRepo = new ArrayList<>();

    public void save(Person person) {
        personRepo.add(person);
    }

    public Person findById(Long id) {
        return personRepo.stream()
                .filter(person -> person.getId().equals(id))
                .findAny()
                .orElse(null);
    }

    public List<Person> findAllByName(String name) {
        return personRepo.stream()
                .filter(person -> person.getName().equals(name))
                .collect(Collectors.toList());

    }
}
