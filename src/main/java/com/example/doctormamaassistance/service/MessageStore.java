package com.example.doctormamaassistance.service;

import com.example.doctormamaassistance.core.model.MessageLike;

/**
 * @author Andrei_Yakushin
 */
public interface MessageStore {
    void saveMessage(MessageLike message);
}
