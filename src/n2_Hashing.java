import java.util.ArrayList;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Random;

import static java.lang.Math.*;
public class n2_Hashing implements Perfect_Hashing_Interface{
    String[] hashTable;
    private long p = 50000007L;   // A prime that's bigger than a, (x-y) where x and y are the two keys
    private final int sizeOfTable;
    long numberofelements=0;
    private long a;
    private long b;

    public n2_Hashing(int size) throws InterruptedException {
        sizeOfTable = (int) pow(size,2);
        System.out.println("size is "+sizeOfTable);
        Random random = new Random();
        a = abs(random.nextLong()%(p-1-1+1)+1);
        b = abs(random.nextLong()%(p-1-0+1));
        System.out.println("a is "+a+" b is "+b);
        hashTable = new String[sizeOfTable];
        Thread.sleep(5000);
    }
    private boolean rehash2(ArrayList<String> myarray,String value){
        System.out.println("i am rehashing "+value);
        Random random = new Random();
        a = abs(random.nextLong()%((p-1)-1+1)+1);
        b = abs(random.nextLong()%((p-1)-0+1));
        System.out.println("a is "+a+" b is "+b);
        int key;
        
        for(int i=0;i<myarray.size();i++){
            key=hashFunction(toKey(myarray.get(i)));
            if(hashTable[key]==null){
                hashTable[key]=myarray.get(i);
            }else{
                return false;
            }
        }
        key=hashFunction(toKey(value));
        if(hashTable[key]!=null){
            return false;
        }else{
            hashTable[key]=value;
        }
        return true;
    }
    private long toKey(String key) {
        long hashVal = 0;
        for(int i = 0; i < key.length(); i++){
            hashVal = (hashVal << 4) + (key.charAt(i));
            long g = hashVal & 0xF0000000L;
            if (g != 0) hashVal ^= g >>> 24;
            hashVal &= ~g;
        }
        return hashVal;
    }
    private int hashFunction(long number) {
        return (int)((a*number+b)%p%sizeOfTable);
    }
    public boolean search(String value){
        int index = hashFunction(toKey(value));
        return Objects.equals(hashTable[index], value);
    }
    public int insert(String value) {
        if (numberofelements == sizeOfTable) {
            int index=hashFunction(toKey(value));
            if(!Objects.equals(hashTable[index],value)){
                return 3;
            }
            return 0;
        }else {
            int key = hashFunction(toKey(value));
            if (hashTable[key] == null) {
                numberofelements++;
                hashTable[key] = value;
                return 1;
            } else {
                if (hashTable[key].equals(value)) {
                    return 0;
                } else {
                    ArrayList<String> currelements=new ArrayList<>();
                    for(int i=0;i<sizeOfTable;i++){
                        if(hashTable[i]!=null){
                            currelements.add(hashTable[i]);
                            hashTable[i]=null;
                        }
                    }
                    boolean check=false;
                    int count=0;
                    while (!check) {
                        check = rehash2(currelements,value);
                        count++;   // number of rehashings
                    }
                    numberofelements++;
                    System.out.println("We Rehashed "+count+" Times");
                    return 2;
                }
            }
        }
    }
    public boolean delete(String value) { // True if deleted, false if not
        int index = hashFunction(toKey(value));
        if(hashTable[index] == null)
        {
            return false;
        }else{
            if(hashTable[index].equals(value)){
                hashTable[index] = null;
                numberofelements--;
                return true;
            }else{
                return false;
            }
        }
    }
    public int getSize() {
        return sizeOfTable;
    }
    public long getElementsOfTable(){
        return numberofelements;

    }





}

