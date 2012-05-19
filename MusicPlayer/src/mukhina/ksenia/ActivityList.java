package mukhina.ksenia;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;

import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: mukhina_ks
 * Date: 05.04.12
 * Time: 10:01
 * To change this template use File | Settings | File Templates.
 */
public class ActivityList extends TabActivity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list);
        TabHost tabHost = getTabHost();
        TabHost.TabSpec spec;
        Intent intent = getIntent();
        ArrayList<String> album = intent.getStringArrayListExtra("album");
        ArrayList<String> artist = intent.getStringArrayListExtra("artist");
        intent = new Intent().setClass(this, ActivitySingers.class);
        intent.putStringArrayListExtra("artist", artist);
        spec = tabHost.newTabSpec("tab1").setIndicator("Artist").setContent(intent);
        tabHost.addTab(spec);

        intent = new Intent().setClass(this, ActivityAlbum.class);
        intent.putExtra("name", "all");
        intent.putStringArrayListExtra("album", album);
        intent.putStringArrayListExtra("artist", artist);
        spec = tabHost.newTabSpec("tab2").setIndicator("Album").setContent(intent);
        tabHost.addTab(spec);

        /*intent = new Intent().setClass(this, ActivitySing.class);
        spec = tabHost.newTabSpec("tab2").setIndicator("Song").setContent(intent);
        tabHost.addTab(spec);*/

    }


}

