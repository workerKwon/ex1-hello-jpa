package hellojpa.jpashop.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class OrderItem extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "ORDER_ITEM_ID")
    private Long id;

    // @Column(name = "ORDER_ID")
    // private Long orderId;

    @ManyToOne
    @JoinColumn(name = "ORDER_ID")
    private Order order;

    public Order getOrder() {
        return this.order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    // @Column(name = "ITEM_ID")
    // private Long itemId;

    @ManyToOne
    @JoinColumn(name = "ITEM_ID")
    private Item item;

    public Item getItem() {
        return this.item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
    
    private int orderPrice;
    private int count;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // public Long getOrderId() {
    //     return this.orderId;
    // }

    // public void setOrderId(Long orderId) {
    //     this.orderId = orderId;
    // }

    // public Long getItemId() {
    //     return this.itemId;
    // }

    // public void setItemId(Long itemId) {
    //     this.itemId = itemId;
    // }

    public int getOrderPrice() {
        return this.orderPrice;
    }

    public void setOrderPrice(int orderPrice) {
        this.orderPrice = orderPrice;
    }

    public int getCount() {
        return this.count;
    }

    public void setCount(int count) {
        this.count = count;
    }
    
}
