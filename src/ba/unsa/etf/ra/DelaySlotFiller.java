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
                    fillerInstruction = findInstructionOnBranchDestination();
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

    private Instruction findInstructionOnBranchDestination() {
        return null;
    }

    private Instruction findInstructionBeforeBranch(Instruction branchInstruction) {
        for (int i = 0; i < instructions.indexOf(branchInstruction); i++) {
            String iDestinaton = instructions.get(i).getDestination();
            if (!iDestinaton.equals(branchInstruction.getSource1()) && !iDestinaton.equals(branchInstruction.getSource2())) {
                instructions.get(i).setToDelaySlotInstruction(true);
                return instructions.get(i);
            }
        }
        return null;
    }
}
