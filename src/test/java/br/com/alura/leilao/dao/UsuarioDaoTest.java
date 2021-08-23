package br.com.alura.leilao.dao;

import br.com.alura.leilao.model.Usuario;
import br.com.alura.leilao.util.JPAUtil;
import br.com.alura.leilao.util.builder.UsuarioBuilder;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import static org.junit.jupiter.api.Assertions.*;

class UsuarioDaoTest {

    private UsuarioDao usuarioDao;
    private EntityManager manager;

    @BeforeEach
    public void beforeEach() {
        this.manager = JPAUtil.getEntityManager();
        this.usuarioDao = new UsuarioDao(manager);
        manager.getTransaction().begin();
    }

    @AfterEach
    public void afterEach() {
        manager.getTransaction().rollback();
    }

    @Test
    void deveriaEncontrarUsuarioCadastrado() {
        Usuario usuario = new UsuarioBuilder()
                .comNome("fulano")
                .comEmail("fulano@email.com")
                .comSenha("1233456789")
                .criar();
        manager.persist(usuario);
        Usuario encontrado = this.usuarioDao.buscarPorUsername(usuario.getNome());
        Assert.assertNotNull(encontrado);
    }

    @Test
    void naoDeveriaEncontrarUsuarioNaoCadastrado() {
        Usuario usuario = new UsuarioBuilder()
                .comNome("Fulano")
                .comEmail("fulano@email.com")
                .comSenha("1233456789")
                .criar();
        manager.persist(usuario);
        Assert.assertThrows(NoResultException.class, () -> this.usuarioDao.buscarPorUsername("beltrano"));
    }

    @Test
    void deveriaRemoverUmUsuario() {
        Usuario usuario = new UsuarioBuilder()
                .comNome("Fulano")
                .comEmail("fulano@email.com")
                .comSenha("1233456789")
                .criar();
        manager.persist(usuario);
        usuarioDao.deletar(usuario);
        Assert.assertThrows(NoResultException.class, () -> this.usuarioDao.buscarPorUsername(usuario.getNome()));
    }

}