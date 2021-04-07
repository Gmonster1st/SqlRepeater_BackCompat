import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SqlRepeater {
    public static void main(String[] args) {
        String text;
        List<String> list = new ArrayList<>();

        if (args.length > 0) {
            text = args[0];
            if (text.substring(0, 3).equals("-f:")) {
                list = readFromFile(text.substring(3));
            }
        }

        saveToFile(list);
    }

    private static void saveToFile(List<String> list) {
        if (!list.isEmpty()) {
            try (FileWriter myWriter = new FileWriter("SqlRepeater.txt")) {
                for (String t : list) {
                    myWriter.write("UPDATE " + t + " SET DATAAREAID = 'isp' WHERE DATAAREAID = 'ivc'");
                    myWriter.write("\n");
                }
                myWriter.close();
                System.out.println("Successfully wrote to the file.");
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        }
    }

    private static List<String> readFromFile(String path) {
        List<String> list = new ArrayList<>();
        try {
            File myObj = new File(path);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                list.add(data.substring(1));
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return list;
    }
}

