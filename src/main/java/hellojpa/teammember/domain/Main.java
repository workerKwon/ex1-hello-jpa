package hellojpa.teammember.domain;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.h2.util.TempFileDeleter;

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

            // Team team = new Team();
            // team.setName("TeamA");
            // em.persist(team);

            // Member member = new Member();
            // member.setName("member1");
            // member.setTeam(team);
            // member.setCreatedBy("kim"); // MappedSuperclass를 사용한 필드 세팅.
            // member.setCreatedDate(LocalDateTime.now());

            // em.persist(member);

            // /**
            //  * 양방향 연관관계를 증명하려면 필요하다. 
            //  * 왜냐하면 Team 객체에 members를 set 해준 상태가 아니니까 1차 캐시의 team에 members 값이 존재하지 않는다.
            //  * 그래서 DB에 먼저 저장을 하고 1차 캐시를 모두 지운다음 
            //  * find를 통해서 DB에서 불러온 값을 1차 캐시에 다시 넣어서 양방향 연관관계를 증명할 수 있다.
            //  * 순수 객체 상태를 고려해서 객체 양쪽에 값을 설정하는 작업이 필요하다. 그래서 연관관계 편의 메소드를 사용했다.
            //  */
            // // em.flush(); 
            // // em.clear();

            // Member findMember = em.find(Member.class, member.getId());

            // List<Member> members = findMember.getTeam().getMembers();
            // for(Member m : members) {
            //     System.out.println("======= m = " + m.getName());
            // }

            // Team findTeam = findMember.getTeam();
            // System.out.println("findTeam = " + findTeam.getName());

            // // 이런식으로 하면 DB의 외래키 값이 업데이트 된다.
            // // Team newTeam = em.find(Team.class, 100L);
            // // findMember.setTeam(newTeam);







            // 상속관계 매핑
            // Movie movie = new Movie();
            // movie.setActor("aaaa");
            // movie.setDirector("bbbb");
            // movie.setName("name");
            // movie.setPrice(10000);

            // em.persist(movie);

            // // identity 전략이라서 persist 단계에서 DB에 저장을 하고, 저장된 값을 1차 캐시에 다시 저장해서 조회를 한다.
            // System.out.println("================");

            // em.flush();
            // em.clear();

            // Movie findMovie = em.find(Movie.class, movie.getId());
            // System.out.println("findMovie = " + findMovie.getId() + findMovie.getDirector());






            // 즉시로딩 지연로딩
            // Locker locker = new Locker();
            // locker.setName("LockerBBBB");
            // em.persist(locker);

            // Team team = new Team();
            // team.setName("TeamA");
            // em.persist(team);

            // Member member = new Member();
            // member.setName("member1");
            // member.setTeam(team);
            // member.setLocker(locker);
            // em.persist(member);

            // em.flush();
            // em.clear();

            // Member m = em.find(Member.class, member.getId());

            // System.out.println("---------------------");
            // m.getTeam().getName(); // 즉시로딩이라서 em.find(Member.class, member.getId())를 호출 할 때 1차 캐시에 저장된걸 불러오는 것이므로 쿼리를 또 날리지 않는다.
            // System.out.println("================");
            // m.getLocker().getName(); // OneToOne은 양쪽에서 지연로딩을 해줘야 한다.
            // System.out.println("================");





            


            // 영속성 전이
            Child child1 = new Child();
            Child child2 = new Child();

            Parent parent = new Parent();
            parent.addChild(child1);
            parent.addChild(child2);

            em.persist(parent);


            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
