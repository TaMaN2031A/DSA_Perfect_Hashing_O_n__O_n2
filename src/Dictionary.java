import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Dictionary {
    Perfect_Hashing_Interface hash;
    Factory factory = new Factory();
    long start,end;
    int count;
public Dictionary(String type, int size) throws InterruptedException{
        hash = factory.getPerfectHASHING(type, size);
        count=0;
}
void BatchInsert(String route) throws IOException, InterruptedException {
        System.out.println("i am here ");
        int count=0, fileSize=0;
        start=System.nanoTime();
        Path path = Paths.get(route);
        List<String> lines = Files.readAllLines(path);
        fileSize = lines.size();
     //   count = hash.batchInsert((ArrayList<String>) lines);
        int[] state = new int[4];
        for (String line : lines) {
            state[insert(line)]++;
            if(state[3]!=0)
                break;
        //    System.out.println("no. Elements is now " + hash.getElementsOfTable());
        }
        end=System.nanoTime();
        System.out.println("\nBatch insertion Done Successfully!");
        System.out.println(state[1]+" new keys inserted without hashing!");
        System.out.println(state[0]+" were already their");
        System.out.println(state[2]+" new keys inserted with rehashing their inner table!");
        //System.out.println((fileSize-count)+" keys already exist in the Dictionary!");
        System.out.println("\nTime of insertion is : "+(end-start)+" ns");
        Thread.sleep(8000);

    }
boolean search(String a) {
        boolean res = hash.search(a);
        if(res){
            System.out.println(a+" Found Successfully!");
        }else{
            System.out.println(a+" Doesn't exist !");
        }
        return res
                ;
    }   
int insert(String a) throws IOException, InterruptedException {
        int x = hash.insert(a);
       if(x == 0){
        //    System.out.println(a+" Already found");
       } else if(x == 1){
        count++;
        //    System.out.println(a+ " Added successfully");
       }else if(x==2){
        count++;
        //    System.out.println(a+" Added, but we rehashed the code");
       }else{
        //    System.out.println("Hash Table is Full!");
       }
        return x;
    }

void BatchDelete(String route) throws IOException, InterruptedException {
        start=System.nanoTime();
        Path path = Paths.get(route);
        List<String> lines = Files.readAllLines(path);
        int deleted = 0, notDeleted = 0;
        for (String line : lines) {
            if (delete(line))
                deleted++;
            else
                notDeleted++;
        }
        end=System.nanoTime();
        System.out.println("\nBatch delete Done Successfully!");
        System.out.println(deleted+" Successfully deleted Items!");
        System.out.println(notDeleted+" Doesn't exist");
        System.out.println("\nTime of delete is : "+(end-start)+" ns");
        Thread.sleep(3000);

    }  
boolean delete(String a) throws IOException {
        boolean x = hash.delete(a);
       if(x){
        count--;
        //    System.out.println(a+" Successfully Deleted");
       }else{
        //    System.out.println(a+" Doesn't exist");
       }
        return x;
    }
long getSize(){
           return count;
        // return hash.getElementsOfTable();
    }
void ends() throws IOException {
        System.out.println("\033[0;31mExecution Times have been writen in files!\033[0m");
        System.out.println("\033[0;32m\nThanks for Using Our Dictionary\033[0m");
       // hash.ends();
    }
}
