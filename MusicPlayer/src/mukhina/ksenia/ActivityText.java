package mukhina.ksenia;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: mukhina_ks
 * Date: 21.04.12
 * Time: 13:03
 * To change this template use File | Settings | File Templates.
 */
public class ActivityText extends Activity {
    TextView text;
   TextView title;
    String name = "all";

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.text);
        text = (TextView) findViewById(R.id.text);
       title = (TextView) findViewById(R.id.title);
        Intent intent = getIntent();
        try {
            if (intent.hasExtra("text")) {
                name = intent.getStringExtra("text");

            }
        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        title.setText(name);
       get();
    }


    public void get() {
        String revert = name;
        String submit = "";
        for (int i = 0; i < revert.length(); i++) {
            char current = revert.charAt(i);
            if (current == ' ') {
                submit += '_';
            } else {
                submit += current;
            }
        }
        String responseBody = "";
        String url = "http://192.168.1.66:8018/PlayerBolet?name=";
        HttpGet request = new HttpGet(url + submit);
        ResponseHandler<String> responseHandler = new BasicResponseHandler();
        HttpClient client = new DefaultHttpClient();
        try {
            responseBody = client.execute(request, responseHandler);
        } catch (IOException e) {
            text.setText("connection error");
        }
        JSONObject json = null;
        try {
            json = new JSONObject(responseBody);
            String res = json.get("text").toString();
            text.setText(res);
        } catch (JSONException e) {
            text.setText("parsing error");
        }
    }
}