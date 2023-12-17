package com.dgu.wantToGraduate.domain.user.entity;

import com.dgu.wantToGraduate.domain.BaseTimeEntity;
import com.dgu.wantToGraduate.domain.chat.entity.ChatRoom;
import com.dgu.wantToGraduate.domain.matching.entity.PreferBrand;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class User extends BaseTimeEntity{

    // 필수 데이터
    // 닉네임, 프로필사진, 계좌번호, 이메일, 비밀번호, 평점

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "user_id")
    private Long id;

    @Column(nullable = false)
    @ColumnDefault("false")
    private boolean isMatched;

    @Column(nullable = false)
    private String nickname;

    @Column
    private String profile_image;

    @Column(nullable = false)
    private String account_number;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    @ColumnDefault("0.0")
    private float grade;

    @OneToMany(mappedBy = "user")
    private List<PreferBrand> preferBrandList=new ArrayList<>();

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "chatRoom_id")
    private ChatRoom chatRoom;

    @OneToMany(mappedBy = "user")
    private List<UserReview> userReviews=new ArrayList<>();

    @ColumnDefault("'USER_ROLE'")
    private String role;
    //user flag toggle
//    public void toggling(){
//        this.isMatched=!this.isMatched;
//    }
}
