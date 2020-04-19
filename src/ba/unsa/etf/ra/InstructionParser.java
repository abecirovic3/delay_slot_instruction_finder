package ba.unsa.etf.ra;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InstructionParser {

    private String filePath;
    private List instructions = new ArrayList<Instruction>();

    public InstructionParser(String filePath) {
        this.filePath = filePath;
    }

    public List getInstructions() {
        return instructions;
    }

    public void setInstructions(List instructions) {
        this.instructions = instructions;
    }

    public void parseFile() throws InvalidInstructionFileFormat {
        Scanner fileScanner = null;
        try {
            fileScanner = new Scanner(new FileReader(filePath));
        } catch (FileNotFoundException e) {
            System.out.println("Problemi s otvaranjem datoteke");
            e.printStackTrace();
        }

        String instructionString;

        while(fileScanner.hasNext()) {
            instructionString = fileScanner.nextLine();
            instructionString = instructionString.toLowerCase();
            String[] strArray = instructionString.split(" ");
            Instruction instruction = getInstructionFromName(strArray);
            if (instruction == null) {
                throw new InvalidInstructionFileFormat("Prilikom parsiranja datoteke pronadjena je instrukcija neodgovarajuceg formata");
            }
            instructions.add(instruction);
        }
        fileScanner.close();
    }

    private Instruction getInstructionFromName(String[] instructionParts) {
        for (RsRtRdInstructions instructionName : RsRtRdInstructions.values()) {
            if (instructionName.toString().equals(instructionParts[0]))
                return getRsRtInstruction(instructionParts);
        }

        for (MemoryInstructions instructionName : MemoryInstructions.values()) {
            if (instructionName.toString().equals(instructionParts[0]))
                return getMemoryInstruction(instructionParts);
        }

        for (JumpInstructions instructionName : JumpInstructions.values()) {
            if (instructionName.toString().equals(instructionParts[0]))
                return getJumpInstruction(instructionParts);
        }

        for (ImmInstructions instructionName : ImmInstructions.values()) {
            if (instructionName.toString().equals(instructionParts[0]))
                return getImmInstructions(instructionParts);
        }

        for (BrancRsRtInstructions instructionName : BrancRsRtInstructions.values()) {
            if (instructionName.toString().equals(instructionParts[0]))
                return getBranchRsRtInstructions(instructionParts);
        }

        for (BranchZeroInstructions instructionName : BranchZeroInstructions.values()) {
            if (instructionName.toString().equals(instructionParts[0]))
                return getBranchZeroInstructions(instructionParts);
        }
        return null;
    }

    private Instruction getBranchZeroInstructions(String[] instructionParts) {
        return null;
    }

    private Instruction getBranchRsRtInstructions(String[] instructionParts) {
        return null;
    }

    private Instruction getImmInstructions(String[] instructionParts) {
        return null;
    }

    private Instruction getJumpInstruction(String[] instructionParts) {
        return null;
    }

    private Instruction getMemoryInstruction(String[] instructionParts) {
        return null;
    }

    private Instruction getRsRtInstruction(String[] instructionParts) {
        if (instructionParts.length < 4) return null;
        Instruction instruction = new Instruction();
        instruction.setName(instructionParts[0].replaceAll("\\s+",""));
        instruction.setRd(instructionParts[1].replaceAll("\\s+","").replaceAll(",", ""));
        instruction.setRs(instructionParts[2].replaceAll("\\s+","").replaceAll(",", ""));
        instruction.setRt(instructionParts[3].replaceAll("\\s+","").replaceAll(",", ""));
        return instruction;
    }
}
