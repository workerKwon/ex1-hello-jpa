package hellojpa.teammember.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "MEMBERS")
public class Member{
    
    @Id @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name = "USERNAME")
    private String name;

    // @Column(name = "TEAM_ID")
    // private Long teamId;
    // public Long getTeamId() {
    //     return this.teamId;
    // }
    // public void setTeamId(Long teamId) {
    //     this.teamId = teamId;
    // }

    @ManyToOne(fetch = FetchType.EAGER) // Member 여럿에 Team 하나
    @JoinColumn(name = "TEAM_ID")
    private Team team;
    
    // 일대일 연관관계
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "LOCKER_ID")
    private Locker locker;

    // 다대다 연관관계 매핑. 연결 테이블을 생성해주는데. 보통 연결 테이블이 단순히 연결만하고 끝나지 않는다. 그래서 다대다는 사용안하는게 좋다.
    // @ManyToMany
    // @JoinTable(name = "MEMBER_PRODUCT")
    // private List<Product> products = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<MemberProduct> memberProducts = new ArrayList<>();

    @Embedded // 임베디드 타입
    private Period workPeriod;

    @Embedded
    private Address homeAddress;

    // 중복되는 임베디드 타입을 구분해서 사용하는 방법
    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name="city", 
                           column=@Column(name = "work_city")),
        @AttributeOverride(name="street", 
                           column=@Column(name = "work_street")),
        @AttributeOverride(name="zipcode", 
                           column=@Column(name = "work_zipcode"))
    })
    private Address workAddress;

    // 값 타입 컬렉션
    @ElementCollection
    @CollectionTable(name = "FAVORITE_FOOD", joinColumns = @JoinColumn(name = "MEMBER_ID"))
    @Column(name = "FOOD_NAME")
    private Set<String> favoritFoods = new HashSet<>();

    @ElementCollection
    @CollectionTable(name = "ADDRESS", joinColumns = @JoinColumn(name = "MEMBER_ID"))
    private List<Address> addressHistory = new ArrayList<>();

    public Set<String> getFavoritFoods() {
        return favoritFoods;
    }

    public void setFavoritFoods(Set<String> favoritFoods) {
        this.favoritFoods = favoritFoods;
    }

    public List<Address> getAddressHistory() {
        return addressHistory;
    }

    public void setAddressHistory(List<Address> addressHistory) {
        this.addressHistory = addressHistory;
    }

    public Address getHomeAddress() {
        return this.homeAddress;
    }

    public void setHomeAddress(Address homeAddress) {
        this.homeAddress = homeAddress;
    }

    public Period getWorkPeriod() {
        return this.workPeriod;
    }

    public void setWorkPeriod(Period workPeriod) {
        this.workPeriod = workPeriod;
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
    public Locker getLocker() {
        return this.locker;
    }

    public void setLocker(Locker locker) {
        this.locker = locker;
    }
}
