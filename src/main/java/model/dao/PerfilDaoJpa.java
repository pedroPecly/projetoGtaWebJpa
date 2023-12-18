package model.dao;

import java.util.List;

import javax.persistence.EntityManager;

import model.Perfil;

public class PerfilDaoJpa implements InterfaceDao<Perfil> {

    @Override
    public void incluir(Perfil entidade) throws Exception {
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
    public void editar(Perfil entidade) throws Exception {
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
    public void excluir(Perfil entidade) throws Exception {
        EntityManager em = ConnFactory.getEntityManager();
        try {
            em.getTransaction().begin();
            em.remove(em.find(Perfil.class, entidade.getId()));
            em.getTransaction();
        } finally {
            em.close();
        }

    }

    @Override
    public Perfil pesquisarPorId(int id) throws Exception {
        Perfil c = null;
        EntityManager em = ConnFactory.getEntityManager();
        try{
            em.getTransaction().begin();
            c = em.find(Perfil.class, id);
            em.getTransaction().commit();
        } finally {
            em.close();
        }

        return c;
    }

    @Override
    public List<Perfil> listar() throws Exception {
        List<Perfil> lista = null;
        EntityManager em = ConnFactory.getEntityManager();
        try {
            em.getTransaction().begin();
            lista = em.createQuery("FROM Perfil p").getResultList();
            em.getTransaction().commit();
        } finally {
            em.close();
        }

        return lista;
    }

}
