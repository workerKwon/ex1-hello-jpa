package hellojpa;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity // JPA의 관리 대상으로 등록된다.
public class Member {

    @Id
    private Long id;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "name", nullable = false)
    private String username;

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column()
    private BigDecimal age;

    public BigDecimal getAge() {
        return this.age;
    }

    public void setAge(BigDecimal age) {
        this.age = age;
    }

    /**
     * EnumType에 ORDINAL을 쓰면 Enum에 값이 추가 됐을 때 순서가 저장됨으로 심각한 데이터 문제가 발생할 수 있다. 
     * 그래서 그냥 STRING으로 써야한다고 생각하면 된다. default는 ORDINAL이라서 꼭 적어줘야한다.
     */
    @Enumerated(EnumType.STRING) 
    private RoleType roleType;

    public RoleType getRoleType() {
        return this.roleType;
    }

    public void setRoleType(RoleType roleType) {
        this.roleType = roleType;
    }

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    public Date getCreatedDate() {
        return this.createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    public Date getLastModifiedDate() {
        return this.lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    @Lob
    private String description;

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * LocalDate, LocalDateTime을 사용하면 어노테이션이 없어도 하이버네이트에서 맵핑해준다.
     */
    private LocalDate testLocalDate;
    private LocalDateTime testLocalDateTime;


    public Member() {}
    
}
