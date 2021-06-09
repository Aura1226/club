package org.donghyun.club.entity;

import lombok.*;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import java.util.HashSet;
import java.util.Set;


@Entity //자기만의 로직을 가지면 불변이 아닐 수 있다
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class ClubMember extends BaseEntity{

    @Id
    private String email;

    private String password;

    private String name;

    private boolean fromSocial;

    @ElementCollection(fetch = FetchType.LAZY)
    @Builder.Default //빌더로 만들어줄 때 디폴트로 넣어주게...없으면 초기화가 안됨...HashSet때문 //null?
    //처음 넣어 줄 때 부터 HashSet으로 넣어주기위해 = 넣어 줄 값을 HashSet으로 고정하기 위해
    private Set<ClubMemberRole> roleSet = new HashSet<>();

    public void addMemberRole(ClubMemberRole clubMemberRole){
        roleSet.add(clubMemberRole);
    }



}
