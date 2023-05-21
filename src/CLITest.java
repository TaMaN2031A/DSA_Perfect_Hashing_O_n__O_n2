import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.Assert.assertEquals;


public class CLITest {
    CLI cli =new CLI();
    Dictionary dictionary = null;
    @Test
    public void test1_N2_insert_search_exist() throws IOException, InterruptedException {
        dictionary = new Dictionary("N2",5000);
        String path ="C:\\Users\\LeNoVo\\Desktop\\gh\\src\\test1.txt";
        dictionary.BatchInsert(path);
        assertEquals(dictionary.search("FBCBAC"), true);
    }
    @Test
    public void test1_N2_insert_search_not_exist() throws IOException, InterruptedException {
        dictionary = new Dictionary("N2",5000);
        String path ="C:\\Users\\LeNoVo\\Desktop\\gh\\src\\test1.txt";
        dictionary.BatchInsert(path);
        assertEquals(dictionary.search("Ana M4 Hena"), false);
    }
    @Test
    public void test2_N2_insert_found_delete_not_found() throws IOException, InterruptedException {
        //file above 1000 words with 22 unique, search for exisiting then delete it, then research
        dictionary = new Dictionary("N2",5000);
        String path ="C:\\Users\\LeNoVo\\Desktop\\gh\\src\\test2.txt";
        dictionary.BatchInsert(path);
        assertEquals(dictionary.search("FFFFFF"), true);
        dictionary.delete("FFFFFF");
        assertEquals(dictionary.search("FFFFFF"), false);
    }
    @Test
    public void test3_N2_insert_search_not_found_insert_found() throws IOException, InterruptedException {

        dictionary = new Dictionary("N2",5000);
        String path ="C:\\Users\\LeNoVo\\Desktop\\gh\\src\\test3.txt";
        assertEquals(dictionary.search("sTahnqJcskUMiOiWSsTH"), false);
        dictionary.BatchInsert(path);
        assertEquals(dictionary.search("sTahnqJcskUMiOiWSsTH"), true);
    }
    @Test
    public void test5_N2_insert_emptyfile_not_found_insert_found() throws IOException, InterruptedException {
        //empty file
        dictionary = new Dictionary("N2",5000);
        String path ="C:\\Users\\LeNoVo\\Desktop\\gh\\src\\test6.txt";
        dictionary.BatchInsert(path);
        assertEquals(dictionary.search("Any thing"), false);
        dictionary.insert("Any thing");
        assertEquals(dictionary.search("Any thing"), true);
    }
    @Test
    public void test7_N2_deleteall() throws IOException, InterruptedException {
        //delete all
        dictionary = new Dictionary("N2",5000);
        String path ="C:\\Users\\LeNoVo\\Desktop\\gh\\src\\test3.txt";
        dictionary.BatchInsert(path);
        assertEquals(dictionary.search("NZqVpZTFgFkGRoEEXdhM"), true);
        dictionary.BatchDelete(path);
        assertEquals(dictionary.search("NZqVpZTFgFkGRoEEXdhM"), false);
    }
    @Test
    public void test8_N2_add_found_deletenonexisting_nonfound() throws IOException, InterruptedException {
        //delete some elm from many elm
        //add add delete
        dictionary = new Dictionary("N2",6000);
        String path1 ="C:\\Users\\LeNoVo\\Desktop\\gh\\src\\test1.txt";
        String path3="C:\\Users\\LeNoVo\\Desktop\\gh\\src\\test3.txt";
        dictionary.BatchInsert(path1);
        assertEquals(dictionary.search("FBCBAC"), true);
        dictionary.BatchInsert(path3);
        assertEquals(dictionary.search("FBCBAC"), true);
        dictionary.BatchDelete(path1);
        assertEquals(dictionary.search("FBCBAC"), false);
    }
    @Test
    public void test9_N2_delete() throws IOException, InterruptedException {
        //delete some elm from many elm
        //add  delete add
        dictionary = new Dictionary("N2",6000);
        String path1 ="C:\\Users\\LeNoVo\\Desktop\\gh\\src\\test1.txt";
        String path3="C:\\Users\\LeNoVo\\Desktop\\gh\\src\\test3.txt";
        dictionary.BatchInsert(path3);
        assertEquals(dictionary.search("rDYuTxZZiwyuCcIqXljy"), true);
        assertEquals(dictionary.search("FBCBAC"), false);

        dictionary.BatchDelete(path3);
        assertEquals(dictionary.search("rDYuTxZZiwyuCcIqXljy"), false);
        assertEquals(dictionary.search("FBCBAC"), false);

        dictionary.BatchInsert(path1);
        assertEquals(dictionary.search("FBCBAC"), true);

    }

    @Test
    public void test1_N_insert_search_exist() throws IOException, InterruptedException {
        dictionary = new Dictionary("N2",5000);
        String path ="C:\\Users\\LeNoVo\\Desktop\\gh\\src\\test1.txt";
        dictionary.BatchInsert(path);
        assertEquals(dictionary.search("FBCBAC"), true);
    }
    @Test
    public void test2_N_insert_search_not_exist() throws IOException, InterruptedException {
        dictionary = new Dictionary("N2",5000);
        String path ="C:\\Users\\LeNoVo\\Desktop\\gh\\src\\test1.txt";
        dictionary.BatchInsert(path);
        assertEquals(dictionary.search("Ana M4 Hena"), false);
    }
    @Test
    public void test3_N_insert_found_delete_not_found() throws IOException, InterruptedException {
        //file above 1000 words with 22 unique, search for exisiting then delete it, then research
        dictionary = new Dictionary("N2",5000);
        String path ="C:\\Users\\LeNoVo\\Desktop\\gh\\src\\test2.txt";
        dictionary.BatchInsert(path);
        assertEquals(dictionary.search("FFFFFF"), true);
        dictionary.delete("FFFFFF");
        assertEquals(dictionary.search("FFFFFF"), false);
    }
    @Test
    public void test4_N_insert_search_not_found_insert_found() throws IOException, InterruptedException {

        dictionary = new Dictionary("N2",5000);
        String path ="C:\\Users\\LeNoVo\\Desktop\\gh\\src\\test3.txt";
        assertEquals(dictionary.search("sTahnqJcskUMiOiWSsTH"), false);
        dictionary.BatchInsert(path);
        assertEquals(dictionary.search("sTahnqJcskUMiOiWSsTH"), true);
    }
    @Test
    public void test5_N_insert_emptyfile_not_found_insert_found() throws IOException, InterruptedException {
        //empty file
        dictionary = new Dictionary("N2",5000);
        String path ="C:\\Users\\LeNoVo\\Desktop\\gh\\src\\test6.txt";
        dictionary.BatchInsert(path);
        assertEquals(dictionary.search("Any thing"), false);
        dictionary.insert("Any thing");
        assertEquals(dictionary.search("Any thing"), true);
    }
    @Test
    public void test6_N_deleteall() throws IOException, InterruptedException {
        //delete all
        dictionary = new Dictionary("N2",5000);
        String path ="C:\\Users\\LeNoVo\\Desktop\\gh\\src\\test3.txt";
        dictionary.BatchInsert(path);
        assertEquals(dictionary.search("NZqVpZTFgFkGRoEEXdhM"), true);
        dictionary.BatchDelete(path);
        assertEquals(dictionary.search("NZqVpZTFgFkGRoEEXdhM"), false);
    }
    @Test
    public void test7_N_add_found_deletenonexisting_nonfound() throws IOException, InterruptedException {
        //delete some elm from many elm
        //add add delete
        dictionary = new Dictionary("N2",6000);
        String path1 ="C:\\Users\\LeNoVo\\Desktop\\gh\\src\\test1.txt";
        String path3="C:\\Users\\LeNoVo\\Desktop\\gh\\src\\test3.txt";
        dictionary.BatchInsert(path1);
        assertEquals(dictionary.search("FBCBAC"), true);
        dictionary.BatchInsert(path3);
        assertEquals(dictionary.search("FBCBAC"), true);
        dictionary.BatchDelete(path1);
        assertEquals(dictionary.search("FBCBAC"), false);
    }
    @Test
    public void test8_N_delete() throws IOException, InterruptedException {
        //delete some elm from many elm
        //add  delete add
        dictionary = new Dictionary("N2",6000);
        String path1 ="C:\\Users\\LeNoVo\\Desktop\\gh\\src\\test1.txt";
        String path3="C:\\Users\\LeNoVo\\Desktop\\gh\\src\\test3.txt";
        dictionary.BatchInsert(path3);
        assertEquals(dictionary.search("rDYuTxZZiwyuCcIqXljy"), true);
        assertEquals(dictionary.search("FBCBAC"), false);

        dictionary.BatchDelete(path3);
        assertEquals(dictionary.search("rDYuTxZZiwyuCcIqXljy"), false);
        assertEquals(dictionary.search("FBCBAC"), false);

        dictionary.BatchInsert(path1);
        assertEquals(dictionary.search("FBCBAC"), true);

    }

    @Test
    public void test10_N_search() throws IOException, InterruptedException {
        //delete some elm from many elm
        //search
        dictionary = new Dictionary("N",6000);
        String path3="C:\\Users\\LeNoVo\\Desktop\\gh\\src\\test3.txt";
        dictionary.BatchInsert(path3);
        boolean x =dictionary.search("vohszQMNFhNjaeUuKACT");

        assertEquals("Testing search",x,true);
        x =dictionary.search("sTahnqJcskUMiOiWSsTH");
        assertEquals("Testing search",x,true);
        x =dictionary.search("AAAA");
        assertEquals("Testing search",x,false);
        x =dictionary.search("BBBB");
        assertEquals("Testing search",x,false);
    }
    @Test
    public void test12_N_delete() throws IOException, InterruptedException {
        //delete some elm from many elm
        //add  delete add
        dictionary = new Dictionary("N",6000);
        String path1 ="C:\\Users\\LeNoVo\\Desktop\\gh\\src\\test1.txt";
        String path3="C:\\Users\\LeNoVo\\Desktop\\gh\\src\\test3.txt";
        boolean x =dictionary.search("vohszQMNFhNjaeUuKACT");
        assertEquals("Testing search",x,false);
        dictionary.BatchInsert(path3);
        dictionary.BatchDelete(path3);
        dictionary.BatchInsert(path1);
        x =dictionary.search("sTahnqJcskUMiOiWSsTH");
        assertEquals("Testing search",x,false);
        x =dictionary.search("BDADDF");
        assertEquals("Testing search",x,true);
        x =dictionary.search("CDFBCC");
        assertEquals("Testing search",x,true);
    }
    @Test
    public void test13_N_insert() throws IOException, InterruptedException {
        //insert 1000000
        dictionary = new Dictionary("N",1000000);
        String path ="C:\\Users\\LeNoVo\\Desktop\\gh\\src\\test7.txt";
        dictionary.BatchInsert(path);
        assertEquals(dictionary.search("QmDnZWyuxrrDFsQQHnla"), true);
        dictionary.delete("QmDnZWyuxrrDFsQQHnla");
        assertEquals(dictionary.search("QmDnZWyuxrrDFsQQHnla"), false);
    }
    @Test
    public void test14_N_delete() throws IOException, InterruptedException {
        //delete some elm from many elm
        //add  delete
        dictionary = new Dictionary("N",100000);
        String path ="C:/l.txt";
        dictionary.BatchInsert(path);
        assertEquals(dictionary.search("bXDdDUDj"), true);
        assertEquals(dictionary.search("ETfGamxV"), true);
        assertEquals(dictionary.search("iQPKEPXs"), true);
        dictionary.BatchDelete(path);
        assertEquals(dictionary.search("bXDdDUDj"), false);
        assertEquals(dictionary.search("ETfGamxV"), false);
        assertEquals(dictionary.search("iQPKEPXs"), false);
    }



}