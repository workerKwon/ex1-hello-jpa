package hellojpa.jpashop.domain;

import java.time.LocalDateTime;

import javax.persistence.MappedSuperclass;

/**
 * 상속 관계 매핑이 아니다
 * 엔티티가 아니라서 테이블과 매핑이 안된다.
 * 자식 클래스에 매핑 정보만 제공한다.
 * 그래서 MappedSuperclass로는 조회가 안된다.
 * 직접 생성해서 사용할 일이 없으므로 추상클래스로 만드는 것을 권장한다.
 */
@MappedSuperclass // 매핑 정보만 상속하는 부모 클래스.
public abstract class BaseEntity {

    private String createdBy;
    private LocalDateTime createdDate;
    private String lastModifiedBy;
    private LocalDateTime lastModifiedDate;

    public String getCreatedBy() {
        return this.createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDateTime getCreatedDate() {
        return this.createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public String getLastModifiedBy() {
        return this.lastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public LocalDateTime getLastModifiedDate() {
        return this.lastModifiedDate;
    }

    public void setLastModifiedDate(LocalDateTime lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }
    
}
