package lt.techin.springboot.exam.karaoke.persistance;

import lt.techin.springboot.exam.karaoke.persistance.modal.FavouriteSongRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavouriteSongRepository extends JpaRepository<FavouriteSongRecord, String> {

}
