package com.example.doctormamaassistance.service;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

/**
 * @author Andrei_Yakushin
 */
@AllArgsConstructor
@Getter
public class Description {
    private Long typeId;
    private final List<String> items;
    private final List<List<String>> itemOptions;
}