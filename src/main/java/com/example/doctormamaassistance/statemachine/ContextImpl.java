package com.example.doctormamaassistance.statemachine;

import com.example.doctormamaassistance.model.Child;
import com.example.doctormamaassistance.model.MessageLike;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Andrei_Yakushin
 */
@RequiredArgsConstructor
@Accessors(fluent = true)
class ContextImpl implements Context {
    @Getter
    private final Child child;

    private List<MessageLike> messages = new ArrayList<>();

    @Getter
    private List<Integer> daysToSkip = new ArrayList<>();

    @Override
    public void addMessage(MessageLike message) {
        messages.add(message.setChildId(child.getId())
                .setCreateDate(LocalDate.now())
                .setArchived(false));
    }

    List<MessageLike> popMessages() {
        List<MessageLike> messages = this.messages;
        this.messages = new ArrayList<>();
        return messages;
    }

    @Override
    public void skipDaysAndTerminate(int days) {
        if (days <= 0) {
            throw new IllegalArgumentException("Days to skip should be positive");
        }
        daysToSkip.add(days);
    }
}