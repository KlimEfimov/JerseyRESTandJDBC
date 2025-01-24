package org.klimefimov.restservice.dao.auxil;

import org.klimefimov.restservice.entities.Category;
import org.klimefimov.restservice.util.DBConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public interface AuxDAO<T> {

    int insert(T t);

    int getMaxId(Statement statement) throws SQLException;

    int deleteByFirstId (int id);
    int deleteBySecondId (int id);

    List<T> getByFirstId(int id);

}
