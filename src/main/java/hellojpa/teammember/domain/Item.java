package hellojpa.teammember.domain;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

/**
 * Inheritance의 default 값은 item2 테이블 하나에 필드를 늘려서 저장하는 것 (SINGLE_TABLE)
 * - JOINED 전략은 상위 테이블과 하위 테이블로 나눠서 조인해서 호출하는 방식이다.
 *      - 객체, 설계와 잘 맞는 방식이라서 기본적으로 사용하면 좋다.
 *      - 테이블이 정규화되어 있어서 저장공간이 효율적이다
 *      - 다만, 조회시 조인을 많이 사용해서 성능 저하가 약간 발생하고, 
 *      - 데이터 저장 시 쿼리가 상위 테이블, 하위테이블에 각각 날아가므로 쿼리가 2번 호출된다.
 * 
 * - SINGLE_TABLE 전략은 ITEM 테이블 하나에 생성이 되고 한 테이블에 모든 데이터가 다 들어간다. 
 *      - 성능이 잘 나온다. 쿼리도 한번 친다. 왜냐 테이블이 하나니까 당연히 쿼리를 한번친다.
 *      - DiscriminatorColumn(DTYPE) 을 안넣어줘도 무조건 들어간다. 왜냐 없으면 구분할 수 있는 방법이 없기 때문에. 디폴트로 넣어준다.
 *      - 자식 엔티티가 매핑한 컬럼은 모두 null을 허용해야한다. ex) movie에 있는 컬럼은 album에서 사용하지 않기 때문
 * 
 * - TABLE_PER_CLASS 전략은 상위 테이블의 필드를 각각의 하위 테이블에 모두 다 넣어주고 상위 테이블을 만들지 않는 방식이다.
 *      - 사용하지 않는 전략이긴 하다. 여러 테이블 함께 조회할 때 성능이 느리기 때문에(Union Sql을 사용)
 *      - 기본으로 조인전략을 사용하고, 정말 단순한 상속관계인 경우 단일 테이블 전략을 사용하면 된다.
 *      - 상위 테이블은 추상클래스로 만들어줘야 한다. 왜냐 기본 클래스면 Item 테이블만 따로 사용하는 경우도 있다는 의미가 되서 Item 테이블을 생성한다.
 *      - 테이블을 생성하지 않기 때문에 Id IDENTITY 전략을 사용할 수 없다. 그래서 SEQUENCE로 해놓음.
 *      - 각 테이블로 나눠져 있고, 상위 테이블이 생성되지도 않기 때문에 DTYPE 설정을 넣어줘도 들어가지 않는다.
 *      - 근데, 객체 Item으로 조회를 할 경우 DB에는 테이블이 없기 때문에 모든 자식 테이블을 다 찾아보는 비효율적인 쿼리를 날리게 된다.
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@DiscriminatorColumn // DTYPE Column을 넣을지 말지하는 옵션
@Table(name = "ITEM2")
public abstract class Item {
    
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE) // Auto로 하면 1차 캐시에서 자동으로 id값을 늘리는 것으로 확인했다.
    private Long id;

    private String name;
    private int price;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return this.price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

}
