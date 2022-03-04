package hellojpa.jpashop.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ORDERS")
public class Order {

    @Id @GeneratedValue
    @Column(name = "ORDER_ID")
    private Long id;

    /**
     * 데이터 중심의 설계이다.
     * 연관관계의 데이터를 바로 꺼내올 수가 없다.
     */
    @Column(name = "MEMBER_ID")
    private Long memberId;

    /**
     * 객체 중심의 설계이다.
     * Order를 조회하면 그와 연관된 member를 getMember로 바로 조회할 수 있다.
     */
    // private Member member;
    // public Member getMember() {
    //     return this.member;
    // }
    // public void setMember(Member member) {
    //     this.member = member;
    // }

    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING) // Enum에 변경이 생길 때 ORDINAL 옵션일 경우 순서가 박혀서 데이터가 꼬일 수 있다. 필수로 STRING으로 해주자.
    private OrderStatus status;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMemberId() {
        return this.memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public LocalDateTime getOrderDate() {
        return this.orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public OrderStatus getStatus() {
        return this.status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

}
