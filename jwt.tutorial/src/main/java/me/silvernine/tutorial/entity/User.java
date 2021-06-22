package me.silvernine.tutorial.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity // 데이터베이스 테이블하고 1:1로 매핑되는 객체
@Table(name = "user") // 테이블명은 user로 지정
@Getter // lombak
@Setter // lombak
@Builder // lombak
@AllArgsConstructor // 생성자 어노테이션 - lombak
@NoArgsConstructor
public class User {

   @JsonIgnore
   @Id // primary key
   @Column(name = "user_id")
   @GeneratedValue(strategy = GenerationType.IDENTITY) // 자동증가
   private Long userId;

   @Column(name = "username", length = 50, unique = true)
   private String username;

   @JsonIgnore
   @Column(name = "password", length = 100)
   private String password;

   @Column(name = "nickname", length = 50)
   private String nickname;

   @JsonIgnore
   @Column(name = "activated")
   private boolean activated; // 활성화 여부

   @ManyToMany
   @JoinTable( // user 객체와 authority 객체의 다대다 관계를 중간에 테이블을 둬서 1대다 다대1 관계로 정의했다는 뜻
      name = "user_authority",
      joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "user_id")},
      inverseJoinColumns = {@JoinColumn(name = "authority_name", referencedColumnName = "authority_name")})
   private Set<Authority> authorities; // 권한에 대한 관계
}