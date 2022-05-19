package ru.zenkov.skb_dz_7.controllers;

import org.springframework.web.bind.annotation.*;
import ru.zenkov.skb_dz_7.exeptions.NotFoundByIdException;
import ru.zenkov.skb_dz_7.exeptions.NotFoundByNameException;
import ru.zenkov.skb_dz_7.models.Person;
import ru.zenkov.skb_dz_7.repositories.PersonRepository;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {

    private final PersonRepository personRepository;

    @PostMapping("/add")
    public void savePerson(@RequestBody @Valid Person person) {
        System.out.println(person);
        personRepository.save(person);
    }

    @GetMapping("/id/{id}")
    public Person getPersonById(@PathVariable("id") @Min(0) Long id) throws NotFoundByIdException {
        var person = personRepository.findById(id);
        if (person != null) {
            return person;
        }
        throw new NotFoundByIdException(id);
    }

    @GetMapping("/name/{name}")
    public List<Person> getPersonByName(@PathVariable("name") @NotBlank String name) throws NotFoundByNameException {
        var people = personRepository.findAllByName(name);
        if (!people.isEmpty()) {
            return people;
        }
        throw new NotFoundByNameException(name);
    }

    public PersonController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }
}
