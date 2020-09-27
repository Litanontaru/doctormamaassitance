package com.example.doctormamaassistance.service;

import java.util.List;

/**
 * @author Andrei_Yakushin
 */
public interface MessageService {
    List<MessageSummary> getMessagesByChildId(Long childId);
    MessageDetails getMessageDetails(String messageId);
}
