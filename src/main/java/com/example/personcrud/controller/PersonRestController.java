package com.example.personcrud.controller;

import com.example.personcrud.domain.Person;
import com.example.personcrud.domain.Response;
import com.example.personcrud.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("person")
public class PersonRestController {

    private PersonService personService;

    @Autowired
    public PersonRestController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("{id}")
    public Optional<Person> findPersonById(@PathVariable UUID id) {
        Optional<Person> personById = personService.findPersonById(id);
        personById.ifPresent(person -> {
            if (!person.hasLink("find_all")) {
                Link link = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(PersonRestController.class).findAll())
                        .withRel("find_all");
                person.add(link);
            }
            if (!person.hasLink("delete_person")) {
                Link link = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(PersonRestController.class)
                        .deletePerson(person.getId())).withRel("delete_person");
                person.add(link);
            }
            if (!person.hasLink("add_person")) {
                Link link = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(PersonRestController.class).addPerson(person))
                        .withRel("add_person");
                person.add(link);
            }
        });

        return personById;
    }

    @GetMapping("list")
    public List<Person> findAll() {
        return personService.listAll();
    }

    @PostMapping("add")
    public Person addPerson(@RequestBody Person person) {
        return personService.save(person);
    }

    @DeleteMapping("delete/{id}")
    public Response deletePerson(@PathVariable UUID id) {
        return personService.delete(id);
    }

}
