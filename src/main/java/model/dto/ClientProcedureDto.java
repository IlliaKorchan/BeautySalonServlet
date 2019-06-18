package model.dto;

import model.entities.Procedure;

public class ClientProcedureDto {
    private Procedure procedure;
    private String name;

    public ClientProcedureDto(Procedure procedure) {
        this.procedure = procedure;
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
