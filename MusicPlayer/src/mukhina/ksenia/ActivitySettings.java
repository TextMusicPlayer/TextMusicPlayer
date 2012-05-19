package mukhina.ksenia;

import android.os.Bundle;
import android.preference.PreferenceActivity;

/**
 * Created by IntelliJ IDEA.
 * User: mukhina_ks
 * Date: 05.04.12
 * Time: 10:50
 * To change this template use File | Settings | File Templates.
 */
public class ActivitySettings extends PreferenceActivity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.settings);
    }
}