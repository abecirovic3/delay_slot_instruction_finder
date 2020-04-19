package ba.unsa.etf.ra;

public enum BranchZeroInstructions {
    BGEZ ( "bgez" ),
    BGEZAL ( "bgezal" ),
    BGTZ ( "bgtz" ),
    BLEZ ( "blez" ),
    BLTZ ( "bltz" ),
    BLTZAL ( "bltzal" );

    private final String description;

    private BranchZeroInstructions(String description) { this.description = description; }

    @Override
    public String toString() { return description; }
}
