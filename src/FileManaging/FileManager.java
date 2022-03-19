package FileManaging;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileManager {
    public static List<String> readFromFile(String path){
        List<String> readWords = new ArrayList<>();
        File file = new File(path);
        try{
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) readWords.add(reader.nextLine());
            reader.close();
        }
        catch (Exception e){
            System.out.println("file not found");
        }
        return readWords;
    }
}
