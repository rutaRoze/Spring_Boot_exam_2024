package lt.techin.springboot.exam.karaoke.persistance.modal;


import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name="favourite_songs")
public class FavouriteSongRecord {


    @Id
    @Column(name = "uuid")
    private String uuid;

    @Column(name = "artist_name")
    private String artistName;

    @Column(name = "song_title")
    private String songTitle;

}
