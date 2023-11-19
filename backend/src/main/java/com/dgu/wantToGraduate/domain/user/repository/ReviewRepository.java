package com.dgu.wantToGraduate.domain.user.repository;

import com.dgu.wantToGraduate.domain.user.entity.UserReview;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<UserReview, Long> {
}
