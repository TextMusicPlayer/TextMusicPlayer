package mukhina.ksenia.player.core;

import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.TagException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by IntelliJ IDEA.
 * User: mukhina_ks
 * Date: 22.03.12
 * Time: 22:43
 * To change this template use File | Settings | File Templates.
 */
public class Test {
    public static void main(String[] args) throws IOException, InvalidDataException, UnsupportedTagException, InvalidAudioFrameException, ReadOnlyFileException, TagException {

        PlayList playList = new PlayList();
        playList.createAll();
        ArrayList<String> list = playList.getAlbums();
        HashMap<String, ArrayList<Integer>> map;
        map = new HashMap<String, ArrayList<Integer>>();
        int i = 0;
        for(String string: list){
            if(map.containsKey(string)){
                map.get(string).add(i);
            } else{
                ArrayList<Integer> l = new ArrayList<Integer>();
                l.add(i);
                map.put(string, l);
            }
            i++;
        }
        ArrayList<String> artist = new ArrayList<String>();
        for(String s: map.keySet()){
            artist.add(s);
        }
        ArrayList<String> sublist = new ArrayList<String>();

            for (int k : map.get(artist.get(3))) {
                sublist.add(list.get(k));
            }
            list = sublist;

        System.out.println(list);
    }
}
