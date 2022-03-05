package hellojpa.teammember.domain;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Main {
    
    public static void main(String[] args) {

        // Persistence가 데이터베이스 설정 정보를 조회해서 entityManagerFactory를 만든다. DB당 하나씩 묶여서 사용된다.
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        // entityManager가 트랜잭션 단위의 쿼리를 처리해준다.
        EntityManager em = emf.createEntityManager();

        // JPA는 트랜잭션 안에서 수행을 해줘야한다.
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            Team team = new Team();
            team.setName("TeamA");
            em.persist(team);

            Member member = new Member();
            member.setName("member1");
            member.setTeam(team);
            em.persist(member);

            /**
             * 양방향 연관관계를 증명하려면 필요하다. 
             * 왜냐하면 Team 객체에 members를 set 해준 상태가 아니니까 1차 캐시의 team에 members 값이 존재하지 않는다.
             * 그래서 DB에 먼저 저장을 하고 1차 캐시를 모두 지운다음 
             * find를 통해서 DB에서 불러온 값을 1차 캐시에 다시 넣어서 양방향 연관관계를 증명할 수 있다.
             * 순수 객체 상태를 고려해서 객체 양쪽에 값을 설정하는 작업이 필요하다. 그래서 연관관계 편의 메소드를 사용했다.
             */
            // em.flush(); 
            // em.clear();

            Member findMember = em.find(Member.class, member.getId());

            List<Member> members = findMember.getTeam().getMembers();
            for(Member m : members) {
                System.out.println("======= m = " + m.getName());
            }

            Team findTeam = findMember.getTeam();
            System.out.println("findTeam = " + findTeam.getName());

            // 이런식으로 하면 DB의 외래키 값이 업데이트 된다.
            Team newTeam = em.find(Team.class, 100L);
            findMember.setTeam(newTeam);

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
