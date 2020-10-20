package com.example.doctormamaassistance.core.repository;

import com.example.doctormamaassistance.core.model.Recommendation;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @author Andrei_Yakushin
 */
public interface RecommendationRepository extends CrudRepository<Recommendation, Long> {
    List<Recommendation> findByChildIdAndArchivedFalse(Long childId);
}
