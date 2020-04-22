package ba.unsa.etf.ra;

public class InstructionType {

    public enum BranchRsRtInstructions {
        BEQ ( "beq" ),
        BNE ( "bne" );

        private final String description;

        private BranchRsRtInstructions(String description) { this.description = description; }

        @Override
        public String toString() { return description; }
    }

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
}
