package lt.techin.springboot.exam.karaoke.persistance;

import lt.techin.springboot.exam.karaoke.persistance.modal.FavouriteSongRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FavouriteSongRepository extends JpaRepository<FavouriteSongRecord, Integer> {

    Optional<FavouriteSongRecord> findByArtistNameAndSongTitleIgnoreCase(String artistName, String songTitle);

}
