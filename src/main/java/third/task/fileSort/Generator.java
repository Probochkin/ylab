package third.task.fileSort;

import java.io.File;
import java.io.FileNotFoundException;

import java.io.PrintWriter;
import java.util.Random;

public class Generator {
    public File generate(String name, int count)  {
        Random random = new Random();
        File file = new File(name);
        try (PrintWriter pw = new PrintWriter(file)) {
            for (int i = 0; i < count; i++) {
                pw.println(random.nextLong());
            }
            pw.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return file;
    }
}
