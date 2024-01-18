package lt.techin.springboot.exam.karaoke.modal.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FavouriteSongResponse {

    private String artistName;

    private String songTitle;
}
