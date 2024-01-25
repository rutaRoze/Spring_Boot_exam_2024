package lt.techin.springboot.exam.karaoke.service;

import lombok.AllArgsConstructor;
import lt.techin.springboot.exam.karaoke.exception.UserNotFoundException;
import lt.techin.springboot.exam.karaoke.modal.response.FavouriteSongResponse;
import lt.techin.springboot.exam.karaoke.persistance.FavouriteSongRepository;
import lt.techin.springboot.exam.karaoke.persistance.UserRepository;
import lt.techin.springboot.exam.karaoke.persistance.modal.FavouriteSongRecord;
import lt.techin.springboot.exam.karaoke.persistance.modal.UserRecord;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
    public FavouriteSongResponse addNewSongsByUserUuid(String uuid, List<FavouriteSongRecord> newSongs) {

        UserRecord user = userRepository.findById(uuid)
                .orElseThrow(() -> new UserNotFoundException("User not found by uuid - " + uuid));

//        List<FavouriteSongRecord> currentSongsList = user.getSongs();
//
//        currentSongsList.addAll(newSongs);
//
//        List<FavouriteSongRecord> updatedSongList = currentSongsList
//                .stream()
//                .map(song -> new FavouriteSongRecord(
//                        song.getArtistName().toLowerCase().strip(),
//                        song.getSongTitle().toLowerCase().strip())
//                )
//                .distinct()
//                .collect(Collectors.toList());
//
//        user.setSongs(updatedSongList);

        for (FavouriteSongRecord newSong : newSongs) {

            String artistName = newSong.getArtistName().toLowerCase().strip();
            String songTitle = newSong.getSongTitle().toLowerCase().strip();

            FavouriteSongRecord existingSong = favouriteSongRepository
                    .findByArtistNameAndSongTitle(artistName, songTitle)
                    .orElseGet(() -> favouriteSongRepository.save(new FavouriteSongRecord(artistName, songTitle)));

            if (!user.getSongs().contains(existingSong)) {
                user.addSongs(existingSong);
                existingSong.addUser(user);

                userRepository.save(user);
                favouriteSongRepository.save(existingSong);

            }
        }

        List<FavouriteSongRecord> usersFavouriteSongsList = user.getSongs()
                .stream()
                .map(song -> new FavouriteSongRecord(
                        song.getArtistName(),
                        song.getSongTitle())
                )
                .distinct()
                .collect(Collectors.toList());

        FavouriteSongResponse response = new FavouriteSongResponse(user.getUsername(), usersFavouriteSongsList);

        return response;
    }


}
