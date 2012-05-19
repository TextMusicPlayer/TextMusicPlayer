package mukhina.ksenia.player.core;

import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.TagException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: mukhina_ks
 * Date: 23.04.12
 * Time: 7:15
 * To change this template use File | Settings | File Templates.
 */
public class PlayList {
    List<String> files = new ArrayList<String>();
    ArrayList<String> albums = new ArrayList<String>();
    ArrayList<String> artists = new ArrayList<String>();
    ArrayList<String> song = new ArrayList<String>();
    ArrayList<String> year = new ArrayList<String>();
    ArrayList<String> duration = new ArrayList<String>();

    public PlayList(){
        Searcher searcher = new Searcher();
        files= searcher.getSongs();
    }

    public void createAll() throws InvalidDataException, InvalidAudioFrameException, IOException, ReadOnlyFileException, TagException, UnsupportedTagException {
        for(String s: files){
            SongImpl song = new SongImpl(new File(s));
            albums.add(song.getAlbum());
            artists.add(song.getArtist());
            this.song.add(song.getTitle());
            year.add(song.getYear());
            duration.add(song.getDuration());
        }
    }
    
    public ArrayList<String> getAlbums(){
        return albums;
    }
    public ArrayList<String> getArtists(){
        return  artists;
    }
    public ArrayList<String> getSong(){
        return song;
    }
    public ArrayList<String> getYear(){
        return year;
    }
    public ArrayList<String> getDuration(){
        return duration;
    }
    public List<String> getFiles(){
        return files;
    }
}
