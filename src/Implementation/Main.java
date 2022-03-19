package Implementation;


import FileManaging.FileManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    private static TreeRB<Integer> tree = new TreeRB<>();
    public static void main(String[] args){
        List<Integer> keys = new ArrayList<>();
        for(int i = 0; i < 100; i++){
            int k = (int) (Math.random() * 10000000);
            System.out.println(k);
            tree.insert(k);
            keys.add(k);
        }
        for(Integer k : keys){
            System.out.println(k);
            tree.delete(k);
        }
        for(int i = 0; i < 99; i++){
            for(int j = i; j < 100; j++){
                if(Objects.equals(keys.get(i), keys.get(j)))
                    System.out.println(i + "Repeated!!!");
            }
        }





//        tree.delete(10);
//        System.out.println("..............................");
//        tree.preOrderTraverse(tree.root);
//        tree.delete(6);
//        System.out.println("..............................");
//        tree.preOrderTraverse(tree.root);
//        tree.delete(14);
//        System.out.println("..............................");
//        tree.preOrderTraverse(tree.root);
//        tree.delete(16);
//        System.out.println("..............................");
//        tree.preOrderTraverse(tree.root);
//        tree.delete(20);
//        System.out.println("..............................");
//        tree.preOrderTraverse(tree.root);
//        tree.delete(21);
//        System.out.println("..............................");
//        tree.preOrderTraverse(tree.root);
//        System.out.println("RB Tree Implementation");
//        System.out.println("=======================");
//        while (true){
//            System.out.println("Choose a mode: ");
//            System.out.println("- 1...to receive input from keyboard");
//            System.out.println("- 2...to receive input from file");
//            System.out.println("- Or type anything else...to exit");
//            System.out.print("=> ");
//            Scanner read = new Scanner(System.in);
//            String mode = read.nextLine();
//            if(Objects.equals(mode, "1")){
//                while (true){
//                    System.out.println("- enter the word of the operation + the word");
//                    System.out.println("    e.g.: insert boy...delete apple...search car");
//                    System.out.println("- type \"exit\" ...to return to mode selection again");
//                    System.out.print("=> ");
//                    String input = read.nextLine();
//                    Boolean res = readIO(input);
//                    if(!res) break;
//                }
//            }
//            else if(Objects.equals(mode, "2")){
//                while (true){
//                    System.out.println("- enter the word of the operation + the file path");
//                    System.out.println("    e.g.: insert D:/jjk/jlkl...delete */apple/**...search /car/**");
//                    System.out.println("- type \"exit\" ...to return to mode selection again");
//                    System.out.print("=> ");
//                    String input = read.nextLine();
//                    Boolean res ;
//
//                        res = readFile(input + " E:\\studying\\aa.txt");
//
//                        if(!res) break;
//                }
//            }
//            else break;
//        }
    }
//    private static Boolean readIO(String input){
//        String[] splitted = input.split(" ");
//        String word;
//        if(splitted.length != 0){
//            if(splitted[0].equalsIgnoreCase("insert")){
//                word = splitted[1];
//                if(word != null)tree.insert(word);
//            }
//            else if(splitted[0].equalsIgnoreCase("delete")){
//                word = splitted[1];
//                if(word != null)tree.delete(word);
//            }
//            else if(splitted[0].equalsIgnoreCase("search")){
//                word = splitted[1];
//                if(word != null){
//                    if(tree.search(word) != null) System.out.println("found");
//                    else System.out.println("not found");
//                }
//            }
//            else return !splitted[0].equalsIgnoreCase("exit");
//        }
//        return true;
//    }
//
//    private static Boolean readFile(String input){
//        String[] splitted = input.split(" ");
//        List<String> words = new ArrayList<>();
//        String word ;
//        if(splitted.length != 0){
//            if(splitted[0].equalsIgnoreCase("insert")){
//                word = splitted[1];
//                if(word != null) words = FileManager.readFromFile(word);
//                long startTime = System.currentTimeMillis();
//                for (int i = 0; i < words.size(); i++) tree.insert(words.get(i));
//                System.out.println("Time taking for insertion : " + (System.currentTimeMillis() - startTime) + "ms");
//            }
//            else if(splitted[0].equalsIgnoreCase("delete")){
//                word = splitted[1];
//                if(word != null)words = FileManager.readFromFile(word);
//                long startTime = System.currentTimeMillis();
//
//                for (int i = 0; i < words.size(); i++) tree.delete(words.get(i));
//                System.out.println("Time taking for deletion : " + (System.currentTimeMillis() - startTime) + "ms");
//
//            }
//            else if(splitted[0].equalsIgnoreCase("search")){
//                word = splitted[1];
//                if(word != null) words = FileManager.readFromFile(word);
//                int a = 0;
//                for (int i = 0; i < words.size(); i++){
//                    if(tree.search(words.get(i)) != null) a++;
//                    else System.out.println("not found");
//                }
//                System.out.println(a);
//              //  tree.preOrderTraverse(tree.root);
//            }
//            else return !splitted[0].equalsIgnoreCase("exit");
//        }
//        return true;
//    }
}

//                 21b
//        3 r                  32b
//    2 b      7b
//         4 r     15 r

