package com.skillseekr.Services;

import java.sql.SQLException;
import java.util.List;

public interface IServices<T>{
    void add(T t) throws SQLException;
    void update(T t) throws SQLException;
    void delete(T t) throws SQLException;
    List<T> show() throws SQLException;
    void signup(T t) throws SQLException;

}
