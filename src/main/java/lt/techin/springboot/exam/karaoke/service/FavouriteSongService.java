package lt.techin.springboot.exam.karaoke.service;

import lombok.AllArgsConstructor;
import lt.techin.springboot.exam.karaoke.exception.UserNotFoundException;
import lt.techin.springboot.exam.karaoke.modal.request.FavouriteSongRequest;
import lt.techin.springboot.exam.karaoke.modal.response.FavouriteSongResponse;
import lt.techin.springboot.exam.karaoke.persistance.FavouriteSongRepository;
import lt.techin.springboot.exam.karaoke.persistance.modal.FavouriteSongRecord;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class FavouriteSongService {

    private FavouriteSongRepository favouriteSongRepository;

    public FavouriteSongResponse findFavouriteSongsByUser(String uuid) {

        Optional<FavouriteSongRecord> searchResult = favouriteSongRepository.findById(uuid);

        FavouriteSongResponse favouriteSong;

        if (searchResult.isPresent()) {
            favouriteSong = new FavouriteSongResponse(searchResult.get().getArtistName(),
                    searchResult.get().getSongTitle());
        } else {
            throw new UserNotFoundException("Did not find user by id - " + uuid);
        }

        return favouriteSong;
    }

    public void saveFavouriteSong(String uuid, FavouriteSongRequest favouriteSongRequest) {

        Optional<FavouriteSongRecord> searchResult = favouriteSongRepository.findById(uuid);

        FavouriteSongRecord favouriteSongRecord;

        if (searchResult.isPresent()) {
            favouriteSongRecord = new FavouriteSongRecord(
                    uuid,
                    favouriteSongRequest.getArtistName(),
                    favouriteSongRequest.getSongTitle()
            );
        } else {
            throw new UserNotFoundException("Did not find user by id - " + uuid);
        }

        favouriteSongRepository.save(favouriteSongRecord);
    }

    public void deleteFavouriteSong(String uuid, FavouriteSongRequest favouriteSongRequest) {

        Optional<FavouriteSongRecord> userSearchResult = favouriteSongRepository.findById(uuid);

        if (userSearchResult.isPresent()) {



            );
        } else {
            throw new UserNotFoundException("Did not find user by id - " + uuid);
        }

    }
}
