package org.apphibernate.controllers;


import org.apphibernate.models.Person;
import org.apphibernate.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/people")
public class PeopleController {

    private final PersonService personService;

    @Autowired
    public PeopleController(PersonService personService){
        this.personService = personService;
    }

    @GetMapping()
    public String getAllPeople(Model model){
        model.addAttribute("people",personService.getAllPeople());
        return "people/index";
    }

    @GetMapping("/new")
    public String createNewPerson(Model model){
        model.addAttribute("person",new Person());
        return "people/new";
    }

    @PostMapping
    public String createNewPerson(@ModelAttribute("person") @Valid Person person,
                                  BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return "people/new";
        personService.savePerson(person);
        return "redirect:/people";
    }

    @GetMapping("/{id}/edit")
    public String editPerson(Model model,
                             @PathVariable("id") int id){
        model.addAttribute("person",personService.showById(id));
        return "people/edit";
    }

    @PatchMapping("/{id}")
    public String updatePerson(@ModelAttribute("person") @Valid Person person,
                               BindingResult bindingResult,
                               @PathVariable("id") int id){
        if(bindingResult.hasErrors())
            return "people/edit";

        personService.updatePerson(id,person);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}/delete")
    public String deletePerson(@PathVariable("id") int id){
        personService.deletePerson(id);
        return "redirect:/people";
    }
}
