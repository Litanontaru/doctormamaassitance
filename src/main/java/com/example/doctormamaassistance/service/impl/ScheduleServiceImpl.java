package com.example.doctormamaassistance.service.impl;


import static java.util.stream.Collectors.toList;

import com.example.doctormamaassistance.core.model.Child;
import com.example.doctormamaassistance.core.model.ScheduleAction;
import com.example.doctormamaassistance.core.repository.ScheduleActioneRepository;
import com.example.doctormamaassistance.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

/**
 * @author Andrei_Yakushin
 */
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ScheduleServiceImpl implements ScheduleService {
    private final ScheduleActioneRepository repository;

    //test purposes only
    @Setter
    private int shift = 0;


    @Override
    public void schedule(Child child, List<Integer> days) {
        LocalDate now = LocalDate.now();
        days.stream()
                .map(d -> new ScheduleAction(child.getId(), now.plusDays(d)))
                .forEach(repository::save);
    }

    @Override
    public List<Long> getTodaySchedule() {
        return repository
                .findByWhen(LocalDate.now().plusDays(shift))
                .stream()
                .map(ScheduleAction::getChildId)
                .collect(toList());
    }
}