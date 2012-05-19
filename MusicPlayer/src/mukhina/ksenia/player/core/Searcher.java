package mukhina.ksenia.player.core;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: mukhina_ks
 * Date: 11.04.12
 * Time: 21:15
 * To change this template use File | Settings | File Templates.
 */
public class Searcher {
    private final String MEDIA_PATH = new String("mnt/sdcard");
    private List<String> songs = new ArrayList<String>();
    public void updateSongList(String path) {
        File home = new File(path);
        if (home.listFiles().length > 0) {
            for (File file : home.listFiles(new Mp3Filter())) {
                songs.add(file.getAbsolutePath());
            }
            try {
                for (File file: home.listFiles(new Directory())){
                    updateSongList(file.getPath());
                }
            } catch (Exception e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
            /*  ArrayAdapter<String> songList = new ArrayAdapter<String>(this,
                    R.layout.song_item, songs);
            setListAdapter(songList);*/
        }
    }
    public List<String> getSongs(){
        updateSongList(MEDIA_PATH);
        return songs;
    }

    class  Mp3Filter implements FilenameFilter {
        public boolean accept(File dir, String name) {
            return (name.endsWith(".mp3"));
        }
    }

    class Directory implements FileFilter {

        public boolean accept(File file) {
            return file.isDirectory();
        }
    }

}
