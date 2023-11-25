package com.dgu.wantToGraduate.domain.matching.entity;

import com.dgu.wantToGraduate.domain.user.entity.User;
import com.dgu.wantToGraduate.domain.user.entity.WaitUserData;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class PreferTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="waitUserData_id")
    private WaitUserData waitUserData;

    @OneToMany(mappedBy = "preferTable")
    private List<User> userList=new ArrayList<>();

    @Column(nullable = false)
    private int preferenceScore;

    //addUserList
    public void addUserList(User user){
        this.userList=new ArrayList<>();
        userList.add(user);
    }
}
