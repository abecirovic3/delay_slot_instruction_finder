package ba.unsa.etf.ra;

public enum JumpInstructions {
    J ( "j" ),
    JAL ( "jal" );

    private final String description;

    private JumpInstructions(String description) { this.description = description; }

    @Override
    public String toString() { return description; }
}
