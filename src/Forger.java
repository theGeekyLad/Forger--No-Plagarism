import java.io.*;
import java.util.HashMap;

public class Forger {

    private HashMap<Character, Character> map;

    public Forger() {
        map = new HashMap<>();
        map.put('S','\u0405');
        map.put('I', '\u04C0');
        map.put('J', '\u0408');
        map.put('A', '\u0410');
        map.put('B', '\u0412');
        map.put('E', '\u0415');
        map.put('M', '\u041C');
        map.put('H', '\u041D');
        map.put('O', '\u041E');
        map.put('P', '\u0420');
        map.put('C', '\u0421');
        map.put('T', '\u0422');
        map.put('a', '\u0430');
        map.put('e', '\u0435');
        map.put('o', '\u043E');
        map.put('p', '\u0440');
        map.put('c', '\u0441');
        map.put('x', '\u0445');
        map.put('s', '\u0455');
        map.put('i', '\u0456');
        map.put('j', '\u0458');
        map.put('w', '\u0461');
        map.put('Y', '\u04AE');
        //map.put('h', '\u04BB');
        //map.put('l', '\u04C0');
    }

    public boolean forge(File file) {
        try {
            if (!file.getName().endsWith(".txt")) {
                System.out.println("Please supply a .txt file.");
                return false;
            }
            String path = file.getAbsolutePath();
            File targetFile = new File(new StringBuilder(path).replace(path.length()-4, path.length(), " [FORGED].txt").toString());
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(targetFile));
            String t;
            StringBuilder builder = new StringBuilder();
            while ((t=bufferedReader.readLine()) != null) {
                for (int i=0; i<t.length(); i++) {
                    char ch = t.charAt(i);
                    if (map.containsKey(ch))
                        builder.append(map.get(ch));
                    else
                        builder.append(ch);
                }
                builder.append("\n");
            }
            bufferedWriter.write(builder.toString());
            bufferedReader.close();
            bufferedWriter.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return true;
    }

    public static void main(String[] args) {
        Forger forger = new Forger();
        if (forger.forge(new File("C:\\Users\\Public\\Documents\\Sample.txt")))
            System.out.println("Forge complete.");
        else
            System.out.println("Forge failed.");
    }
}