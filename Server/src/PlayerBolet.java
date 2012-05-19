import ru.ifmo.enf.micelius.core.Bolet;
import ru.ifmo.enf.micelius.core.InnerRequest;
import ru.ifmo.enf.micelius.core.InnerResponse;

/**
 * Author: Mihail Melnik
 * Date: 02.03.2012 19:54:00
 */
public class PlayerBolet implements Bolet {
    public void process(final InnerRequest request, final InnerResponse response) {
        String name = request.getParameter("name");
        Rampage r = new Rampage("D:/Player/Server/src/soundsznak.txt");
        String dispersion = "";
        for (int i = 0; i < name.length(); i++) {
            char current = name.charAt(i);
            if (current == '_') {
                dispersion += ' ';
            } else {
                dispersion += current;
            }
        }
        String text = r.getText(dispersion);
        response.addObject("text", text);
    }
}
