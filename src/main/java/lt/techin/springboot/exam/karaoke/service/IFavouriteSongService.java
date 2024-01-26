package lt.techin.springboot.exam.karaoke.service;

import lt.techin.springboot.exam.karaoke.modal.request.FavouriteSongRequest;
import lt.techin.springboot.exam.karaoke.modal.response.FavouriteSongResponse;

import java.util.List;

public interface IFavouriteSongService {

    FavouriteSongResponse findSongsByUserUuid(String uuid);

    void addNewSongsByUserUuid(
            String uuid, List<FavouriteSongRequest> songRequestListToAdd);

   void deleteFavouriteSongsByUser(
            String uuid, List<FavouriteSongRequest> songRequestListToDelete);
}
