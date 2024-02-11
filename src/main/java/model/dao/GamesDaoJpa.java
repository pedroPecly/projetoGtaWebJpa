package model.dao;

import java.util.List;

import javax.persistence.EntityManager;

import model.Games;
import model.Perfil;

public class GamesDaoJpa implements InterfaceDao<Games> {

    @Override
    public void incluir(Games entidade) throws Exception {
        EntityManager em = ConnFactory.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(entidade);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @Override
    public void editar(Games entidade) throws Exception {
        EntityManager em = ConnFactory.getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(entidade);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @Override
    public void excluir(Games entidade) throws Exception {
        EntityManager em = ConnFactory.getEntityManager();
        try {
            em.getTransaction().begin();
            em.remove(em.find(Games.class, entidade.getId()));
            em.getTransaction().commit();;
        } finally {
            em.close();
        }
    }

    @Override
    public Games pesquisarPorId(int id) throws Exception {
        Games g = null;
        EntityManager em = ConnFactory.getEntityManager();
        try {
            em.getTransaction().begin();
            g = em.find(Games.class, id);
            em.getTransaction().commit();
        } finally {
            em.close();
        }

        return g;
    }

    @Override
    public List<Games> listar() throws Exception {
        List<Games> lista = null;
        EntityManager em = ConnFactory.getEntityManager();
        try {
            em.getTransaction().begin();
            lista = em.createQuery("FROM Games g").getResultList();
            em.getTransaction().commit();
        } finally {
            em.close();
        }

        return lista;
    }

}
