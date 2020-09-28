package com.example.doctormamaassistance.service.impl;

import com.example.doctormamaassistance.service.Description;
import com.example.doctormamaassistance.service.DescriptionStore;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Andrei_Yakushin
 */
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DescriptionStoreImpl implements DescriptionStore {
    private final Map<Long, Description> descriptions = new HashMap<>();

    private final List<DescriptionProvider> providers;

    @PostConstruct
    private void register() {
        providers.stream()
                .map(DescriptionProvider::getDescription)
                .forEach(d -> descriptions.put(d.getTypeId(), d));
    }

    @Override
    public Description get(Long typeId) {
        return descriptions.get(typeId);
    }
}
