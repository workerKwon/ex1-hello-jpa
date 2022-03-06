package hellojpa.jpashop.domain;

import javax.persistence.Entity;

@Entity
public class Album extends Item{
    
    private String artist;
    private String etc;

    public String getArtist() {
        return this.artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getEtc() {
        return this.etc;
    }

    public void setEtc(String etc) {
        this.etc = etc;
    }

}
