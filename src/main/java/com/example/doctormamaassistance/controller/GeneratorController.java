package com.example.doctormamaassistance.controller;

import com.example.doctormamaassistance.service.RuleEngine;
import com.example.doctormamaassistance.service.ScheduleService;
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
public class GeneratorController {
    private final RuleEngine ruleEngine;
    private final ScheduleService scheduleService;

    @GetMapping("/children/{child_id}/generate")
    public String start(@PathVariable("child_id") Long childId) {
        ruleEngine.generateScheduledForToday(childId);
        return "ok";
    }

    @GetMapping("/generate_scheduled")
    public String forAll() {
        ruleEngine.generateScheduledForToday();
        return "ok";
    }

    @GetMapping("/shift/{shift}")
    public String setShift(@PathVariable("shift") int shift) {
        scheduleService.setShift(shift);
        return "ok";
    }
}
