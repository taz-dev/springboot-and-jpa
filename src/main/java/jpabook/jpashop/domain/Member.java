package jpabook.jpashop.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String name;

    @Embedded
    private Address address;

    //@JsonIgnore -> 이건 정말 최악, api가 이거 하나뿐이 아님!!
    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();
}
