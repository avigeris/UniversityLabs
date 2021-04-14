package treeSort;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class TreeSort {
    public static void main(String args[]) throws FileNotFoundException {
        Test test = new Test();
       // test.TestWithFile();
       // test.TestWithLineByLineReadFile();
        test.TestLargeFile();
    }

}
