package ba.unsa.etf.ra;

import java.util.ArrayList;
import java.util.List;

public class InstructionParser {
    String filePath;
    List instructions = new ArrayList<Instruction>();

    public InstructionParser(String filePath) {
        this.filePath = filePath;
    }


}
