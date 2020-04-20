package ba.unsa.etf.ra;

public enum MemoryInstructions {
    LB ( "lb" ),
    LBU ( "lbu" ),
    LH ( "lh" ),
    LW ( "lw" ),
    SB ( "sb" ),
    SH ( "sh" ),
    SW ( "sw" );

    private final String description;

    private MemoryInstructions(String description) { this.description = description; }

    @Override
    public String toString() { return description; }
}
