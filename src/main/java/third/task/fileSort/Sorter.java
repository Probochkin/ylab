package third.task.fileSort;


import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Sorter {

    public File sortFile(File dataFile) throws IOException {
        long countNumberInFiles;
        long coutNumber = getTotalNumberOfLines(dataFile);
        if (coutNumber < 50) {
            countNumberInFiles = coutNumber / 2;
        } else {
            countNumberInFiles = coutNumber / 15;
        }

        int j = 0;

        try (FileInputStream inFile = new FileInputStream(dataFile)) {

            Scanner scanner = new Scanner(inFile);
            int i = 0;
            List<Long> list = new ArrayList<>();
            while (scanner.hasNextLong()) {
                while (i < countNumberInFiles && scanner.hasNextLong()) {
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
        }catch (IOException e) {
            e.printStackTrace();
        }

        return mergeSortfile(j);
    }

    private static long getTotalNumberOfLines(File file) throws IOException {
        long max;
        try (FileInputStream inFile = new FileInputStream(file)) {
            Scanner scanner = new Scanner(inFile);
            max = scanner.findAll(System.lineSeparator()).count();
        }
        return max;
    }
    private static File mergeSortfile (int end) {

        File result = null;
        int sortfilenumber = end - 1;
        for (int i = 0; i < end - 1; i++) {
            sortfilenumber++;
            File buffer1 = new File(String.format("data%d.txt", i));
            File buffer2 = new File(String.format("data%d.txt", 1 + i));
            result = mergeSortInnerfile(buffer1, buffer2, new File(String.format("data%d.txt", sortfilenumber)));
            i++;
            end++;
        }

        return result;
    }
    public static  File mergeSortInnerfile(File file, File file2, File newFile) {
        try(FileInputStream inFile = new FileInputStream(file);FileInputStream inFile2 = new FileInputStream(file2);PrintWriter pw = new PrintWriter(newFile)) {
            Scanner scannerfile1 = new Scanner(inFile);
            Scanner scannerfile2 = new Scanner(inFile2);
            if (scannerfile1.hasNextLong() && scannerfile2.hasNextLong()) {
                Long number1 = scannerfile1.nextLong();
                Long number2 = scannerfile2.nextLong();
                while (scannerfile1.hasNextLong() && scannerfile2.hasNextLong()) {


                    if (number1 < number2) {
                        pw.println(number1);

                        number1 = scannerfile1.nextLong();

                        if (!scannerfile1.hasNextLong()) {
                            if (number1 < number2) {
                                pw.println(number1);
                                pw.println(number2);
                            } else {
                                pw.println(number2);
                                while (scannerfile2.hasNextLong()) {
                                    number2 = scannerfile2.nextLong();
                                    if (number1 < number2) {
                                        pw.println(number1);
                                        pw.println(number2);
                                        break;
                                    } else {
                                        pw.println(number2);
                                        if (!scannerfile2.hasNextLong()) {
                                            pw.println(number1);
                                        }
                                    }
                                }
                            }
                        }
                    } else {
                        pw.println(number2);
                        number2 = scannerfile2.nextLong();
                        if (!scannerfile2.hasNextLong()) {
                            if (number2 < number1) {
                                pw.println(number2);
                                pw.println(number1);
                            } else {
                                pw.println(number1);
                                while (scannerfile1.hasNextLong()) {
                                    number1 = scannerfile1.nextLong();
                                    if (number2 < number1) {
                                        pw.println(number2);
                                        pw.println(number1);
                                        break;
                                    } else {
                                        pw.println(number1);
                                        if (!scannerfile1.hasNextLong()) {
                                            pw.println(number2);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

                while (scannerfile1.hasNextLong()) {
                            pw.println(scannerfile1.nextLong());
                }

                while (scannerfile2.hasNextLong()) {
                    pw.println(scannerfile2.nextLong());
                }

            }

            scannerfile1.close();
            scannerfile2.close();
            pw.flush();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        file.deleteOnExit();
        file2.deleteOnExit();
        return newFile;
    }
}
