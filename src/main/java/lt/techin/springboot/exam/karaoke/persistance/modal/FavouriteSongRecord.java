package lt.techin.springboot.exam.karaoke.persistance.modal;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "favourite_songs")
public class FavouriteSongRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @JsonIgnore
    private int id;

    @Column(name = "artist_name")
    private String artistName;

    @Column(name = "song_title")
    private String songTitle;

    @JsonIgnore
    @ManyToMany(mappedBy = "songs",
            cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH},
            fetch = FetchType.LAZY)
    private List<UserRecord> users = new ArrayList<>();

    public FavouriteSongRecord() {
    }

    public FavouriteSongRecord(String artistName, String songTitle) {
        this.artistName = artistName;
        this.songTitle = songTitle;
    }

    public void addUser(UserRecord user) {
        users.add(user);
    }

}
