package mukhina.ksenia;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: mukhina_ks
 * Date: 14.04.12
 * Time: 8:09
 * To change this template use File | Settings | File Templates.
 */
public class ActivitySingers extends ListActivity {
    private ArrayAdapter<String> adapter;
    ArrayList<String> list = new ArrayList<String>();
    ArrayList<String> album = new ArrayList<String>();
    ArrayList<String> song = new ArrayList<String>();

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        list = intent.getStringArrayListExtra("artist");
        album = intent.getStringArrayListExtra("album");
        song = intent.getStringArrayListExtra("song");
        final HashMap<String, List<Integer>> map = new HashMap<String, List<Integer>>();
        int i = 0;
        for(String string: list){
            if(map.containsKey(string)){
                map.get(string).add(i);
            } else{
                List<Integer> l = new ArrayList<Integer>();
                l.add(i);
                map.put(string, l);
            }
            i++;
        }
        ArrayList<String> artist = new ArrayList<String>();
        for(String s: map.keySet()){
            artist.add(s);
        }
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, artist);

        // присваиваем адаптер списку
        setListAdapter(adapter);
        AdapterView.OnItemClickListener itemListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                // TODO Auto-generated method stub
                String s = parent.getItemAtPosition(position).toString();
                Intent in = new Intent();
                in.putIntegerArrayListExtra("file", (ArrayList<Integer>) map.get(s));
                setResult(RESULT_OK, in);
                finish();
            }
        };
        getListView().setOnItemClickListener(itemListener);
    }
    // создание меню
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // TODO Auto-generated method stub
        // добавляем пункты меню
        menu.add(0, 1, 0, "Album");
        menu.add(0, 2, 1, "Artist");
        menu.add(0, 3, 2, "Songs");


        return super.onCreateOptionsMenu(menu);
    }

    // обновление меню
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // TODO Auto-generated method stub
        // пункты меню с ID группы = 1 видны, если в CheckBox стоит галка
        return super.onPrepareOptionsMenu(menu);
    }

    // обработка нажатий
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // TODO Auto-generated method stub
        switch (item.getItemId()){
            case 1:{
                Intent intent = new Intent(this, ActivityAlbum.class);
                intent.putStringArrayListExtra("artist", (ArrayList<String>) list);
                intent.putStringArrayListExtra("album", (ArrayList<String>) album);
                intent.putStringArrayListExtra("song", (ArrayList<String>) song);
                startActivityForResult(intent, 1);
                break;
            }
            case 2:{
                break;
            }
            case 3:{
                Intent intent = new Intent(this, ActivitySing.class);
                intent.putStringArrayListExtra("artist", (ArrayList<String>) list);
                intent.putStringArrayListExtra("album", (ArrayList<String>) album);
                intent.putStringArrayListExtra("song", (ArrayList<String>) song);
                startActivityForResult(intent, 1);
                break;
            }
        }

        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // запишем в лог значения requestCode и resultCode
        Log.d("myLogs", "requestCode = " + requestCode + ", resultCode = " + resultCode);
        // если пришло ОК
        if (resultCode == RESULT_OK) {
            if(data.hasExtra("file")){
                Intent in = new Intent();
                in.putIntegerArrayListExtra("file", data.getIntegerArrayListExtra("file"));
                setResult(RESULT_OK, in);
                finish();
            }
        }
    }

}