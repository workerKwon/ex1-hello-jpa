package hellojpa.jpashop.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.swing.GroupLayout.Alignment;

@Entity
@Table(name = "ORDERS")
public class Order extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "ORDER_ID")
    private Long id;

    /**
     * 데이터 중심의 설계이다.
     * 연관관계의 데이터를 바로 꺼내올 수가 없다.
     */
    // @Column(name = "MEMBER_ID")
    // private Long memberId;

    /**
     * 객체 중심의 설계이다.
     * Order를 조회하면 그와 연관된 member를 getMember로 바로 조회할 수 있다.
     * 단방향 연관관계를 먼저 잘 해놓고 반대쪽 객체에서의 조회가 필요하면 양방향 연관관계를 추가하자.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "DELIVERY_ID")
    private Delivery delivery;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();

    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING) // Enum에 변경이 생길 때 ORDINAL 옵션일 경우 순서가 박혀서 데이터가 꼬일 수 있다. 필수로 STRING으로 해주자.
    private OrderStatus status;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // public Long getMemberId() {
    //     return this.memberId;
    // }

    // public void setMemberId(Long memberId) {
    //     this.memberId = memberId;
    // }

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

    // 연관관계 편의 메소드 (순수 객체 입장에서는 order에 orderItem이 들어있지 않을테니 양쪽 객체 모두에 연관관계인 값을 세팅)
    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

    public Member getMember() {
        return this.member;
    }
    public void setMember(Member member) {
        this.member = member;
    }

    public List<OrderItem> getOrderItems() {
        return this.orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

}
