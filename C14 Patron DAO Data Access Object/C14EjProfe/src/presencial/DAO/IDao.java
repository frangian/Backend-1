package presencial.DAO;

public interface IDao <T> {

    T guardar(T t);
    void buscar(Integer id);

}
