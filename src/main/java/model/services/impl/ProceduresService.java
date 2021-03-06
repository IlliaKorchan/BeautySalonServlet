package model.services.impl;

import model.dao.DaoFactory;
import model.dao.ProcedureDao;
import model.entities.Procedure;
import model.dto.ClientProcedureDto;
import model.services.ProceduresFinder;

import java.util.ArrayList;
import java.util.List;

import static string.containers.ConstantsContainer.PENNY_TO_UAH_DIVIDE;
import static string.containers.StringContainer.LOCALE_UKR;

public class ProceduresService implements ProceduresFinder {
    @Override
    public List<ClientProcedureDto> getAllProcedures(String language) {

        ProcedureDao procedureDao = DaoFactory.getInstance().createProcedureDao();
        List<Procedure> procedures = procedureDao.findAll();
        List<ClientProcedureDto> procedureDtos = new ArrayList<>();
        procedures.forEach(procedure -> { procedure.setPrice(procedure.getPrice() / PENNY_TO_UAH_DIVIDE);
                                          procedureDtos.add(new ClientProcedureDto(procedure));});

        procedureDtos.forEach(procedureDto -> procedureDto.setName(language.equals(LOCALE_UKR)
                                                                ? procedureDto.getProcedure().getNameUkr()
                                                                : procedureDto.getProcedure().getNameEn()));
        procedureDao.close();
        return procedureDtos;
    }
}
