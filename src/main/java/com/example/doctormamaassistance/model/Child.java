package com.example.doctormamaassistance.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author Andrei_Yakushin
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Child {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String state = null;

    public String getAge() {
        //todo
        return "Х месяцев";
    }
}