package lt.techin.springboot.exam.karaoke.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lt.techin.springboot.exam.karaoke.exception.NoEntriesFoundException;
import lt.techin.springboot.exam.karaoke.exception.UserNotFoundException;
import lt.techin.springboot.exam.karaoke.modal.request.FavouriteSongRequest;
import lt.techin.springboot.exam.karaoke.modal.response.FavouriteSongResponse;
import lt.techin.springboot.exam.karaoke.persistance.FavouriteSongRepository;
import lt.techin.springboot.exam.karaoke.persistance.UserRepository;
import lt.techin.springboot.exam.karaoke.persistance.modal.FavouriteSongRecord;
import lt.techin.springboot.exam.karaoke.persistance.modal.UserRecord;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class FavouriteSongService implements IFavouriteSongService {

    private FavouriteSongRepository favouriteSongRepository;
    private UserRepository userRepository;

    @Override
    public FavouriteSongResponse findSongsByUserUuid(String uuid) {

        UserRecord user = userRepository.findById(uuid)
                .orElseThrow(() -> new UserNotFoundException(uuid));

        FavouriteSongResponse response = new FavouriteSongResponse(user.getUsername(), user.getSongs());

        return response;
    }

    @Override
    public void addNewSongsByUserUuid(String uuid, List<FavouriteSongRequest> songRequestListToAdd) {

        UserRecord user = userRepository.findById(uuid)
                .orElseThrow(() -> new UserNotFoundException(uuid));

        for (FavouriteSongRequest favouriteSongRequest : songRequestListToAdd) {

            String artistName = favouriteSongRequest.getArtistName();
            String songTitle = favouriteSongRequest.getSongTitle();

            FavouriteSongRecord song = favouriteSongRepository
                    .findByArtistNameAndSongTitleIgnoreCase(artistName, songTitle)
                    .orElseGet(() -> favouriteSongRepository.save(new FavouriteSongRecord(artistName, songTitle)));

            if (!user.getSongs().contains(song)) {
                user.addSongs(song);
                song.addUser(user);

                userRepository.save(user);
            }
        }
    }

    @Override
    public void deleteFavouriteSongsByUser(String uuid, List<FavouriteSongRequest> songRequestListToDelete) {

        UserRecord user = userRepository.findById(uuid)
                .orElseThrow(() -> new UserNotFoundException(uuid));

        List<FavouriteSongRecord> currentUserSongsInTheList = user.getSongs();

        if (currentUserSongsInTheList.isEmpty()) {
            throw new NoEntriesFoundException("No favourites to delete for user - " + user.getUsername());
        }

        List<FavouriteSongRecord> songsForDeletion = songRequestListToDelete
                .stream()
                .map(requestSongToDelete -> new FavouriteSongRecord(
                        requestSongToDelete.getArtistName().toLowerCase().strip(),
                        requestSongToDelete.getSongTitle().toLowerCase().strip()
                ))
                .filter(songToDelete -> currentUserSongsInTheList.contains(songToDelete))
                .collect(Collectors.toList());

        if (songsForDeletion.isEmpty()) {
            throw new NoEntriesFoundException("Favourites entries do not match given request for user - " + user.getUsername());
        }

        currentUserSongsInTheList.removeAll(songsForDeletion);
        user.setSongs(currentUserSongsInTheList);

        userRepository.save(user);
    }
}
