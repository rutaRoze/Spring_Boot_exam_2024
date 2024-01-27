package lt.techin.springboot.exam.karaoke.modal.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lt.techin.springboot.exam.karaoke.persistance.modal.FavouriteSongRecord;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FavouriteSongResponse {

    private String username;

    private List<FavouriteSongRecord> songs;

}
