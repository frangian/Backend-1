package dao;

public interface iDAO<T> {
    T registrar (T t);
    void eliminar (int id);
    T buscar (int id);
}
