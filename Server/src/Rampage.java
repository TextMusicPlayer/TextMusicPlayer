import java.io.*;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Author: Mihail Melnik
 * Date: 02.03.2012 19:54:00
 */
public class Rampage {
    HashMap<String, String> base;
    File file;

    Rampage(String filePath) {
        file = new File(filePath);
        base = new HashMap<String, String>();
        dispersion();
    }

    private void dispersion() {
        try {
            Reader reader = null;
            reader = new FileReader(file);
            Scanner scanner = new Scanner(reader);
            String name = "";
            String text = "";
            if (scanner.hasNextLine()) {
                String current = scanner.nextLine();
                while (scanner.hasNextLine()) {
                    if (current.length() != 0) {
                        if (current.charAt(0) == '!') {
                            name = current.substring(1);
                            current = scanner.nextLine();
                            current = scanner.nextLine();
                            while (scanner.hasNextLine()) {
                                if (current.length() > 0) {
                                    if (current.charAt(0) == '!') {
                                        break;
                                    } else {
                                        text += current + "\n";
                                        current = scanner.nextLine();
                                    }
                                }
                                text += current + "\n";
                                current = scanner.nextLine();
                            }
                            base.put(name, text);
                            name = "";
                            text = "";
                        } else {
                            current = scanner.nextLine();
                        }
                    } else {
                        current = scanner.nextLine();
                    }
                }
            }
            scanner.close();
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

    }

    public String getText(String name) {
        if (base.containsKey(name)) {
            return name + "\n" + "\n" + base.get(name);
        } else {
            return "no text";
        }
    }
}
