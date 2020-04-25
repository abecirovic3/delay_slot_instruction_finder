package ba.unsa.etf.ra;

import java.util.ArrayList;

public class DelaySlotFiller {
    ArrayList<Instruction> instructions = new ArrayList<>();

    public ArrayList<Instruction> fillDelaySlots(ArrayList<Instruction> instructions) throws DelaySlotFillerException {
        this.instructions = instructions;
        int branchInstructionsCounter = 0, failedFillings = 0;
        for(Instruction i : instructions) {
            if (i instanceof BranchInstruction) {
                Instruction fillerInstruction = findInstructionBeforeBranch(i);
                if (fillerInstruction == null) {
                    fillerInstruction = findInstructionOnBranchDestination(i);
                }
                if (fillerInstruction == null) {
                    fillerInstruction = findInstructionAfterBranch(i);
                }
                if (fillerInstruction == null) failedFillings++;
                ((BranchInstruction) i).setDelaySlotInstruction(fillerInstruction);
                branchInstructionsCounter++;
            }
        }
        if (branchInstructionsCounter == 0)
            throw new DelaySlotFillerException("U datoj sekvenci nema instruckcija grananja");
        if (failedFillings == branchInstructionsCounter)
            throw new DelaySlotFillerException("Niti za jednu instrukciju grananja nije pronađena instrukcija zadrške");

        return instructions;
    }

    private Instruction findInstructionAfterBranch(Instruction branchInstruction) {
        Instruction instructionOnBranchDestination = getDestinationInstructionBeforeBranch(branchInstruction);
        if (instructionOnBranchDestination != null) {
            for (int i = instructions.indexOf(branchInstruction) + 1; i < instructions.size(); i++) {
                boolean isSuitable = true;
                Instruction candidate = instructions.get(i);
                for (int j = instructions.indexOf(instructionOnBranchDestination); j < instructions.indexOf(branchInstruction); j++) {
                    if (instructions.get(j).dependsOn(candidate)) {
                        isSuitable = false;
                        break;
                    }
                }
                if (isSuitable && !(candidate instanceof BranchInstruction) && !candidate.isDelaySlotInstruction()) {
                    instructions.get(i).setToDelaySlotInstruction(true);
                    fixLabels(instructions.get(i));
                    return instructions.get(i);
                }
            }
        }
        instructionOnBranchDestination = getDestinationInstructionAfterBranch(branchInstruction);
        if (instructionOnBranchDestination != null) {
            for (int i = instructions.indexOf(branchInstruction) + 1; i < instructions.indexOf(instructionOnBranchDestination); i++) {
                boolean isSuitable = true;
                Instruction candidate = instructions.get(i);
                for (int j = instructions.indexOf(instructionOnBranchDestination); j < instructions.size(); j++) {
                    if (instructions.get(j).dependsOn(candidate)) {
                        isSuitable = false;
                        break;
                    }
                }
                if (isSuitable && !(candidate instanceof BranchInstruction) && !candidate.isDelaySlotInstruction()) {
                    instructions.get(i).setToDelaySlotInstruction(true);
                    fixLabels(instructions.get(i));
                    return instructions.get(i);
                }
            }
        }
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
                for (int i = instructions.indexOf(branchInstruction) + 1; i < instructions.indexOf(instructionOnBranchDestination); i++) {
                    if (instructions.get(i).dependsOn(instructionOnBranchDestination)) return null;
                }
            }
        }

        if (instructionOnBranchDestination != null && !(instructionOnBranchDestination instanceof BranchInstruction)
                && !instructionOnBranchDestination.isDelaySlotInstruction()) {
            instructionOnBranchDestination.setToDelaySlotInstruction(true);
            fixLabels(instructionOnBranchDestination);
            return instructionOnBranchDestination;
        }
        return null;
    }

    private Instruction findInstructionBeforeBranch(Instruction branchInstruction) {
        if (getDestinationInstructionAfterBranch(branchInstruction) == null) return null;
        for (int i = 0; i < instructions.indexOf(branchInstruction); i++) {
            Instruction candidate = instructions.get(i);
            boolean isSuitable = true;
            for (int j = i + 1; j <= instructions.indexOf(branchInstruction); j++) {
                if (instructions.get(j).dependsOn(candidate)) {
                    isSuitable = false;
                    break;
                }
            }
            if (isSuitable && !(instructions.get(i) instanceof BranchInstruction) && !instructions.get(i).isDelaySlotInstruction()) {
                candidate.setToDelaySlotInstruction(true);
                fixLabels(candidate);
                return candidate;
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

    private void fixLabels(Instruction delaySlotInstruction) {
        if (delaySlotInstruction.getLabel().equals("")) return;
        int indexOfDelaySlot = instructions.indexOf(delaySlotInstruction);
        if (indexOfDelaySlot < instructions.size() - 1) {
            Instruction nextInsruction = instructions.get(indexOfDelaySlot + 1);
            if (!nextInsruction.getLabel().equals("")) {
                nextInsruction.setInstructionString(nextInsruction.getInstructionString().replace(nextInsruction.getLabel(), nextInsruction.getLabel() + ": " + delaySlotInstruction.getLabel()));
            } else {
                nextInsruction.setLabel(delaySlotInstruction.getLabel());
                nextInsruction.setInstructionString(delaySlotInstruction.getLabel() + ": " + nextInsruction.getInstructionString());
            }
        }
        delaySlotInstruction.setInstructionString(delaySlotInstruction.getInstructionString().replace(delaySlotInstruction.getLabel() + ":", ""));
        delaySlotInstruction.setLabel("");
    }
}
