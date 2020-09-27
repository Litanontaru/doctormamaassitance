package com.example.doctormamaassistance.repository;

import com.example.doctormamaassistance.model.Recommendation;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @author Andrei_Yakushin
 */
public interface RecommendationRepository extends CrudRepository<Recommendation, Long> {
    List<Recommendation> findByChildIdAndArchivedFalse(Long childId);
}
