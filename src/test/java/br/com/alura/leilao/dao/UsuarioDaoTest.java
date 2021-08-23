package br.com.alura.leilao.dao;

import br.com.alura.leilao.model.Usuario;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;

class UsuarioDaoTest {

    private UsuarioDao usuarioDao;
    private EntityManager manager;

    @Test
    void testeBuscaDeUsuarioPeloUsername(){
        this.usuarioDao = new UsuarioDao(manager);
        Usuario usuario = this.usuarioDao.buscarPorUsername("fulano");
        Assert.assertNotNull(usuario);
    }

}