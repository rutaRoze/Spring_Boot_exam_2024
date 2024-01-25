package lt.techin.springboot.exam.karaoke.service;

import lt.techin.springboot.exam.karaoke.modal.request.FavouriteSongRequest;
import lt.techin.springboot.exam.karaoke.modal.response.FavouriteSongResponse;

import java.util.List;

public interface IFavouriteSongService {

    FavouriteSongResponse findSongsByUserUuid(String uuid);

   FavouriteSongResponse addNewSongsByUserUuid(String uuid, List<FavouriteSongRequest> favouriteSongRequestList);
}
