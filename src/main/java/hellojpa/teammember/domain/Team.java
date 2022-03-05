package hellojpa.teammember.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Team {
    
    @Id @GeneratedValue
    @Column(name = "TEAM_ID")
    private Long id;

    private String name;

    /**
     * 연관되는 객체의 어떤 변수랑 매핑이 되는가. 연관관계의 주인이 누구인가를 설정하는 것.
     * 양방향 연관관계에서는 외래키를 업데이트 할 객체를 하나 정해야 한다. 
     * 두 객체에서 모두 업데이트를 하면 데이터가 꼬일 수 있기 때문에. 규칙이 하나 생긴것이다.
     */
    @OneToMany(mappedBy = "team")
    private List<Member> members = new ArrayList<>();

    public List<Member> getMembers() {
        return this.members;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
