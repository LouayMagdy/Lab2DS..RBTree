package Implementation;


import FileManaging.FileManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    private static TreeRB<String> tree = new TreeRB<>();
    public static void main(String[] args){
System.out.println("RB Tree Implementation");
        System.out.println("=======================");
        while (true){
            System.out.println("Choose a mode: ");
            System.out.println("- 1...to receive input from keyboard");
            System.out.println("- 2...to receive input from file");
            System.out.println("- Or type anything else...to exit");
            System.out.print("=> ");
            Scanner read = new Scanner(System.in);
            String mode = read.nextLine();
            if(Objects.equals(mode, "1")){
                while (true){
                    System.out.println("- enter the word of the operation + the word");
                    System.out.println("    e.g.: insert boy...delete apple...search car...contain ball");
                    System.out.println("- you can also enter :  - isEmpty?   - clear   - getRoot");
                    System.out.println("- type \"exit\" ...to return to mode selection again");
                    System.out.print("=> ");
                    String input = read.nextLine();
                    Boolean res = readIO(input);
                    if(!res) break;
                }
            }
            else if(Objects.equals(mode, "2")){
                while (true){
                    System.out.println("- enter the word of the operation + the file path");
                    System.out.println("    e.g.: insert D:/jjk/jlkl...delete */apple/**...search /car/**");
                    System.out.println("- type \"exit\" ...to return to mode selection again");
                    System.out.print("=> ");
                    String input = read.nextLine();
                    Boolean res ;
                        res = readFile(input);
                        if(!res) break;
                }
            }
            else break;
        }
    }
    private static Boolean readIO(String input){
        String[] splitted = input.split(" ");
        String word;
        if(splitted.length != 0){
            if(splitted[0].equalsIgnoreCase("insert")){
                word = splitted[1];
                if(word != null)tree.insert(word);
            }
            else if(splitted[0].equalsIgnoreCase("delete")){
                word = splitted[1];
                if(word != null)tree.delete(word);
            }
            else if(splitted[0].equalsIgnoreCase("search")){
                word = splitted[1];
                if(word != null){
                    if(tree.search(word) != null) System.out.println(tree.search(word).getValue());
                    else System.out.println("not found");
                }
            }
            else if(splitted[0].equalsIgnoreCase("contain")){
                word = splitted[1];
                if(word != null){
                    if(tree.contain(word)) System.out.println("found");
                    else System.out.println("not found");
                }
            }
            else if(splitted[0].equalsIgnoreCase("isEmpty?")){
                System.out.println(tree.isEmpty());
            }
            else if(splitted[0].equalsIgnoreCase("clear")){
                tree.clear();
            }
            else if(splitted[0].equalsIgnoreCase("getRoot")){
                tree.getRoot();
            }
            else return !splitted[0].equalsIgnoreCase("exit");
        }
        return true;
    }

    private static Boolean readFile(String input){
        String[] splitted = input.split(" ");
        List<String> words = new ArrayList<>();
        String word ;
        if(splitted.length != 0){
            if(splitted[0].equalsIgnoreCase("insert")){
                word = splitted[1];
                if(word != null) words = FileManager.readFromFile(word);
                long startTime = System.currentTimeMillis();
                for (int i = 0; i < words.size(); i++) tree.insert(words.get(i));
                System.out.println("Time taken for insertion : " + (System.currentTimeMillis() - startTime) + "ms");
            }
            else if(splitted[0].equalsIgnoreCase("delete")){
                word = splitted[1];
                if(word != null)words = FileManager.readFromFile(word);
                long startTime = System.currentTimeMillis();
                for (int i = 0; i < words.size(); i++) tree.delete(words.get(i));
                System.out.println("Time taken for deletion : " + (System.currentTimeMillis() - startTime) + "ms");
            }
            else if(splitted[0].equalsIgnoreCase("search")){
                word = splitted[1];
                if(word != null) words = FileManager.readFromFile(word);
                for (int i = 0; i < words.size(); i++){
                    if(tree.contain(words.get(i))) System.out.println("found");
                    else System.out.println("not found");
                }
            }
            else return !splitted[0].equalsIgnoreCase("exit");
        }
        return true;
    }
}
