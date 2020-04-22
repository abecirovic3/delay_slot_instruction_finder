package ba.unsa.etf.ra;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;

public class InstructionWriter {
    public void writeInstructionsToFile(ArrayList<Instruction> instructions, String filePath) {
        try {
            //PrintStream fileStream = new PrintStream(new File(filePath));
            //fileStream.println("your data");
            FileWriter fileWriter = new FileWriter(filePath);
            for (Instruction i : instructions) {
                if (i.toString() == null) continue;
                fileWriter.write(i.toString() + System.getProperty("line.separator"));
            }
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Problemi s pisanjem datoteke");
        }
    }
}
