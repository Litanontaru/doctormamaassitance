package com.example.doctormamaassistance.service.impl;

import com.example.doctormamaassistance.service.MessageStore;
import com.example.doctormamaassistance.core.model.MessageLike;
import com.example.doctormamaassistance.core.model.Note;
import com.example.doctormamaassistance.core.model.Recommendation;
import com.example.doctormamaassistance.core.repository.NoteRepository;
import com.example.doctormamaassistance.core.repository.RecommendationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Andrei_Yakushin
 */
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MessageStoreImpl implements MessageStore {
    private final NoteRepository noteRepository;
    private final RecommendationRepository recommendationRepository;

    @Override
    public void saveMessage(MessageLike message) {
        if (message instanceof Note) {
            noteRepository.save((Note) message);
        } else if (message instanceof Recommendation) {
            recommendationRepository.save((Recommendation) message);
        } else {
            System.out.println("Unknown type of message: " + message);
        }
    }
}