package com.dgu.wantToGraduate.domain.user.entity;

import com.dgu.wantToGraduate.domain.BaseTimeEntity;
import com.dgu.wantToGraduate.domain.chat.entity.ChatRoom;
import com.dgu.wantToGraduate.domain.matching.entity.PreferBrand;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class User extends BaseTimeEntity {

    // 필수 데이터
    // 닉네임, 프로필사진, 계좌번호, 이메일, 비밀번호, 평점

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "user_id")
    private Long id;

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

    @ManyToOne // User : ChatRoom = N : 1
    @JoinColumn(name = "chatRoom_id") // 생략 가능 => 기본전략사용 ( 필드명 + _ + 참조하는 테이블의 컬럼명)
    private ChatRoom chatRoom;

    @OneToMany(mappedBy = "user") // mappedBy : 연관관계의 주인이 아니다. (주인은 FK를 가진 UserReview)
    private List<UserReview> userReviews=new ArrayList<>();
}
