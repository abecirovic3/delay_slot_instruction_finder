package ba.unsa.etf.ra;

public enum ImmInstructions {
    ADDI ( "addi" ),
    ADDIU ( "addiu" ),
    ANDI ( "andi" ),
    ORI ( "ori" ),
    SLTI ( "slti" ),
    SLTIU ( "sltiu" ),
    XORI ( "xori" ),
    SLL ( "sll" ),
    SRA ( "sra"),
    SRL ( "srl" );

    private final String description;

    private ImmInstructions(String description) { this.description = description; }

    @Override
    public String toString() { return description; }
}
