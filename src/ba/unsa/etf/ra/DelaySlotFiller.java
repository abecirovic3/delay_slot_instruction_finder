package ba.unsa.etf.ra;

import java.util.ArrayList;

public class DelaySlotFiller {
    ArrayList<Instruction> instructions = new ArrayList<>();

    public ArrayList<Instruction> fillDelaySlots(ArrayList<Instruction> instructions) throws ZeroBranchInstructionsException {
        this.instructions = instructions;
        int branchInstructionsCounter = 0;
        for(Instruction i : instructions) {
            if (i instanceof BranchInstruction) {
                Instruction fillerInstruction = findInstructionBeforeBranch(i);
                if (fillerInstruction == null) {
                    fillerInstruction = findInstructionOnBranchDestination(i);
                }
                if (fillerInstruction == null) {
                    fillerInstruction = findInstructionAfterBranch();
                }
                ((BranchInstruction) i).setDelaySlotInstruction(fillerInstruction);
                branchInstructionsCounter++;
            }
        }
        if (branchInstructionsCounter == 0)
            throw new ZeroBranchInstructionsException("U datoj sekvenci nema instruckcija grananja");

        return instructions;
    }

    private Instruction findInstructionAfterBranch() {
        return null;
    }

    private Instruction findInstructionOnBranchDestination(Instruction branchInstruction) {
        // if the branch destination is before branch...
        Instruction instructionOnBranchDestination = getDestinationInstructionBeforeBranch(branchInstruction);
        boolean found = false;
        if (instructionOnBranchDestination != null) {
            for (int i = instructions.indexOf(branchInstruction); i < instructions.size(); i++) {
                if (instructions.get(i).dependsOn(instructionOnBranchDestination)) return null;
            }
            found = true;
        }

        if (!found) {
            // if the branch destination is after the branch...
            instructionOnBranchDestination = getDestinationInstructionAfterBranch(branchInstruction);
            if (instructionOnBranchDestination != null) {
                for (int i = instructions.indexOf(branchInstruction); i < instructions.indexOf(instructionOnBranchDestination); i++) {
                    if (instructions.get(i).dependsOn(instructionOnBranchDestination)) return null;
                }
            }
        }

        if (instructionOnBranchDestination != null &&!(instructionOnBranchDestination instanceof BranchInstruction)
                && !instructionOnBranchDestination.isDelaySlotInstruction()) {
            instructionOnBranchDestination.setToDelaySlotInstruction(true);
            if (instructions.indexOf(instructionOnBranchDestination) < instructions.size() - 1)
                instructions.get(instructions.indexOf(instructionOnBranchDestination) + 1).setLabel(instructionOnBranchDestination.getLabel());
            instructionOnBranchDestination.setLabel("");

        }
        return instructionOnBranchDestination;
    }

    private Instruction findInstructionBeforeBranch(Instruction branchInstruction) {
        if (getDestinationInstructionAfterBranch(branchInstruction) == null) return null;
        for (int i = 0; i < instructions.indexOf(branchInstruction); i++) {
            if (!branchInstruction.dependsOn(instructions.get(i)) && !(instructions.get(i) instanceof BranchInstruction)
                    && !instructions.get(i).isDelaySlotInstruction()) {
                instructions.get(i).setToDelaySlotInstruction(true);
                return instructions.get(i);
            }
        }
        return null;
    }

    private Instruction getDestinationInstructionAfterBranch(Instruction branchInstruction) {
        for(int i = instructions.indexOf(branchInstruction) + 1; i < instructions.size(); i++) {
            String label = instructions.get(i).getLabel();
            if (label != null && label.equals(((BranchInstruction) branchInstruction).getDestinationLabel()))
                return instructions.get(i);
        }
        return null;
    }

    private Instruction getDestinationInstructionBeforeBranch(Instruction branchInstruction) {
        for (int i = 0; i < instructions.indexOf(branchInstruction); i++) {
            String label = instructions.get(i).getLabel();
            if (label != null && label.equals(((BranchInstruction) branchInstruction).getDestinationLabel()))
                return instructions.get(i);
        }
        return null;
    }
}
