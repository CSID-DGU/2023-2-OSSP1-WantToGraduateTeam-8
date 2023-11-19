package com.dgu.wantToGraduate.domain.user.repository;

import com.dgu.wantToGraduate.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
