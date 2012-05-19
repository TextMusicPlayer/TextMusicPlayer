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

/**
 * Created by IntelliJ IDEA.
 * User: mukhina_ks
 * Date: 20.04.12
 * Time: 22:01
 * To change this template use File | Settings | File Templates.
 */
public class ActivitySing extends ListActivity {
    private ArrayAdapter<String> adapter;
    ArrayList<String> list = new ArrayList<String>();
    ArrayList<String> album = new ArrayList<String>();
    ArrayList<String> artist = new ArrayList<String>();

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Intent intent = getIntent();
        list = intent.getStringArrayListExtra("song");
        album = intent.getStringArrayListExtra("album");
        artist = intent.getStringArrayListExtra("artist");
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, list);

        // присваиваем адаптер списку
        setListAdapter(adapter);
        AdapterView.OnItemClickListener itemListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position,
                                    long id) {
                String s = parent.getItemAtPosition(position).toString();
                Intent in = new Intent();
                in.putExtra("position", position);
                ArrayList<Integer> order = new ArrayList<Integer>();
                for (int i = 0; i < list.size(); i++) {
                    order.add(i);
                }
                in.putIntegerArrayListExtra("filej", order);
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
                intent.putStringArrayListExtra("artist", (ArrayList<String>) artist);
                intent.putStringArrayListExtra("album", (ArrayList<String>) album);
                intent.putStringArrayListExtra("song", (ArrayList<String>) list);
                startActivityForResult(intent, 1);
                break;
            }
            case 2:{
                Intent intent = new Intent(this, ActivitySingers.class);
                intent.putStringArrayListExtra("artist", (ArrayList<String>) artist);
                intent.putStringArrayListExtra("album", (ArrayList<String>) album);
                intent.putStringArrayListExtra("song", (ArrayList<String>) list);
                startActivityForResult(intent, 1);
                break;
            }
            case 3:{
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