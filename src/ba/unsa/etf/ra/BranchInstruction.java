package ba.unsa.etf.ra;

public class BranchInstruction extends Instruction {
    private String destinationLabel;
    private Instruction delaySlotInstruction;

    public BranchInstruction(String name, String source1, String source2, String destination, String label, String immediate,
                             String destinationLabel, Instruction delaySlotInstruction) {
        super(name, source1, source2, destination, label, immediate);
        this.destinationLabel = destinationLabel;
        this.delaySlotInstruction = delaySlotInstruction;
    }

    public BranchInstruction(String destinationLabel, Instruction delaySlotInstruction) {
        this.destinationLabel = destinationLabel;
        this.delaySlotInstruction = delaySlotInstruction;
    }

    public BranchInstruction() {
        super();
        destinationLabel = "";
        delaySlotInstruction = null;
    }

    public String getDestinationLabel() {
        return destinationLabel;
    }

    public void setDestinationLabel(String destinationLabel) {
        this.destinationLabel = destinationLabel;
    }

    public Instruction getDelaySlotInstruction() {
        return delaySlotInstruction;
    }

    public void setDelaySlotInstruction(Instruction delaySlotInstruction) {
        this.delaySlotInstruction = delaySlotInstruction;
    }

    @Override
    public String toString() {
        if (delaySlotInstruction != null)
            return super.toString() + "\n" + delaySlotInstruction.getInstructionString() + " #zadrska";
        return super.toString();
    }
}
