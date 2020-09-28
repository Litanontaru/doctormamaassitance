package com.example.doctormamaassistance.service.impl;

import static java.util.Collections.emptyList;
import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

import com.example.doctormamaassistance.model.Child;
import com.example.doctormamaassistance.repository.ChildRepository;
import com.example.doctormamaassistance.repository.NoteRepository;
import com.example.doctormamaassistance.repository.RecommendationRepository;
import com.example.doctormamaassistance.service.DescriptionStore;
import com.example.doctormamaassistance.service.MessageDetail;
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
    private final DescriptionStore descriptionStore;

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
    public MessageDetail getMessageDetails(String messageId) {
        if (messageId.startsWith("n_")) {
            return noteRepository.findById(Long.parseLong(messageId.substring(2)))
                    .map(n -> childRepository
                            .findById(n.getChildId())
                            .map(c -> new MessageDetail(
                                    "n_" + n.getId(),
                                    c.getName(),
                                    c.getAge(),
                                    n.getSummary(),
                                    n.getNote(),
                                    n.getCreateDate(),
                                    emptyList()
                            ))
                    )
                    .orElse(null)
                    .orElse(null);

        } else if (messageId.startsWith("r_")) {
            return recommendationRepository.findById(Long.parseLong(messageId.substring(2)))
                    .map(r -> childRepository
                            .findById(r.getChildId())
                            .map(c -> new MessageDetail(
                                    "r_" + r.getId(),
                                    c.getName(),
                                    c.getAge(),
                                    r.getSummary(),
                                    r.getNote(),
                                    r.getCreateDate(),
                                    descriptionStore.get(r.getTypeId()).build(r)
                            ))
                    )
                    .orElse(null)
                    .orElse(null);
        }
        return null;
    }
}