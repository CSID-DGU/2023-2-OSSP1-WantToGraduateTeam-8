package com.dgu.wantToGraduate.domain.matching.repository;

import com.dgu.wantToGraduate.domain.matching.entity.PreferTable;
import com.dgu.wantToGraduate.domain.user.entity.WaitUserData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PreferTableRepository extends JpaRepository<PreferTable, Long> {

//    @Query("select pt from PreferTable pt where pt.waitUserData = :waitUserData")
    List<PreferTable> searchPreferTableByWaitUserData(WaitUserData waitUserData);
}
