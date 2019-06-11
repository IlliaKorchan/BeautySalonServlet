package model.dao;

import model.entities.WorkingDay;

import java.util.List;

public interface WorkingDayDao extends GenericDao<WorkingDay> {
    List<WorkingDay> findByMasterId(Integer id);
}
