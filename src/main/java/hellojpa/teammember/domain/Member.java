package hellojpa.teammember.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "MEMBERS")
public class Member {
    
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

    public void setTeam(Team team) {
        this.team = team;
    }


}
