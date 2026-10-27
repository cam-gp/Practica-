package Polizas.General;

import java.util.List;

public interface DAO {

    int create(Object obj);
    List read();
    int update(Object obj);
    int delete(Object obj);
}