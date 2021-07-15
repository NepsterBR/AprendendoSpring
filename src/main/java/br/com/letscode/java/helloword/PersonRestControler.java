package br.com.letscode.java.helloword;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("persons")
public class PersonRestControler {

    private final Set<String> persons = new LinkedHashSet<>();

    public PersonRestControler() {
        this.persons.add("Jesse");
        this.persons.add("Bruno");
        this.persons.add("Matheus");
        this.persons.add("Lucio");
    }

    @GetMapping
    public Set<String> listAll() {
        return this.persons;
    }

    @GetMapping("find")
    public Set<String> findByName(@RequestParam String name) {
        return this.persons.stream()
                .filter(p -> p.toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toSet());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String addPerson(@RequestBody  String name) {
        if (name == null && !name.isEmpty()){
            this.persons.add(name);
        }
        return name;
    }

    @DeleteMapping("{id:[\\d+]}")
    public void deleteById(@PathVariable Integer id){
        String person = new ArrayList<>(this.persons).get(id);
        this.persons.remove(person);
    }

    @DeleteMapping("{name:[\\D+]}")
    public void deleteByName(@PathVariable String name){
        this.persons.removeIf(p -> p.equalsIgnoreCase(name));
    }
}
