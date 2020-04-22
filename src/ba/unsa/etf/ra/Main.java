package ba.unsa.etf.ra;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("Pozdrav\nPrije nego što navedete putanju do datoteke koja sadrži sekvencu instrukcija\n" +
                "važna napomena: Format instrukcija mora biti takav da je između svake riječi barem jedan razmak, te " +
                "da su operandi pored razmaka razdvojeni i zarezima!" +
                "Takođe je bitno da su instrukcije u MIPS formatu npr. add $s0, $s1, $s2");

        System.out.println("Unesite putanju do datoteke sa sekvencom instrukcija:");

        Scanner scanner = new Scanner(System.in);

        String path = scanner.nextLine();

        InstructionParser instructionParser = new InstructionParser(path);
        try {
            instructionParser.parseFile();
            ArrayList<Instruction> instructions = (ArrayList<Instruction>) instructionParser.getInstructions();
            DelaySlotFiller filler = new DelaySlotFiller();
            instructions = filler.fillDelaySlots(instructions);
            for (Instruction i : instructions) {
                if (i.toString() == null) continue;
                System.out.println(i);
                //System.out.println("labela: " + i.getLabel());
            }
        } catch (InvalidInstructionFileFormat invalidInstructionFileFormat) {
            System.out.println(invalidInstructionFileFormat.getMessage());
        } catch (ZeroBranchInstructionsException e) {
            System.out.println(e.getMessage());
        }
    }
}
