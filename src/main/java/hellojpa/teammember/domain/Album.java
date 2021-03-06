package hellojpa.teammember.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@DiscriminatorValue("A") // DTYPE의 값을 바꿔서 넣을 수 있다.
@Table(name = "ALBUM2")
public class Album extends Item{
    
    private String artist;

    public String getArtist() {
        return this.artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

}
