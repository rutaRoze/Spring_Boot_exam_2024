package lt.techin.springboot.exam.karaoke.persistance.modal;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@Entity
@Table(name = "users")
public class UserRecord {

    @Id
    //@GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "uuid")
    private String uuid;

    @Column(name = "username")
    private String username;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH},
            fetch = FetchType.EAGER)
    @JoinTable(name = "user_song",
            joinColumns = @JoinColumn(name = "user_uuid"),
            inverseJoinColumns = @JoinColumn(name = "song_id"))
    private List<FavouriteSongRecord> songs;

    public UserRecord() {
    }

    public UserRecord(String username, String uuid) {
        this.uuid = uuid;
        this.username = username;
    }

    public void addSongs(FavouriteSongRecord favouriteSong) {

        if(songs == null) {
            songs = new ArrayList<>();
        }

        songs.add(favouriteSong);
    }


}
