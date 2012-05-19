package mukhina.ksenia.player.core;

import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;

import java.io.File;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: mukhina_ks
 * Date: 23.03.12
 * Time: 23:16
 * To change this template use File | Settings | File Templates.
 */
public interface Song {
    String getTitle();

    String getArtist();

    String getAlbum();

    String getYear();

    File getSong();

    String getDuration() throws InvalidDataException, IOException, UnsupportedTagException;

    String getArt(); //return AbsolutePath to image (album art)
}
