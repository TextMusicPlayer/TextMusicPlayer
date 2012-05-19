package mukhina.ksenia;


import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.*;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;
import mukhina.ksenia.player.core.PlayList;
import mukhina.ksenia.player.core.SongImpl;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.TagException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class MainActivity extends Activity {
//    private SeekBar seekBar;
    TextView editArtist;
    TextView editTitle;
    TextView editAlbum;
    TextView editYear;
    //TextView editDuration;
    TextView tracks;
    ImageView albumCover;
    Uri myUri;
    MediaPlayer mediaPlayer = new MediaPlayer();
    int position = 0;
    PlayList playList = new PlayList();
    List<String> files = playList.getFiles();
    List<String> artists;
    List<String> album;
    List<String> year;
   // List<String> duration;
    ArrayList<Integer> order = new ArrayList<Integer>();
    List<String> title;
    CountDownTimer timer;
    ImageView button1;
    ImageView button2;
    ImageView button3;
    ImageView button4;
    ImageView button5;
    ImageView button6;
    ImageView button7;
    ImageView button8;
    ImageView button9;
    ImageView button10;
    ImageView button11;
    ImageView button12;
    ImageView button13;
    ImageView button14;
    List<ImageView> list = new LinkedList<ImageView>();
    int i = 0;
    LinearLayout l1;
    TableLayout l2;


    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fucking_layout);

        button1 = (ImageView) findViewById(R.id.but1);
        button2 = (ImageView) findViewById(R.id.but2);
        button3 = (ImageView) findViewById(R.id.but3);
        button4 = (ImageView) findViewById(R.id.but4);
        button5 = (ImageView) findViewById(R.id.but5);
        button6 = (ImageView) findViewById(R.id.but6);
        button7 = (ImageView) findViewById(R.id.but7);
        button8 = (ImageView) findViewById(R.id.but8);
        button9 = (ImageView) findViewById(R.id.but9);
        button10 = (ImageView) findViewById(R.id.but10);
        button11 = (ImageView) findViewById(R.id.but11);
        button12 = (ImageView) findViewById(R.id.but12);
        button13 = (ImageView) findViewById(R.id.but13);
        button14 = (ImageView) findViewById(R.id.but14);
        l1 = (LinearLayout) findViewById(R.id.l1);
        l2 = (TableLayout) findViewById(R.id.l2);
        list.add(button1);
        list.add(button2);
        list.add(button3);
        list.add(button4);
        list.add(button5);
        list.add(button6);
        list.add(button7);
        list.add(button8);
        list.add(button9);
        list.add(button10);
        list.add(button11);
        list.add(button12);
        list.add(button13);
        list.add(button14);
        Log.d("ini", "ini");
        startTime();


    }

    public void startTime() {
        timer = new CountDownTimer(1000, 80) {
            @Override
            public void onTick(long l) {
            if (i == 14) {
                    i = 0;
                }
                ImageView current = list.get(i);
                i++;
                if (i == 14) {
                    i = 0;
                }
                ImageView next = list.get(i);
                next.setVisibility(0);
                current.setVisibility(10);
            }

            @Override
            public void onFinish() {
                try {
                    createPlayList();
                    Intent data = getIntent();

                    if (data.hasExtra("file")) {
                        order = data.getIntegerArrayListExtra("file");
                    } else {
                        for (int i = 0; i < files.size(); i++) {
                            order.add(i);
                        }
                    }
                    editTitle.setText(title.get(order.get(position)));
                    editArtist.setText(artists.get(order.get(position)));
                    editAlbum.setText(album.get(order.get(position)));
                    editYear.setText(year.get(order.get(position)));
                    //    editDuration.setText("");

                } catch (IOException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                } catch (UnsupportedTagException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                } catch (InvalidDataException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                } catch (TagException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                } catch (ReadOnlyFileException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                } catch (InvalidAudioFrameException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
               /* seekBar = (SeekBar) findViewById(R.id.SeekBar01);
                seekBar.setMax(mediaPlayer.getDuration());*/
                myUri = Uri.fromFile(new File(files.get(order.get(position))));

                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                try {
                    mediaPlayer.setDataSource(getApplicationContext(), myUri);
                    mediaPlayer.prepare();

                } catch (IOException e) {
                    editAlbum.setText("AAAAAAAAAA");
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
                list.get(i).setVisibility(10);
                l2.setVisibility(0);
                l1.setVisibility(10);
                timer.cancel();
            }
        };
        timer.start();
    }

    private void createPlayList() throws InvalidDataException, IOException, UnsupportedTagException, InvalidAudioFrameException, ReadOnlyFileException, TagException {
        playList.createAll();
        album = playList.getAlbums();
        artists = playList.getArtists();
        title = playList.getSong();
        year = playList.getYear();
        //duration = playList.getDuration();
    }


    public void playClick(View v) throws IOException, InvalidDataException, UnsupportedTagException, InvalidAudioFrameException, ReadOnlyFileException, TagException {
        mediaPlayer.start();
        startPlayProgressUpdater();
        editTitle.setText(title.get(order.get(position)));
        editArtist.setText(artists.get(order.get(position)));
        editAlbum.setText(album.get(order.get(position)));
        editYear.setText(year.get(order.get(position)));
       // editDuration.setText("");
        if (position == files.size()) {
            position = 0;
        }
        int count = position + 1;
        tracks.setText(count + " / " + order.size());
        String cover = new SongImpl(new File(files.get(order.get(position)))).getArt();
        File file = null;
        if (cover != null) {
            file = new File(cover);
            albumCover.setMaxHeight(20);
            albumCover.setMaxWidth(20);
            albumCover.setImageURI(Uri.fromFile(file));
            albumCover.setMaxHeight(20);
            albumCover.setMaxWidth(20);
        } else {
            albumCover.setImageResource(R.drawable.t);
        }
        if (file != null) {
            file.delete();
        }
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

            public void onCompletion(MediaPlayer arg0) {
                try {
                    nextClick(new View(MainActivity.this));
                } catch (IOException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                } catch (InvalidDataException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                } catch (UnsupportedTagException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                } catch (TagException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                } catch (ReadOnlyFileException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                } catch (InvalidAudioFrameException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
            }

        });
    }

    public void pauseClick(View v) {
        mediaPlayer.pause();
    }

    public void stopClick(View v) {
        mediaPlayer.stop();
    }

    public void nextClick(View v) throws IOException, InvalidDataException, UnsupportedTagException, InvalidAudioFrameException, ReadOnlyFileException, TagException {
        if (position < order.size() - 1) {
            play(++position);
        }
        if (position == order.size() - 1) {
            position = 0;
            play(position);
        }
    }

    public void previousClick(View v) throws IOException, InvalidDataException, UnsupportedTagException, InvalidAudioFrameException, ReadOnlyFileException, TagException {
        if (position > 0) {
            play(--position);
        }
        if (position == 0) {
            position = order.size() - 1;
            play(position);
        }
    }

    public void clicking(View v) throws InvalidDataException, InvalidAudioFrameException, IOException, ReadOnlyFileException, TagException, UnsupportedTagException {
        switch (v.getId()) {
            case R.id.all: {
                order = new ArrayList<Integer>();
                for (int i = 0; i < files.size(); i++) {
                    order.add(i);
                }
                play(position);
                break;
            }
            case R.id.random: {
                Random random = new Random();
                int length = files.size();
                ArrayList<Integer> list = new ArrayList<Integer>();
                while (list.size() != length) {
                    int a = random.nextInt(length);
                    if (!list.contains(a)) {
                        list.add(a);
                    }
                }
                order = list;
                position = 0;
                play(position);
                break;
            }
            case R.id.one: {
                order = new ArrayList<Integer>();
                order.add(position);
                position = 0;
                play(position);
                break;
            }
        }
    }

    public void showListClick(View v) {
        Intent intent = new Intent(this, ActivitySing.class);
        intent.putStringArrayListExtra("artist", (ArrayList<String>) artists);
        intent.putStringArrayListExtra("album", (ArrayList<String>) album);
        intent.putStringArrayListExtra("song", (ArrayList<String>) title);
        startActivityForResult(intent, 1);

    }

    public void settingsClick(View v) {
        Intent intent = new Intent(this, ActivitySettings.class);
        startActivity(intent);
    }

    public void textClick(View v) {
        Intent intent = new Intent(this, ActivityText.class);
        try {
            String name = artists.get(order.get(position)) + " - " + title.get(order.get(position));
            intent.putExtra("text", name);
        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // запишем в лог значения requestCode и resultCode
        Log.d("myLogs", "requestCode = " + requestCode + ", resultCode = " + resultCode);
        // если пришло ОК
        if (resultCode == RESULT_OK) {
            if (data.hasExtra("file")) {
                order = data.getIntegerArrayListExtra("file");
                position = 0;
                try {
                    play(position);
                } catch (IOException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                } catch (InvalidDataException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                } catch (InvalidAudioFrameException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                } catch (ReadOnlyFileException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                } catch (TagException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                } catch (UnsupportedTagException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }


            }
            if (data.hasExtra("position")) {
                position = data.getIntExtra("position", 0);
                try {
                    play(position);
                } catch (IOException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                } catch (InvalidDataException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                } catch (InvalidAudioFrameException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                } catch (ReadOnlyFileException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                } catch (TagException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                } catch (UnsupportedTagException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
            }
        }

    }

    public void onDestroy() {
        mediaPlayer.stop();
        super.onDestroy();
    }

    @Override
    public void onResume() {
        super.onResume();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        // читаем установленное значение из CheckBoxPreference
        if (prefs.getBoolean(getString(R.string.pref_openmode), false)) {
            try {
                playClick(new View(this));
            } catch (IOException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            } catch (InvalidDataException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            } catch (UnsupportedTagException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            } catch (TagException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            } catch (ReadOnlyFileException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            } catch (InvalidAudioFrameException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }
        String regular = prefs.getString(getString(R.string.pref_style), "");

       /*  if(regular.contains("Основной")){
            setContentView(R.layout.main);
        }
        if (regular.contains("Серый")){
            setContentView(R.layout.player_skin1);

        }
        if (regular.contains("Минималистичный")){
            setContentView(R.layout.skin2);
        }*/
        editArtist = (TextView) findViewById(R.id.lead_artist);
        editTitle = (TextView) findViewById(R.id.song_title);
        editAlbum = (TextView) findViewById(R.id.album_title);
        editYear = (TextView) findViewById(R.id.release_year);
        //  editDuration = (TextView) findViewById(R.id.duration);
        tracks = (TextView) findViewById(R.id.count);
        albumCover = (ImageView) findViewById(R.id.cover_album);
    }

    private void play(int idx) throws IOException, InvalidDataException, InvalidAudioFrameException, ReadOnlyFileException, TagException, UnsupportedTagException {
        mediaPlayer.reset();
        myUri = Uri.fromFile(new File(files.get(order.get(idx))));
        mediaPlayer.setDataSource(getApplicationContext(), myUri);
        mediaPlayer.prepare();
        playClick(getCurrentFocus());
        notification();
    }

    @Override
    public void onBackPressed() {
        // Делаем что-то нужное для нас
        notification();
        moveTaskToBack(true);
    }

    private void notification(){
        String ns = Context.NOTIFICATION_SERVICE;
        NotificationManager mNotificationManager = (NotificationManager) getSystemService(ns);

        int icon = R.drawable.icon;
        long when = System.currentTimeMillis();
        Notification notification = new Notification(icon, artists.get(order.get(position)) + " - " + title.get(order.get(position)), when);
        notification.flags = Notification.FLAG_ONGOING_EVENT;
        notification = updateNotification(notification);

        final int HELLO_ID = 1;
        mNotificationManager.notify(HELLO_ID, notification);
    }

    private Notification updateNotification(Notification notification){
        Context context = getApplicationContext();
        CharSequence contentTitle = artists.get(order.get(position));
        CharSequence contentText = title.get(order.get(position));

        Intent intent = new Intent(this, MainActivity.class);
        intent.setAction("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.LAUNCHER");
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, intent, 0);

        notification.setLatestEventInfo(context, contentTitle, contentText, contentIntent);
        return notification;
    }
    private final Handler handler = new Handler();
    public void startPlayProgressUpdater() {
       /* seekBar.setProgress(mediaPlayer.getCurrentPosition());

        if (mediaPlayer.isPlaying()) {
        Runnable notification = new Runnable() {
                public void run() {
                    startPlayProgressUpdater();
                    }
                };
            handler.postDelayed(notification,1000);
            }else{
            mediaPlayer.pause();
            seekBar.setProgress(0);
            }*/
        }
}
