package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

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
            // 등록
//            Member member = new Member();
//            member.setId(2L);
//            member.setName("HelloB");
//            em.persist(member);

            // 조회
//            Member findMember = em.find(Member.class, 1L);
//            System.out.println("findMember = " + findMember.getId());
//            System.out.println("findMember = " + findMember.getName());

            // 삭제
//            em.remove(findMember);

            // 수정
            // JPA를 통해서 데이터를 가져오면 JPA가 관리를 한다.
            // JPA가 트랜잭션을 커밋하는 시점에 바뀌었는지 체크를 하고 커밋하기 직전에 Update를 한다. 그래서 persist를 안해도 된다.
//            Member findMember = em.find(Member.class, 2L);
//            findMember.setName("helloJPA");

            // Member 테이블이 아니고 Member 객체를 대상으로 동작한다. 즉 Member 객체를 가져오라는 쿼리가 된다.
            // List<Member> resultList = em.createQuery("select m from Member as m", Member.class)
            //         .getResultList();

            // for(Member member : resultList) {
            //     System.out.println("member = " + member);
            // }

            Member member = new Member();
            member.setId(1L);
            member.setUsername("A");
            member.setRoleType(RoleType.GUEST);

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
