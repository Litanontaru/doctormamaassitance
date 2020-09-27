package com.example.doctormamaassistance.controller;

import com.example.doctormamaassistance.model.Child;
import com.example.doctormamaassistance.repository.ChildRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Andrei_Yakushin
 */

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ChildController {
    private final ChildRepository repository;

    @GetMapping("/children/create")
    public ChildDTO createStubChild() {
        Child child = new Child();
        child.setName("Сферический ребёнок в вакууме");
        return new ChildDTO(repository.save(child));
    }

    @GetMapping("/children/{child_id}")
    public ChildDTO getChildById(@PathVariable("child_id") Long childId) {
        return repository.findById(childId).map(ChildDTO::new).orElse(null);
    }
}