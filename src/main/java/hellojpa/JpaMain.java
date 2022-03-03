package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import hellojpa.jpashop.domain.Member;
import hellojpa.jpashop.domain.Order;

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

            // 데이터 중심 설계로 인한 불편함
            Long memberId = order.getMemberId();
            Member member = em.find(Member.class, memberId);
            System.out.println(member);

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
