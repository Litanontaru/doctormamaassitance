package com.example.doctormamaassistance.core.service;

import java.util.List;

/**
 * @author Andrei_Yakushin
 */
public interface MessageService {
    List<MessageSummary> getMessagesByChildId(Long childId);
    MessageDetail getMessageDetails(String messageId);
}
