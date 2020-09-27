package com.example.doctormamaassistance.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.time.LocalDate;

/**
 * @author Andrei_Yakushin
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
@ToString
@MappedSuperclass
public class MessageLike {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long typeId;
    private Long childId;
    private String summary;
    private String note;
    private LocalDate createDate;
    private boolean archived = false;
}