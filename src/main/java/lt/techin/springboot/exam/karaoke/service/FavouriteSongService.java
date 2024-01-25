package lt.techin.springboot.exam.karaoke.service;

import lombok.AllArgsConstructor;
import lt.techin.springboot.exam.karaoke.exception.UserNotFoundException;
import lt.techin.springboot.exam.karaoke.modal.request.FavouriteSongRequest;
import lt.techin.springboot.exam.karaoke.modal.response.FavouriteSongResponse;
import lt.techin.springboot.exam.karaoke.persistance.FavouriteSongRepository;
import lt.techin.springboot.exam.karaoke.persistance.UserRepository;
import lt.techin.springboot.exam.karaoke.persistance.modal.FavouriteSongRecord;
import lt.techin.springboot.exam.karaoke.persistance.modal.UserRecord;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class FavouriteSongService implements IFavouriteSongService {

    private FavouriteSongRepository favouriteSongRepository;
    private UserRepository userRepository;


    @Override
    public FavouriteSongResponse findSongsByUserUuid(String uuid) {

        UserRecord user = userRepository.findById(uuid)
                .orElseThrow(() -> new UserNotFoundException("User not found by uuid - " + uuid));

        FavouriteSongResponse response = new FavouriteSongResponse(user.getUsername(), user.getSongs());

        return response;
    }

    @Override
    public FavouriteSongResponse addNewSongsByUserUuid(String uuid, List<FavouriteSongRequest> favouriteSongRequestList) {

        UserRecord user = userRepository.findById(uuid)
                .orElseThrow(() -> new UserNotFoundException("User not found by uuid - " + uuid));

        for (FavouriteSongRequest favouriteSongRequest : favouriteSongRequestList) {

            String artistName = favouriteSongRequest.getArtistName().toLowerCase().strip();
            String songTitle = favouriteSongRequest.getSongTitle().toLowerCase().strip();

            FavouriteSongRecord song = favouriteSongRepository
                    .findByArtistNameAndSongTitle(artistName, songTitle)
                    .orElseGet(() -> favouriteSongRepository.save(new FavouriteSongRecord(artistName, songTitle)));

            if (!user.getSongs().contains(song)) {
                user.addSongs(song);
                song.addUser(user);

                userRepository.save(user);
                favouriteSongRepository.save(song);
            }
        }

        FavouriteSongResponse response = new FavouriteSongResponse(user.getUsername(), user.getSongs());

        return response;
    }


}
