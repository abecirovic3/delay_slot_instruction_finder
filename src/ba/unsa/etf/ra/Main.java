package ba.unsa.etf.ra;

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
            List<Instruction> instructions = instructionParser.getInstructions();
            for (Instruction i : instructions) {
                System.out.println(i.getLabel() + " " + i.getName() + " " + i.getSource1() + " " + i.getDestination() + " ");

                System.out.println(i);
            }
        } catch (InvalidInstructionFileFormat invalidInstructionFileFormat) {
            System.out.println(invalidInstructionFileFormat.getMessage());
        }
    }
}
