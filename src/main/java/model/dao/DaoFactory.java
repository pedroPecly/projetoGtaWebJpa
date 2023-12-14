package model.dao;

public class DaoFactory {
    public static PerfilDaoJpa novoPerfilDaoJpa() throws Exception {
        return new PerfilDaoJpa();
    }
}
