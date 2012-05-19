package mukhina.ksenia.player.core;

/**
 * Created by Max Losevskoy (magistrsmi@yandex.ru)
 * 12:30 21.04.12
 */
public class PositionPrinter {
    public String positionPrinter(final long num) {
        String res = "";
        int days = (int)num / 86400;
        if (days > 0) {
            res += days;
            res += ":";
        }
        print((int) ((num % 86400) / 3600), res);
        print((int) (((num % 86400) % 3600) / 60), res);
        print((int) (((num % 86400) % 3600) % 60), res);

        return res;

    }

    private static void print(final int i, String res) {
        if (i == 0) {
            res += "00:";
        } else {
            res += i;
            res += ":";
        }
    }
}

