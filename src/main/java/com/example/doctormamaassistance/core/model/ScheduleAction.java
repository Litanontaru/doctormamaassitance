package com.example.doctormamaassistance.core.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

/**
 * @author Andrei_Yakushin
 */
@NoArgsConstructor
@Getter
@Setter
@Entity
public class ScheduleAction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long childId;

    @Column(name = "local_date", columnDefinition = "DATE")
    private LocalDate when;

    public ScheduleAction(Long childId, LocalDate when) {
        this.childId = childId;
        this.when = when;
    }
}