package hellojpa.teammember.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "MEMBERS")
public class Member extends BaseEntity{
    
    @Id @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "USERNAME")
    private String name;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // @Column(name = "TEAM_ID")
    // private Long teamId;
    // public Long getTeamId() {
    //     return this.teamId;
    // }
    // public void setTeamId(Long teamId) {
    //     this.teamId = teamId;
    // }

    @ManyToOne // Member 여럿에 Team 하나
    @JoinColumn(name = "TEAM_ID")
    private Team team;

    public Team getTeam() {
        return this.team;
    }

    /**
     * 연관관계 편의 메소드
     * @param team
     */
    public void setTeam(Team team) {
        this.team = team;
        team.getMembers().add(this); // 연관된 team 객체의 members에 연관된 member객체(this)를 넣어준다.
    }

    // 일대일 연관관계
    @OneToOne
    @JoinColumn(name = "LOCKER_ID")
    private Locker locker;

    public Locker getLocker() {
        return this.locker;
    }

    public void setLocker(Locker locker) {
        this.locker = locker;
    }

    // 다대다 연관관계 매핑. 연결 테이블을 생성해주는데. 보통 연결 테이블이 단순히 연결만하고 끝나지 않는다. 그래서 다대다는 사용안하는게 좋다.
    // @ManyToMany
    // @JoinTable(name = "MEMBER_PRODUCT")
    // private List<Product> products = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<MemberProduct> memberProducts = new ArrayList<>();
}
