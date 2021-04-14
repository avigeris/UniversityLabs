package treeSort;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Test {
    public void TestWithLineByLineReadFile() throws FileNotFoundException {
        File file = new File("test/1000000.txt");
            Scanner sc = new Scanner(file);
        long startTime = System.currentTimeMillis();
        Tree myTree = new Tree(sc.nextLine());
            while (sc.hasNextLine())
                myTree.insert(new Tree(sc.nextLine()));
        ArrayList<String> visited = new ArrayList<>();
        myTree.traverse(visited);
        long time = System.currentTimeMillis();
        long memoryUsed = Runtime.getRuntime().totalMemory()
                - Runtime.getRuntime().freeMemory();

        System.out.println("@@ n = " + " time " + (time - startTime)
                + "ms, using " + memoryUsed/(1024*1024) + "Mb");
        System.out.println("Used memory is bytes: " + memoryUsed);
    }
    public void TestWithFile() throws FileNotFoundException {
        File file = new File("test/1000000.txt");
        Scanner sc = new Scanner(file);
        ArrayList<String> arr = new ArrayList<>();
        while (sc.hasNextLine())
            arr.add(sc.nextLine());
        long startTime = System.currentTimeMillis();
        Tree myTree = new Tree(arr.get(0));
        arr.remove(0);
        myTree.insertArray(arr);
        ArrayList<String> visited = new ArrayList<>();
        ArrayList<String> res = myTree.traverse(visited);
        long time = System.currentTimeMillis();
        long memoryUsed = Runtime.getRuntime().totalMemory()
                - Runtime.getRuntime().freeMemory();
        System.out.println("@@ n = " + " time " + (time - startTime)
                + "ms, using " + memoryUsed/(1024*1024) + "Mb");
        System.out.println("Used memory is bytes: " + memoryUsed);
    }

    public void TestLargeFile() throws FileNotFoundException {
        long startTime = System.currentTimeMillis();
        FileSorter fs = new FileSorter();
        fs.sort("test/1000000.txt","test/large/cities_sorted.txt");
        long time = System.currentTimeMillis();
        long memoryUsed = Runtime.getRuntime().totalMemory()
                - Runtime.getRuntime().freeMemory();
        System.out.println("@@ n = " + " time " + (time - startTime)
                + "ms, using " + memoryUsed/(1024*1024) + "Mb");
        System.out.println("Used memory is bytes: " + memoryUsed);
    }


}
