package ba.unsa.etf.ra;

public enum MemoryInstructions {
    LB ( "add" ),
    LBU ( "addu" ),
    LH ( "and" ),
    LW ( "nor" ),
    SB ( "or" ),
    SH ( "slt" ),
    SW ( "sltu" );

    private final String description;

    private MemoryInstructions(String description) { this.description = description; }

    @Override
    public String toString() { return description; }
}
