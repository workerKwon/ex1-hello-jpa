package hellojpa.teammember.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "MOVIE2")
public class Movie extends Item {

    private String director;
    private String actor;

    public String getDirector() {
        return this.director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getActor() {
        return this.actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }
    
}
