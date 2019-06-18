package model.services;

import model.dto.ClientProcedureDto;

import java.util.List;

public interface ProceduresFinder {
    List<ClientProcedureDto> getAllProcedures(String language);
}
