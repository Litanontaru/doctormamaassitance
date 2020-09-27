package com.example.doctormamaassistance.repository;

import com.example.doctormamaassistance.model.ScheduleAction;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;

/**
 * @author Andrei_Yakushin
 */
public interface ScheduleActioneRepository extends CrudRepository<ScheduleAction, Long> {
    List<ScheduleAction> findByWhen(LocalDate when);
}