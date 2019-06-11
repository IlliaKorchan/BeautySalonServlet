package model.entities;

public class ClientProcedureDto {
    private Procedure procedure;
    private String name;

    public ClientProcedureDto(Procedure procedure) {
        this.procedure = procedure;
        this.name = name;
    }

    public Procedure getProcedure() {
        return procedure;
    }

    public void setProcedure(Procedure procedure) {
        this.procedure = procedure;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
