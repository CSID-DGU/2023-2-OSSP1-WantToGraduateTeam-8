package com.dgu.wantToGraduate.domain.user.repository;

import com.dgu.wantToGraduate.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Transactional
    @Modifying
    @Query("UPDATE UserReview u SET u.grade = :grade WHERE u.id = :id")
    void updateGradeById(Long id, float grade);

    @Query("select u from User u where u.email = ?1")
    public Optional<User> findByEmail(String email);

    @Modifying
    @Query("update User u set u.isMatched = ?2 where u.id = ?1")
    @Transactional
    public void updateUserFlag(Long userId, boolean flag);

    @Query("select u.id from User u where u.nickname = ?1")
    public Long findUserIdByNickname(String nickname);
}
