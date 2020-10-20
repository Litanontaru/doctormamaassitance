package com.example.doctormamaassistance.core.service;

import com.example.doctormamaassistance.core.model.Recommendation;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author Andrei_Yakushin
 */
@AllArgsConstructor
@Getter
public class Description {
    private Long typeId;
    private final List<String> items;
    private final List<List<String>> itemOptions;

    public List<MessageItem> build(Recommendation recommendation) {
        List<Boolean> accomplished = recommendation.getAccomplished();
        if (accomplished.size() != items.size()) {
            throw new IllegalArgumentException();
        }
        return IntStream
                .range(0, accomplished.size())
                .mapToObj(i -> new MessageItem(items.get(i), itemOptions.get(i), accomplished.get(i) ? 0 : -1))
                .collect(Collectors.toList());
    }
}