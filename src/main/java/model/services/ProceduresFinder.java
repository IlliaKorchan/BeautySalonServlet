package model.services;

import model.entities.ClientProcedureDto;

import java.util.List;

public interface ProceduresFinder {
    List<ClientProcedureDto> getAllProcedures(String language);
}
