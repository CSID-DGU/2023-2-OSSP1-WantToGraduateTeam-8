package com.dgu.wantToGraduate.domain.user.repository;

import com.dgu.wantToGraduate.domain.user.entity.UserReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReviewRepository extends JpaRepository<UserReview, Long> {

    @Query("select ur.comment from UserReview ur where ur.user.id = :userId")
    List<UserReview> findAllByUserId(Long userId);
}
