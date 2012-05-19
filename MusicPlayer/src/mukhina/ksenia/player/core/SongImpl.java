package mukhina.ksenia.player.core;


import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;
import com.mpatric.mp3agic.app.Mp3Pics;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.audio.mp3.MP3File;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.TagException;

import java.io.File;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: mukhina_ks
 * Date: 23.03.12
 * Time: 23:22
 * To change this template use File | Settings | File Templates.
 */
public class SongImpl implements Song {
    private File song;
    String artist = "";
    String album ="";
    String song_title ="";
    Number track_number;
    String year ="";
    String art ="";
    String duration;

    public SongImpl(File file) throws IOException, InvalidDataException, UnsupportedTagException, InvalidAudioFrameException, ReadOnlyFileException, TagException {
        /*song = file;
        Mp3File mp3File = new Mp3File(song.getAbsolutePath());
        if(mp3File.hasId3v1Tag()){
            ID3v1 tag =  mp3File.getId3v1Tag();
            artist = tag.getArtist();
            album = tag.getAlbum();
            song_title = tag.getTitle();
            year = tag.getYear();
        } else {
            if(mp3File.hasId3v2Tag()){
                ID3v2 tag  = mp3File.getId3v2Tag();
                artist = tag.getArtist();
                album = tag.getAlbum();
                song_title = tag.getTitle();
                year = tag.getYear();

            }
        }*/
        MP3File mp3File = new MP3File(file);
        Tag tag = mp3File.getTag();
        artist = tag.getFirst(FieldKey.ARTIST);
        album = tag.getFirst(FieldKey.ALBUM);
        song_title = tag.getFirst(FieldKey.TITLE);
        year = tag.getFirst(FieldKey.YEAR);
        song = file;
      //  MusicMetadataSet src_set = new MyID3().read(song); //creating tag
      //  IMusicMetadata metadata = src_set.getSimplified();
        // extract tags

    }

    public String getDuration() throws InvalidDataException, IOException, UnsupportedTagException {

            Mp3File mp3File = new Mp3File(song.getAbsolutePath());
        long length =  mp3File.getLengthInSeconds();
        long sec = (length % 60) ;
        int min =  (int)(length / 60);
        long hour = (length - min*60 - sec)/3600;
        if (hour>0){
            duration = hour + ":";
        } else {
            duration = "00:";
        }
        if (min<10){
            duration +=  "0"+ min;
        } else{
            duration += min;
        }
        if (sec<10){
            duration +=  ":0"+ sec;
        } else{
            duration += ":"+sec;
        }
              return duration;
        }

    public String getTitle() {
        try {
            return song_title;
        } catch (NullPointerException e) {
            return "Untitled";
        }
    }

    public String getArtist() {
        try {
            return artist.equals("")?"Unknown":artist;
        } catch (NullPointerException e) {
            return "Unknown";
        }
    }

    public String getAlbum() {
        try {
            return album.equals("")?"Unknown":album;
        } catch (NullPointerException e) {
            return "Unknown";
        }
    }

    public String getYear() {
        try {
            return year;
        } catch (NullPointerException e) {
            return "Unknown";
        }
    }


    public File getSong() {
        return song;
    }

    public String getArt() {
       Mp3Pics mp3Pics = new Mp3Pics(song.getAbsolutePath());   //create image
       art = mp3Pics.getFileName();
        return art;
    }


}
