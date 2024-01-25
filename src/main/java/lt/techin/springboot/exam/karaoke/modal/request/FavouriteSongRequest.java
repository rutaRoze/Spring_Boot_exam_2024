package lt.techin.springboot.exam.karaoke.modal.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FavouriteSongRequest {

    @NotBlank
    @NotEmpty
    private String artistName;

    @NotBlank
    private String songTitle;

}
