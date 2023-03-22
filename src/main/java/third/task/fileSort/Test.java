package third.task.fileSort;

import java.io.*;

public class Test {
    public static void main(String[] args) throws IOException {
        File dataFile = new Generator().generate("data.txt", 100);
        Sorter sorter = new Sorter();
         System.out.println(new Validator(dataFile).isSorted()); // false
        dataFile = sorter.sortFile(dataFile);
        System.out.println(new Validator(dataFile).isSorted()); // true


    }
}
