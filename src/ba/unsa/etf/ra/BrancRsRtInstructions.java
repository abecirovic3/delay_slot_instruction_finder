package ba.unsa.etf.ra;

public enum BrancRsRtInstructions {
    BEQ ( "beq" ),
    BNE ( "bne" );

    private final String description;

    private BrancRsRtInstructions(String description) { this.description = description; }

    @Override
    public String toString() { return description; }
}
