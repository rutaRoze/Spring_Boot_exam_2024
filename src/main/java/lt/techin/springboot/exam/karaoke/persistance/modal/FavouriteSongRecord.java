package lt.techin.springboot.exam.karaoke.persistance.modal;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FavouriteSongRecord that = (FavouriteSongRecord) o;
        return Objects.equals(artistName.toLowerCase().strip(), that.artistName.toLowerCase().strip())
                && Objects.equals(songTitle.toLowerCase().strip(), that.songTitle.toLowerCase().strip());
    }

    @Override
    public int hashCode() {
        return Objects.hash(artistName.toLowerCase().strip(), songTitle.toLowerCase().strip());
    }
}
