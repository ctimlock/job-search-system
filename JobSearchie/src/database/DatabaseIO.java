package database;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

public class DatabaseIO {
    private File file;
    private SimpleDateFormat formatter;

    public DatabaseIO(File file) {
        this.file = file;
        formatter = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
    }

    public File getFile() {
        return file;
    }

    public SimpleDateFormat getFormatter() {
        return formatter;
    }

    public ArrayList<HashMap<String, String>> getLines() {
        ArrayList<HashMap<String, String>> resultSet = new ArrayList<>();
        ArrayList<String> header = new ArrayList<>();
        try (Scanner sc = new Scanner(file)) {
            if (sc.hasNextLine()) {
                Arrays.asList(sc.nextLine().split(",")).forEach(element -> header.add(element.strip()));
            } else {
                return null;
            }
            while (sc.hasNextLine()) {
                String[] line = sc.nextLine().split(",");
                HashMap<String, String> result = new HashMap<>();
                for (int i = 0; i < line.length; i++) {
                    result.put(header.get(i), line[i].strip());
                }
                resultSet.add(result);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public void printLines() {
        try (Scanner sc = new Scanner(file)) {
            while (sc.hasNextLine()) {
                System.out.println(sc.nextLine());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setFile(File file) {
        this.file = file;
    }

    public void setFormatter(SimpleDateFormat formatter) {
        this.formatter = formatter;
    }
}
