package ba.unsa.etf.ra;

public enum BranchRsRtInstructions {
    BEQ ( "beq" ),
    BNE ( "bne" );

    private final String description;

    private BranchRsRtInstructions(String description) { this.description = description; }

    @Override
    public String toString() { return description; }
}
