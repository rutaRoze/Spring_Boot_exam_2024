package lt.techin.springboot.exam.karaoke.modal.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class FavouriteSongRequest {

    @NotBlank
    private String artistName;

    @NotBlank
    private String songTitle;

}
