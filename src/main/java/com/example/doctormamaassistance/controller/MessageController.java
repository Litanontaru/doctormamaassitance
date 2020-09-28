package com.example.doctormamaassistance.controller;

import com.example.doctormamaassistance.service.MessageDetail;
import com.example.doctormamaassistance.service.MessageService;
import com.example.doctormamaassistance.service.MessageSummary;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Andrei_Yakushin
 */
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MessageController {
    private final MessageService messageService;

    @GetMapping("/children/{child_id}/messages")
    public List<MessageSummary> getMessagesByChildId(@PathVariable("child_id") Long childId) {
        return messageService.getMessagesByChildId(childId);
    }

    @GetMapping("/children/{child_id}/messages/{message_id}")
    public MessageDetail getMessageDetails(@PathVariable("child_id") Long childId, @PathVariable("message_id") String messageId) {
        return messageService.getMessageDetails(messageId);
    }
}
