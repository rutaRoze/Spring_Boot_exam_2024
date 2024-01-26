package lt.techin.springboot.exam.karaoke.controller;


import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lt.techin.springboot.exam.karaoke.modal.request.FavouriteSongRequest;
import lt.techin.springboot.exam.karaoke.modal.response.FavouriteSongResponse;
import lt.techin.springboot.exam.karaoke.service.FavouriteSongService;
import org.hibernate.validator.constraints.UUID;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        return favouriteSongService.findSongsByUserUuid(uuid);
    }

    @PostMapping
    public FavouriteSongResponse addFavouriteSongsForUser(
            @RequestParam @UUID String uuid,
            @RequestBody @Valid List<FavouriteSongRequest> favouriteSongRequestList

    ) {
        favouriteSongService.addNewSongsByUserUuid(uuid, favouriteSongRequestList);

        return favouriteSongService.findSongsByUserUuid(uuid);
    }

    @DeleteMapping
    public FavouriteSongResponse deleteFavouriteSong(
            @RequestParam @UUID String uuid,
            @RequestBody @Valid List<FavouriteSongRequest> favouriteSongRequestList

    ) {
        favouriteSongService.deleteFavouriteSongsByUser(uuid, favouriteSongRequestList);

        return favouriteSongService.findSongsByUserUuid(uuid);
    }
}
