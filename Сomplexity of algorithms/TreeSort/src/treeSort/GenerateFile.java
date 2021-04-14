package treeSort;

import java.io.*;
import java.util.Random;
import java.util.Scanner;

public class GenerateFile {
    public static void main(String args[]) throws IOException {
        Random rand = new Random(); //instance of random class
        int upperbound = 50;
        int lowerbound = 4;
        int count = 200000000;
        //generate random values from 0-24
        FileWriter fileWriter = new FileWriter("test/1000000.txt");
            PrintWriter printWriter = new PrintWriter(fileWriter);
            RandomString rs = new RandomString();
            for (int i = 1; i < count; i++){
                printWriter.print(rs.generateRandomString(rand.nextInt((upperbound - lowerbound) + 1) + lowerbound) + "\n");
            }
            printWriter.print(rs.generateRandomString(rand.nextInt((upperbound - lowerbound) + 1) + lowerbound));
            printWriter.close();



    }
}
