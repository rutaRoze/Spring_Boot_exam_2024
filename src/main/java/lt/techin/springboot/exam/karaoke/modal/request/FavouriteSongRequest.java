package lt.techin.springboot.exam.karaoke.modal.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FavouriteSongRequest {

    @NotBlank
    private String artistName;

    @NotBlank
    private String songTitle;

}
