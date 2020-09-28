package com.example.doctormamaassistance.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * @author Andrei_Yakushin
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MessageItem {
    private String text;
    private List<String> options;
    private Integer selected;
}
