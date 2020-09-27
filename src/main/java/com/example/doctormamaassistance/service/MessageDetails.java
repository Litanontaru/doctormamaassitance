package com.example.doctormamaassistance.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

/**
 * @author Andrei_Yakushin
 */

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MessageDetails {
    private String message_id;
    private String child_name;
    private String childAge;
    private String summary;
    private String note;
    private LocalDate create_date;
}
