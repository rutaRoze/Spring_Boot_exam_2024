package lt.techin.springboot.exam.karaoke.modal.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lt.techin.springboot.exam.karaoke.persistance.modal.FavouriteSongRecord;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FavouriteSongRequest {

    @NotBlank
    List<FavouriteSongRecord> songs;

}
