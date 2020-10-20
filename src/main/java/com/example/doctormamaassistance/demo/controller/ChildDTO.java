package com.example.doctormamaassistance.demo.controller;

import com.example.doctormamaassistance.core.model.Child;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Andrei_Yakushin
 */
@Getter
@Setter
@NoArgsConstructor
public class ChildDTO {
    private Long id;
    private String name;
    private String state;

    public ChildDTO(Child child) {
        id = child.getId();
        name = child.getName();
        state = child.getState();
    }
}
