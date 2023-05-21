import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

import static java.lang.Math.abs;
import static java.lang.Math.pow;

public class n_Hashing implements Perfect_Hashing_Interface {
    
private class secondLevel{
        private String[] internalValues;
        private ArrayList<String> beforeHashing= new ArrayList();
        private long no_elements;
        private long a;
        private long b;
        private long p = 5000000021L;
        private long m;
        private String causedRehash;

        private long hashFunction(long number){
            return (this.a*number+this.b)%this.p%this.m;
        }
        long toKey(String s) {
            long hashVal = 0;
            for(int i = 0; i < s.length(); i++){
                hashVal = (hashVal << 4) + (s.charAt(i))+2*abs(a-b)/b;
                long g = hashVal & 0xF0000000L;
                if (g != 0) hashVal ^= g >>> 24;
                hashVal &= ~g;
            }
            return hashVal;
        }
        public int insert(String value) throws InterruptedException {
            this.no_elements++;
            if(no_elements == 1){
                internalValues = new String[((int)(this.no_elements))];
                internalValues[0] = value;
                Random random = new Random();
                a = abs(random.nextInt() + 5);
                b = abs(random.nextInt() + 5);
                m = 1;
                return 1;
            }
            int index = (int)(hashFunction(toKey(value)));
            if(internalValues[index] == null){
                internalValues[index] = value;
                return 1;
            }else{
                if(internalValues[index].equals(value)){
                    this.no_elements--;
                    return 0;
                }else{
                    causedRehash = value;
                    return 2;
                }
            }
        }
        public int[] hash() throws InterruptedException {
        //    System.out.println("Rehash");
            Random random = new Random();
            a = abs(random.nextInt() + 5);
            b = abs(random.nextInt() + 5);
            m = no_elements*no_elements;
            beforeHashing = new ArrayList<>();// on the fly
            for(String iter : internalValues){
                if(iter != null){
                    beforeHashing.add(iter);
                }
            }
            if(causedRehash != null)
                beforeHashing.add(causedRehash);
           // System.out.println(beforeHashing.size());
            //    causedRehash = null;
            String[] buffer = new String[(int)m];
           // internalValues = new String[((int)(this.m))];
//            for(int i = 0; i < this.m; i++){
//                internalValues[i] = null;
//            }
            int[] returned = new int[2];
            for(String iter: beforeHashing){
                long x=toKey(iter);
                int index = (int)(hashFunction(x));
                if(iter.equals("iQPKEPXs")){
                    System.out.println("I'M IN rehash, current key is: "+ index);
                    System.out.println("In hash, Current a b is: " + this.a + " " + this.b);
                }
                if(buffer[index] != null){
                    if(buffer[index].equals(iter)){
                        continue;
                    }
                    else{
                        returned[0] = 0;
                        return returned;
                    }
                }
                buffer[index] = iter;
                returned[1]++;
            }
            returned[0] = 1;
            causedRehash = null;
            internalValues = new String[(int) m];

            return returned;
        }
        public boolean search(String wanted) {
            if(internalValues == null)
                return false;
            System.out.println(wanted);
            long x= toKey(wanted);
            int index = (int)hashFunction(toKey(wanted));
            if(wanted.equals("iQPKEPXs")){
                System.out.println("I'M IN SEARCH, current key is: "+ index);
                System.out.println("In search, Current a b is: " + this.a + " "+ this.b);
            }
            System.out.println("I'm here: " + internalValues[index]);
            return Objects.equals(internalValues[index], wanted);
        }
        public boolean delete(String value) {
            int index = (int)hashFunction(toKey(value));
            if(internalValues[index] == null)
            {
                return false;
            }else{
                if(internalValues[index].equals(value)){
                    internalValues[index] = null;
                    this.no_elements--;
                    return true;
                }else{
                    return false;
                }
            }
        }
}

    private secondLevel[] mainHashing;
    private long p = 5000000021L; // A prime that's bigger than a, (x-y) where x and y are the two keys
    private long m;
    private long a;
    private long b;
    public n_Hashing(int size){
        m = size;
        mainHashing = new secondLevel[size];
        Random random = new Random();
        a = abs(random.nextInt() + 5);
        b = abs(random.nextInt() + 5);
        for(int i = 0; i < m; i++){
            mainHashing[i] = new secondLevel();
        }
        // Print the generated value
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
    private long hashFunction(long number){
        return (a*number+b)%p%m;
    }
    public boolean search(String value){
        int key = (int)hashFunction(toKey(value));
        if(value.equals("iQPKEPXs")){
            System.out.println("searching for me? I'm what you want, key is: "+ key);
        }
        return mainHashing[key].search(value);
    }
    public int insert(String value) throws InterruptedException {
        int key = (int)hashFunction(toKey(value));
        if(value.equals("iQPKEPXs")){
            System.out.println("I'm what you want, key is: "+ key);
        }
        int state = mainHashing[key].insert(value);
        if(state == 0){// Found
            return 0;
        }
        else if(state == 1){ // New added
            return 1;
        }
        else{
            int[] arr;
            while (true){
                arr = mainHashing[key].hash();
                if(arr[0] == 1){
                    break;
                }
            }
            //v = 2;
            return 2;
        }
    }
    public boolean delete(String value) throws IOException{ // True if deleted, false if not
        int key = (int)hashFunction(toKey(value));
        return mainHashing[key].delete(value);
    }
    public int getSize(){
        int c = 0;
        for(int i = 0; i < mainHashing.length; i++){
            c+=mainHashing[i].m;
        }
        return c;
    }
    public long getElementsOfTable() {
        return 1;
    }



    
}