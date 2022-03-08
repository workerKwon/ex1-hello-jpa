package hellojpa.teammember.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Parent {
    @Id @GeneratedValue
    private Long id;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    private String name;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Cascade 영속성 전이. 
     *  - parent가 저장될 때 childList에 담겨있는 애들도 persist를 날릴것이냐. 
     *  - 연관관계의 애들을 같이 저장할 것이냐
     *  - 연관관계를 매핑하는 것과는 전혀 상관이 없다.
     *  - 단일 엔티티에 완전히 종속적일 때 사용해도 된다. 다른 엔티티에서도 연관이 되어있다면 사용하면 안된다.
     *  - 라이프 사이클이 똑같을 때(등록할때)
     * 
     * 고아 객체
     *  - 부모 엔티티와 연관관계가 끊어진 자식 엔티티를 자동으로 삭제하는 기능
     */ 
    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Child> childList = new ArrayList<>();

    public List<Child> getChildList() {
        return this.childList;
    }

    public void setChildList(List<Child> childList) {
        this.childList = childList;
    }

    public void addChild(Child child) {
        childList.add(child);
        child.setParent(this);
    }
}
