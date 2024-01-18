package lt.techin.springboot.exam.karaoke.controller;


import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lt.techin.springboot.exam.karaoke.modal.request.FavouriteSongRequest;
import lt.techin.springboot.exam.karaoke.modal.response.FavouriteSongResponse;
import lt.techin.springboot.exam.karaoke.service.FavouriteSongService;
import org.hibernate.validator.constraints.UUID;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequestMapping("/favourites")
@AllArgsConstructor
public class FavouriteSongController {

    private FavouriteSongService favouriteSongService;

    @GetMapping
    public FavouriteSongResponse getUserFavouriteSongs(
            @RequestParam @UUID String uuid
    ) {
        return favouriteSongService.findFavouriteSongsByUser(uuid);
    }

    @PostMapping
    public void recordReaction(
            @RequestParam @UUID String uuid,
            @RequestBody @Valid FavouriteSongRequest favouriteSongRequest

    ) {
        favouriteSongService.saveFavouriteSong(uuid, favouriteSongRequest);
    }

    @DeleteMapping
    public void deleteFavouriteSong(
            @RequestParam @UUID String uuid,
            @RequestBody @Valid FavouriteSongRequest favouriteSongRequest

    ) {
        favouriteSongService.deleteFavouriteSong(uuid, favouriteSongRequest);
    }
}
