package ba.unsa.etf.ra;

public class Instruction {
    String name, rs, rt, rd, immediate;

    public Instruction(String name, String rs, String rt, String rd, String immediate) {
        this.name = name;
        this.rs = rs;
        this.rt = rt;
        this.rd = rd;
        this.immediate = immediate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRs() {
        return rs;
    }

    public void setRs(String rs) {
        this.rs = rs;
    }

    public String getRt() {
        return rt;
    }

    public void setRt(String rt) {
        this.rt = rt;
    }

    public String getRd() {
        return rd;
    }

    public void setRd(String rd) {
        this.rd = rd;
    }

    public String getImmediate() {
        return immediate;
    }

    public void setImmediate(String immediate) {
        this.immediate = immediate;
    }
}
