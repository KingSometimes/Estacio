package controller;

import model.Usuarios;
import java.io.Serializable;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Query;

public class UsuariosJpaController implements Serializable {

    private EntityManagerFactory emf = null;

    public UsuariosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public UsuariosJpaController() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public Usuarios findUsuario(String login, String senha) {
        EntityManager em = getEntityManager();
        try {
            Query query = em.createQuery("SELECT u FROM Usuarios u WHERE u.Nome = :login AND u.Senha = :senha");
            query.setParameter("Nome", login);
            query.setParameter("Senha", senha);
            return (Usuarios) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }

}
