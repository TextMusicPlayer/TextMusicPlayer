package mukhina.ksenia;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;

/**
 * Created by IntelliJ IDEA.
 * User: mukhina_ks
 * Date: 12.05.12
 * Time: 7:42
 * To change this template use File | Settings | File Templates.
 */
public class Widget extends AppWidgetProvider {
    public static String ACTION_WIDGET_RECEIVER = "ActionReceiverWidget";
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        //Создаем новый RemoteViews
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.main);

        //Подготавливаем Intent для Broadcast
        Intent active = new Intent(context, Widget.class);
        active.setAction(ACTION_WIDGET_RECEIVER);
        active.putExtra("msg", "Hello Habrahabr");

        //создаем наше событие
        PendingIntent actionPendingIntent = PendingIntent.getBroadcast(context, 0, active, 0);

        //регистрируем наше событие
        remoteViews.setOnClickPendingIntent(R.id.widget_button, actionPendingIntent);

        //обновляем виджет
        appWidgetManager.updateAppWidget(appWidgetIds, remoteViews);
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        //Ловим наш Broadcast, проверяем и выводим сообщение
        final String action = intent.getAction();
        if (ACTION_WIDGET_RECEIVER.equals(action)) {
            String msg = "null";
            try {
                msg = intent.getStringExtra("msg");
            } catch (NullPointerException e) {
                Log.e("Error", "msg = null");
            }
            Intent in = new Intent(context, MainActivity.class);
            in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(in);
        }
        super.onReceive(context, intent);
    }

}

