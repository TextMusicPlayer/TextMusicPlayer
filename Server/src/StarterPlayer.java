import ru.ifmo.enf.micelius.core.BoletsContainer;
import ru.ifmo.enf.micelius.server.BoletsRequestHandler;
import ru.ifmo.enf.micelius.server.ConfigKeys;
import ru.ifmo.enf.micelius.server.Server;

import java.util.Properties;

/**
 * Author: Mihail Melnik
 * Date: 02.03.2012 19:54:00
*/
public class StarterPlayer {

    public static void main(final String[] args) {

        final Properties configs = new Properties();
        configs.put(ConfigKeys.PORT, "8018"); //Указываем порт
        configs.put(ConfigKeys.MAX_THREADS, "10"); // макс. кол-во одновременно обрабатываемых запросов
        final BoletsContainer boletsContainer = new BoletsContainer();
        boletsContainer.add("PlayerBolet", new PlayerBolet());
        final BoletsRequestHandler boletsRequestHandler = new BoletsRequestHandler(boletsContainer);
        new Server(configs, boletsRequestHandler);
    }
}
                                          