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

        String row;

        while(fileScanner.hasNext()) {
            row = fileScanner.nextLine();
            String instructionString = row.toLowerCase();
            String[] strArray = instructionString.split(" ");
            Instruction instruction = getInstructionFromName(strArray);
            if (instruction == null) {
                throw new InvalidInstructionFileFormat("Prilikom parsiranja datoteke pronadjena je instrukcija neodgovarajuceg formata");
            }
            instruction.setInstructionString(row);
            instructions.add(instruction);
        }
        fileScanner.close();
    }

    private Instruction getInstructionFromName(String[] instructionParts) {
        for (RsRtRdInstructions instructionName : RsRtRdInstructions.values()) {
            if (instructionName.toString().equals(instructionParts[0]))
                return getRsRtRdInstruction(instructionParts, 0);
            if (instructionName.toString().equals(instructionParts[1]))
                return getRsRtRdInstruction(instructionParts, 1);
        }

        for (MemoryInstructions instructionName : MemoryInstructions.values()) {
            if (instructionName.toString().equals(instructionParts[0]))
                return getMemoryInstruction(instructionParts, 0);
            if (instructionName.toString().equals(instructionParts[1]))
                return getMemoryInstruction(instructionParts, 1);
        }

        for (ImmInstructions instructionName : ImmInstructions.values()) {
            if (instructionName.toString().equals(instructionParts[0]))
                return getImmInstructions(instructionParts, 0);
            if (instructionName.toString().equals(instructionParts[1]))
                return getImmInstructions(instructionParts, 1);
        }

        for (BranchRsRtInstructions instructionName : BranchRsRtInstructions.values()) {
            if (instructionName.toString().equals(instructionParts[0]))
                return getBranchRsRtInstructions(instructionParts, 0);
            if (instructionName.toString().equals(instructionParts[1]))
                return getBranchRsRtInstructions(instructionParts, 1);
        }
        return null;
    }

    private BranchInstruction getBranchRsRtInstructions(String[] instructionParts, int indexOfName) {
        if (instructionParts.length < 4) return null;
        BranchInstruction instruction = new BranchInstruction();
        setLabelAndNameOfInstruction(instruction, instructionParts, indexOfName);
        instruction.setSource1(instructionParts[1 + indexOfName].replaceAll("\\s+","").replaceAll(",", ""));
        instruction.setSource2(instructionParts[2 + indexOfName].replaceAll("\\s+","").replaceAll(",", ""));
        instruction.setDestinationLabel(instructionParts[3 + indexOfName].replaceAll("\\s+","").replaceAll(",", ""));
        return instruction;
    }

    private Instruction getImmInstructions(String[] instructionParts, int indexOfName) {
        if (instructionParts.length < 4) return null;
        Instruction instruction = new Instruction();
        setLabelAndNameOfInstruction(instruction, instructionParts, indexOfName);
        instruction.setDestination(instructionParts[1 + indexOfName].replaceAll("\\s+","").replaceAll(",", ""));
        instruction.setSource1(instructionParts[2 + indexOfName].replaceAll("\\s+","").replaceAll(",", ""));
        instruction.setImmediate(instructionParts[3 + indexOfName].replaceAll("\\s+","").replaceAll(",", ""));
        return instruction;
    }

    private Instruction getMemoryInstruction(String[] instructionParts, int indexOfName) {
        if (instructionParts.length < 3) return null;
        Instruction instruction = new Instruction();
        setLabelAndNameOfInstruction(instruction, instructionParts, indexOfName);
        if (instruction.getName().startsWith("l")) {    // load instructions
            instruction.setDestination(instructionParts[1 + indexOfName].replaceAll("\\s+","").replaceAll(",", ""));
            String[] offsetAndRegister = separateOffsetAndRegister(instructionParts[2 + indexOfName]);
            if (offsetAndRegister.length < 2) return null;
            instruction.setSource1(offsetAndRegister[1].replaceAll("\\s+",""));
            instruction.setImmediate((offsetAndRegister[0].replaceAll("\\s+","")));
        } else {    // store instructions
            instruction.setSource1(instructionParts[1 + indexOfName].replaceAll("\\s+","").replaceAll(",", ""));
            String[] offsetAndRegister = separateOffsetAndRegister(instructionParts[2 + indexOfName]);
            if (offsetAndRegister.length < 2) return null;
            instruction.setSource2(offsetAndRegister[1].replaceAll("\\s+",""));
            instruction.setImmediate(offsetAndRegister[0].replaceAll("\\s+",""));
        }
        return instruction;
    }

    private Instruction getRsRtRdInstruction(String[] instructionParts, int indexOfName) {
        if (instructionParts.length < 4) return null;
        Instruction instruction = new Instruction();
        setLabelAndNameOfInstruction(instruction, instructionParts, indexOfName);
        instruction.setDestination(instructionParts[1 + indexOfName].replaceAll("\\s+","").replaceAll(",", ""));
        instruction.setSource1(instructionParts[2 + indexOfName].replaceAll("\\s+","").replaceAll(",", ""));
        instruction.setSource2(instructionParts[3 + indexOfName].replaceAll("\\s+","").replaceAll(",", ""));
        return instruction;
    }

    private void setLabelAndNameOfInstruction(Instruction instruction, String[] instructionParts, int indexOfName) {
        instruction.setName(instructionParts[indexOfName].replaceAll("\\s+",""));
        if (indexOfName == 1)
            instruction.setLabel(instructionParts[0].replaceAll(":", " ").replaceAll("\\s+",""));
    }

    private String[] separateOffsetAndRegister(String str) {
        str = str.replaceAll("\\(", " ");
        str = str.replaceAll("\\)", " ");
        return str.split(" ");
    }
}
