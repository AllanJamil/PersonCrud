package com.example.personcrud.controller;

import com.example.personcrud.domain.Person;
import com.example.personcrud.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
public class PersonMvcController {

    private PersonService personService;

    @Autowired
    public PersonMvcController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public String homePage(Model model) {
        List<Person> personList = this.personService.listAll();
        model.addAttribute("listPerson", personList);
        return "index";
    }

    @GetMapping("new")
    public String showNewPersonPage(Model model) {
        model.addAttribute("newPerson", new Person());
        return "add_person";
    }

    @PostMapping("add")
    public String addPerson(Person person, BindingResult result) {
        if (result.hasErrors())
            return "error";
        else {
            this.personService.save(person);
            return "redirect:/";
        }
    }


    @GetMapping("/edit/{id}")
    public String showEditPage(@PathVariable UUID id, Model model) {
        Optional<Person> person = personService.findPersonById(id);
        if (person.isPresent()) {
            model.addAttribute("editPerson", person.get());
            return "edit_person";
        }
        else
            return "error";
    }

    @GetMapping("delete/{id}")
    public String deleteProduct(@PathVariable UUID id) {
        personService.delete(id);
        return "redirect:/";
    }
}
