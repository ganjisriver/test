package com.blur.blurmatch.repository;

import com.blur.blurmatch.entity.MatchMakingRating;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatchMakingRatingRepository extends JpaRepository<MatchMakingRating, Long> {

    MatchMakingRating findByUserId(String userId);
}
