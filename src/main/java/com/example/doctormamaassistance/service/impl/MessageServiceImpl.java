package com.example.doctormamaassistance.service.impl;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

import com.example.doctormamaassistance.model.Child;
import com.example.doctormamaassistance.repository.ChildRepository;
import com.example.doctormamaassistance.repository.NoteRepository;
import com.example.doctormamaassistance.repository.RecommendationRepository;
import com.example.doctormamaassistance.service.MessageDetails;
import com.example.doctormamaassistance.service.MessageService;
import com.example.doctormamaassistance.service.MessageSummary;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Stream;

/**
 * @author Andrei_Yakushin
 */
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MessageServiceImpl implements MessageService {
    private final ChildRepository childRepository;
    private final NoteRepository noteRepository;
    private final RecommendationRepository recommendationRepository;

    @Override
    public List<MessageSummary> getMessagesByChildId(Long childId) {
        String child_name = childRepository.findById(childId).map(Child::getName).orElse("");
        Stream<MessageSummary> notes = noteRepository.findByChildIdAndArchivedFalse(childId)
                .stream()
                .map(n -> new MessageSummary(
                        "n_" + n.getId(),
                        child_name,
                        n.getSummary(),
                        n.getCreateDate()
                ));

        Stream<MessageSummary> recommendations = recommendationRepository.findByChildIdAndArchivedFalse(childId)
                .stream()
                .map(r -> new MessageSummary(
                        "r_" + r.getId(),
                        child_name,
                        r.getSummary(),
                        r.getCreateDate()
                ));

        return Stream.concat(notes, recommendations)
                .sorted(comparing(MessageSummary::getCreate_date))
                .collect(toList());
    }

    @Override
    public MessageDetails getMessageDetails(String messageId) {
        //todo
        return null;
    }
}
