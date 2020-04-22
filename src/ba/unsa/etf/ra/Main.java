package ba.unsa.etf.ra;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("Pozdrav\nPrije nego što navedete putanju do datoteke koja sadrži sekvencu instrukcija\n" +
                "važna napomena: Format instrukcija mora biti takav da je između svake riječi barem jedan razmak, te " +
                "da su operandi instrukcije pored razmaka razdvojeni i zarezima!\n" +
                "Takođe je bitno da su sve instrukcije u korektnom formatu npr. add $s0, $s1, $s2 | sub r1, r2, r3");

        System.out.println("Unesite putanju do datoteke sa sekvencom instrukcija:");

        Scanner scanner = new Scanner(System.in);

        String path = scanner.nextLine();

        InstructionParser instructionParser = new InstructionParser(path);
        try {
            instructionParser.parseFile();
            ArrayList<Instruction> instructions = (ArrayList<Instruction>) instructionParser.getInstructions();
            DelaySlotFiller filler = new DelaySlotFiller();
            instructions = filler.fillDelaySlots(instructions);

            //System.out.println("Instrukcije zadrške su pronađene, unesite putanju do datoteke u koju želite upisati dobijenu sekvencu:");
            //path = scanner.nextLine();

            InstructionWriter instructionWriter = new InstructionWriter();
            path = instructionWriter.writeInstructionsToFile(instructions, path);

            System.out.println("Sekvenca je izgenerisana, provjerite sadržaj datoteke " + path);

        } catch (InvalidInstructionFileFormat invalidInstructionFileFormat) {
            System.out.println(invalidInstructionFileFormat.getMessage());
        } catch (DelaySlotFillerException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
