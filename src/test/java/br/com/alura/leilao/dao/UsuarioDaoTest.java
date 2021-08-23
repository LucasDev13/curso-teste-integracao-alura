package br.com.alura.leilao.dao;

import br.com.alura.leilao.model.Usuario;
import br.com.alura.leilao.util.JPAUtil;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;

class UsuarioDaoTest {

    private UsuarioDao usuarioDao;

    @Test
    void testeBuscaDeUsuarioPeloUsername(){
        EntityManager manager = JPAUtil.getEntityManager();
        this.usuarioDao = new UsuarioDao(manager);

         Usuario usuario = new Usuario("fulano", "fulano@email.com", "12345678");
         manager.getTransaction().begin();
         manager.persist(usuario);
         manager.getTransaction().commit();

        Usuario encontrado = this.usuarioDao.buscarPorUsername("fulano");
        Assert.assertNotNull(encontrado);
    }

}