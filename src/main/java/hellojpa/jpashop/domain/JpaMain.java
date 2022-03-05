package hellojpa.jpashop.domain;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 * 연관관계의 주인이 아닌 경우에는 조회말고는 할 수 없다.
 * 즉, 조회를 편하게 하기 위해서 양방향 연관관계를 설정하는 것이다.
 * 사실상 단방향 연관관계만 제대로 해도 애플리케이션 서비스에는 문제가 전혀 없다. 개발 편의를 위해서 양방향 연관관계를 하는 것이다.
 * 먼저 단방향 연관관계로 완벽하게 설계를 끝내놓고, 양방향 연관관계가 필요하면 추가하면 된다.
 * 1. 연관관계의 주인은 외래키가 있는 테이블의 엔티티로 한다.
 * 2. 연관관계 편의 메소드는 객체 입장에서 데이터가 없을 경우를 대비해서 편하게 양쪽에 값을 세팅하기 위해 만들어 주는 것이므로 당연히 없어도 무관하다.
 */
public class JpaMain {

    public static void main(String[] args) {

        // Persistence가 데이터베이스 설정 정보를 조회해서 entityManagerFactory를 만든다. DB당 하나씩 묶여서 사용된다.
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        // entityManager가 트랜잭션 단위의 쿼리를 처리해준다.
        EntityManager em = emf.createEntityManager();

        // JPA는 트랜잭션 안에서 수행을 해줘야한다.
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            Order order = em.find(Order.class, 1L);
            order.addOrderItem(new OrderItem()); // 양방향 연관관계 편의 메소드(order를 통해서 orderItem을 객체 단계에서 조회하기 위해 양쪽 객체에 모두 값을 세팅.)

            // 데이터 중심 설계로 인한 불편함
            // Long memberId = order.getMemberId();
            // Member member = em.find(Member.class, memberId);
            // System.out.println(member);

            // 객체 중심의 설계로 연관된 member를 바로 가져오도록 하자.
            order.getMember(); 

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
