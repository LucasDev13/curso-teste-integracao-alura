package br.com.alura.leilao.dao;

import br.com.alura.leilao.model.Leilao;
import br.com.alura.leilao.model.Usuario;
import br.com.alura.leilao.util.JPAUtil;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.math.BigDecimal;
import java.time.LocalDate;

class LeilaoDaoTest {

    private LeilaoDao leilaoDao;
    private EntityManager manager;

    @BeforeEach
    public void beforeEach() {
        this.manager = JPAUtil.getEntityManager();
        this.leilaoDao = new LeilaoDao(manager);
        manager.getTransaction().begin();
    }

    @AfterEach
    public void afterEach() {
        manager.getTransaction().rollback();
    }

    @Test
    void deveriaCadastrarUmLeilao(){
        Usuario usuario = criarUsuario();
        Leilao leilao = criarLeilao(usuario);

        leilao = leilaoDao.salvar(leilao);
        Leilao salvo = leilaoDao.buscarPorId(leilao.getId());
        Assert.assertNotNull(salvo);

    }

    @Test
    void deveriaAtualizarUmLeilao(){
        Usuario usuario = criarUsuario();
        Leilao leilao = criarLeilao(usuario);

        leilao = leilaoDao.salvar(leilao);

        leilao.setNome("Celular");
        leilao.setValorInicial(new BigDecimal("400"));

        leilao = leilaoDao.salvar(leilao);

        Leilao salvo = leilaoDao.buscarPorId(leilao.getId());
        Assert.assertEquals("Celular", salvo.getNome());
        Assert.assertEquals(new BigDecimal("400"), salvo.getValorInicial());

    }

    private Usuario criarUsuario(){
        Usuario usuario = new Usuario("fulano", "fulano@email.com", "12345678");
        manager.persist(usuario);
        return usuario;
    }

    private Leilao criarLeilao(Usuario usuario){
        Leilao leilao = new Leilao("Mochila", new BigDecimal("70"), LocalDate.now(), usuario);
        return leilao;
    }

}