package com.dgu.wantToGraduate.domain.user.repository;

import com.dgu.wantToGraduate.domain.user.entity.WaitUserData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WaitUserDataRepository extends JpaRepository<WaitUserData, Long> {
}
