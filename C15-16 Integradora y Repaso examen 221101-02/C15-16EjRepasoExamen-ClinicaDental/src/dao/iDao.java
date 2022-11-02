package dao;

import java.util.List;

public interface iDao<T> {

    T guardar(T t);
    T buscar(int id);
    void actualizar (T t);
    void eliminar (int id);
    List<T> buscarTodo();

}
