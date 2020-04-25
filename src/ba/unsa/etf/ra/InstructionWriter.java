package ba.unsa.etf.ra;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class InstructionWriter {

    public String writeInstructionsToFile(ArrayList<Instruction> instructions, String fileName) throws IOException {
        File file = new File(System.getProperty("user.home") + System.getProperty("file.separator") + "izgenerisaneSekvence");
        file.mkdir();
        File f = new File(fileName);
        String path = System.getProperty("user.home") + System.getProperty("file.separator") +
                "izgenerisaneSekvence" + System.getProperty("file.separator") + "Izgenerisani_" + f.getName();
        if (!file.canWrite())
            path = System.getProperty("user.dir") +  System.getProperty("file.separator") + "Izgenerisani_" + f.getName();
        try {
            FileWriter fileWriter = new FileWriter(path);
            for (Instruction i : instructions) {
                if (i.toString() == null) continue;
                fileWriter.write(i.toString() + System.getProperty("line.separator"));
            }
            fileWriter.close();
            return path;
        } catch (IOException e) {
            throw new IOException("Problemi s pisanjem datoteke");
        }
    }
}
