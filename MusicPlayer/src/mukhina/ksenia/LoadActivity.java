package mukhina.ksenia;

import android.app.Activity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.ImageView;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Mathcad
 * Date: 18.04.12
 * Time: 21:55
 * To change this template use File | Settings | File Templates.
 */
public class LoadActivity extends Activity{

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
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.load);
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
        startTime();
    }

    public void startTime() {

        timer = new CountDownTimer(7000, 80) {
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
                timer.cancel();
            }
        };
        timer.start();
    }
}
