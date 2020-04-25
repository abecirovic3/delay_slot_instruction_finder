package ba.unsa.etf.ra;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InstructionParserTest {

    @Test
    void parseFile() {
        InstructionParser instructionParser = new InstructionParser("sekvenca.txt");
        try {
            instructionParser.parseFile();
            List<Instruction> instructions = instructionParser.getInstructions();
            String result = "";
            for (Instruction i : instructions) {
                if (i.getLabel() != null)
                    result += i.getLabel() + " ";
                result += i.getName() + " ";
                if (i.getDestination() != null)
                    result += i.getDestination() + " ";
                if (i.getSource1() != null)
                    result += i.getSource1() + " ";
                if (i.getSource2() != null)
                    result += i.getSource2() + " ";
                if (i.getImmediate() != null)
                    result += i.getImmediate() + " ";
                if (i instanceof BranchInstruction) {
                    BranchInstruction bi = (BranchInstruction) i;
                    result += bi.getDestinationLabel() + " ";
                }
                result += "\n";
            }
            assertEquals("add s1 s2 s3 \nlabela1: beq s1 s2 labela2 \nlw s1 s6 10 \nori t1 t2 100 \nbne s3 s8 labela1 \nsw t1 t3 0 " +
                    "\nsll a0 a1 4 \n", result);
        } catch (InvalidInstructionFileFormat | FileNotFoundException invalidInstructionFileFormat) {
            System.out.println(invalidInstructionFileFormat.getMessage());
        }
    }
}