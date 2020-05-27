package com.example.personcrud.service;

import com.example.personcrud.domain.Person;
import com.example.personcrud.domain.Response;
import com.example.personcrud.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PersonService {

    private PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<Person> listAll() {
        return this.personRepository.findAll();
    }

    public Optional<Person> findPersonById(UUID id) {
        return this.personRepository.findById(id);
    }

    @Transactional
    public Person save(Person person) {
        return personRepository.save(person);
    }

    @Transactional
    public Response delete(UUID id) {
        Optional<Person> personById = personRepository.findById(id);
        Response response = new Response("Deletion.", false);
        personById.ifPresent(person -> {
            personRepository.deleteById(person.getId());
            response.setStatus(true);
        });
         return response;
    }
}
