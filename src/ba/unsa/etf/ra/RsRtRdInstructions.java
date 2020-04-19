package ba.unsa.etf.ra;

public enum RsRtRdInstructions {
    ADD ( "add" ),
    ADDU ( "addu" ),
    AND ( "and" ),
    NOR ( "nor" ),
    OR ( "or" ),
    SLT ( "slt" ),
    SLTU ( "sltu" ),
    SUB ( "sub"),
    SUBU ( "subu" ),
    XOR ( "xor" ),
    SLLV ( "sllv" ),
    SRAV ( "srav"),
    SRLV ( "srlv" );

    private final String description;

    private RsRtRdInstructions(String description) { this.description = description; }

    @Override
    public String toString() { return description; }
}
