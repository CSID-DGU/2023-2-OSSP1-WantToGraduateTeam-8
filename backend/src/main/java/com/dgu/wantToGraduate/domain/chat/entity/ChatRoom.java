package com.dgu.wantToGraduate.domain.chat.entity;

import com.dgu.wantToGraduate.domain.BaseTimeEntity;
import com.dgu.wantToGraduate.domain.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChatRoom extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "chatRoom_id")
    private Long id;

    /*
    @OneToMany(mappedBy = "chatRoom")
    => ChatRoom:User = 1:N
    => 양방향 매핑을 위한 작업 using 'mappedBy=<반대쪽 매핑의 필드 이름>'
    => mappedBy 속성은 연관관계의 주인이 아니다. (주인은 FK를 가진 User)
    =>연관관계의 주인만이 데이터베이스 연관관계와 매핑되고 외래 키를 관리(등록, 수정, 삭제)할 수 있습니다. 반면에 주인이 아닌 쪽은 읽기만 할 수 있습니다.(mappedBy 속성은 읽기 전용입니다.)
    */
    @OneToMany(mappedBy = "chatRoom")
    List<User> users = new ArrayList<User>();

    @Column(nullable = false)
    private String roomId;
}
