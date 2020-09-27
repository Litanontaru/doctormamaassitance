package com.example.doctormamaassistance.repository;

import com.example.doctormamaassistance.model.Note;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @author Andrei_Yakushin
 */
public interface NoteRepository extends CrudRepository<Note, Long> {
    List<Note> findByChildIdAndArchivedFalse(Long childId);
}
