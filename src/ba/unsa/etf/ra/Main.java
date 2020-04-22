package ba.unsa.etf.ra;

import java.util.ArrayList;
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

            System.out.println("Instrukcije zadrške su pronađene, unesite putanju do datoteke u koju želite upisati dobijenu sekvencu:");
            path = scanner.nextLine();

            InstructionWriter instructionWriter = new InstructionWriter();
            instructionWriter.writeInstructionsToFile(instructions, path);

        } catch (InvalidInstructionFileFormat invalidInstructionFileFormat) {
            System.out.println(invalidInstructionFileFormat.getMessage());
        } catch (DelaySlotFillerException e) {
            System.out.println(e.getMessage());
        }
    }
}
