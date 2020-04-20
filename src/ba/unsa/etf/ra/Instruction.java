package ba.unsa.etf.ra;

public class Instruction {
    private String name, source1, source2, destination, label, immediate;
    private String instructionString;
    private boolean  isDelaySlotInstruction = false;

    public Instruction(String name, String source1, String source2, String destination, String label, String immediate) {
        this.name = name;
        this.source1 = source1;
        this.source2 = source2;
        this.destination = destination;
        this.label = label;
        this.immediate = immediate;
    }

    public Instruction() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSource1() {
        return source1;
    }

    public void setSource1(String source1) {
        this.source1 = source1;
    }

    public String getSource2() {
        return source2;
    }

    public void setSource2(String source2) {
        this.source2 = source2;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getImmediate() {
        return immediate;
    }

    public void setImmediate(String immediate) {
        this.immediate = immediate;
    }

    public String getInstructionString() {
        return instructionString;
    }

    public void setInstructionString(String instructionString) {
        this.instructionString = instructionString;
    }

    public boolean isDelaySlotInstruction() {
        return isDelaySlotInstruction;
    }

    public void setToDelaySlotInstruction(boolean delaySlotInstruction) {
        isDelaySlotInstruction = delaySlotInstruction;
    }

    @Override
    public String toString() {
        if (isDelaySlotInstruction) return null;
        return instructionString;
    }
}
