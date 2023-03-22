package third.task.fileSort;


import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Sorter {

    public File sortFile(File dataFile) throws IOException {

        long max = getTotalNumberOfLines(dataFile) / 10 ;
        int j = 0;

        try (FileInputStream inFile = new FileInputStream(dataFile)) {

            Scanner scanner = new Scanner(inFile);
            int i = 0;
            List<Long> list = new ArrayList<Long>();
            while (scanner.hasNextLong()) {
                while (i < max && scanner.hasNextLong()) {
                    list.add(scanner.nextLong());
                    i++;
                }

                Collections.sort(list);

                FileWriter fw = new FileWriter(new File("data" + j + ".txt"));
                BufferedWriter bf = new BufferedWriter(fw);
                PrintWriter out = new PrintWriter(bf);
                for (Long number : list) {
                    out.println(number);
                }
                out.flush();
                list.clear();
                i = 0;
                j++;
        }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return mergeSortfile(j);
    }

    private static long getTotalNumberOfLines(File file) throws IOException {
        long max = 0;
        try (FileInputStream inFile = new FileInputStream(file)) {
            Scanner scanner = new Scanner(inFile);
            max = scanner.findAll(System.lineSeparator()).count();
        }
        return max;
    }
    private static File mergeSortfile (int end) throws FileNotFoundException {

        File result = null;
        int sortfilenumber = end - 1;
        for (int i = 0; i < end - 1; i++) {
            sortfilenumber++;
            File buffer1 = new File(String.format("data%d.txt", i));
            File buffer2 = new File(String.format("data%d.txt", 1 + i));
            result = mergeSortInnerfile(buffer1, buffer2, new File(String.format("data%d.txt", sortfilenumber)));
            buffer1.delete();
            buffer2.delete();
            i++;
            end++;
        }

        return result;
    }
    private static  File mergeSortInnerfile(File file, File file2, File start) {
        try(FileInputStream inFile = new FileInputStream(file);FileInputStream inFile2 = new FileInputStream(file2);PrintWriter pw = new PrintWriter(start)) {
            Scanner scannerfile1 = new Scanner(inFile);
            Scanner scannerfile2 = new Scanner(inFile2);
            if (scannerfile1.hasNextLong() && scannerfile2.hasNextLong()) {
                Long number1 = scannerfile1.nextLong();
                Long number2 = scannerfile2.nextLong();
                while (scannerfile1.hasNextLong() && scannerfile2.hasNextLong()) {
                    if (number1 < number2) {
                        pw.println(number1);
                        number1 = scannerfile1.nextLong();
                    } else {
                        pw.println(number2);
                        if (scannerfile2.hasNextLong()) {
                            number2 = scannerfile2.nextLong();
                        }
                    }

                }
                if (scannerfile1.hasNextLong()) {
                    pw.println(number1);
                    while (scannerfile1.hasNextLong()) {
                        number1 = scannerfile1.nextLong();
                        pw.println(number1);
                    }
                }
                if (scannerfile2.hasNextLong()) {
                    pw.println(number2);
                    while (scannerfile2.hasNextLong()) {
                        number2 = scannerfile2.nextLong();
                        pw.println(number2);
                    }
                }
            }

            scannerfile1.close();
            scannerfile2.close();
            pw.flush();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return start;
    }
}
