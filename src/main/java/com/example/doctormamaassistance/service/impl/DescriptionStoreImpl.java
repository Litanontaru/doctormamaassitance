package com.example.doctormamaassistance.service.impl;

import com.example.doctormamaassistance.service.Description;
import com.example.doctormamaassistance.service.DescriptionStore;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Andrei_Yakushin
 */
@Component
public class DescriptionStoreImpl implements DescriptionStore {
    private final Map<Long, Description> descriptions = new HashMap<>();

    @Override
    public void register(Description description) {
        descriptions.put(description.getTypeId(), description);
    }

    @Override
    public Description get(Long typeId) {
        return descriptions.get(typeId);
    }
}
