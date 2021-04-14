package treeSort;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class FileSorter {
    private static int LIMIT_STRING = 1000000;
    private static int numSplitFile;
    private static ArrayList<BufferedReader> listStream;

    public static class Pair{
        public String data;
        public BufferedReader br;
        public Pair(String data, BufferedReader br){
            this.data = data;
            this.br = br;
        }
    }

    public static void sort(String inputFile ,String outputFile){
        numSplitFile = 0;
        System.out.println("Reading and Splitting file........ ");
        if(readAndSplitFile(inputFile)){
            System.out.println("Merge file........ ");
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));
                createListStream();
                PriorityQueue<Pair> pq = new PriorityQueue<>(Comparator.comparing(o -> o.data));
                for(BufferedReader br : listStream){
                    String line;
                    if((line = br.readLine()) != null){
                        pq.add(new Pair(line,br));
                    }
                }
                while(pq.size() > 0){
                    Pair pair = pq.poll();
                    writer.write(pair.data);
                    writer.newLine();
                    String line;
                    if((line = pair.br.readLine()) != null){
                        pair.data = line;
                        pq.add(pair);
                    }else{
                        pair.br.close();
                    }
                }

                writer.close();
                deleteFile();

            }catch(Exception e){
                System.out.println("something wrong!!!");
                return;
            }
        }
    }

    public static void deleteFile(){
        for(int i = 0 ; i < numSplitFile; i++){
            String fileName = String.format("%d.tmp",i+1);
            File file = new File(fileName);
            if(file.delete()){
                System.out.println("Delete file "+fileName);
            }else{
                System.out.println("Cannot delete file "+fileName);
            }
        }
    }

    public static void createListStream(){
        try {
            listStream = new ArrayList<>();
            for(int i = 0 ; i < numSplitFile; i++){
                String fileName = String.format("%d.tmp",i+1);
                BufferedReader br = new BufferedReader(new FileReader(fileName));
                listStream.add(br);
            }
        }catch(Exception e){
            System.out.println("something wrong!!!");
            return;
        }
    }


    public static boolean readAndSplitFile(String inputFile){
        File file = new File(inputFile);
        String []data = new String[LIMIT_STRING];
        int sizeData = 0;


        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                if(sizeData < LIMIT_STRING){
                    data[sizeData] = line;
                    sizeData++;
                }else{
                    ArrayList<String> arr = new ArrayList<>(Arrays.asList(data));
                    Tree myTree = new Tree(arr.get(0));
                    arr.remove(0);
                    myTree.insertArray(arr);
                    ArrayList<String> visited = new ArrayList<>();
                    ArrayList<String> result = myTree.traverse(visited);
                    data = result.toArray(new String[0]);
                    numSplitFile ++;
                    String fileOutName = String.format("%d.tmp",numSplitFile);
                    BufferedWriter writer  = new BufferedWriter(new FileWriter(fileOutName));
                    for(int i = 0 ; i < sizeData; i++){
                        writer.write(data[i]);
                        writer.write('\n');
                    }
                    writer.close();
                    sizeData = 0;
                }
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}