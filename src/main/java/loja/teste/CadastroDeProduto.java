package loja.teste;

import loja.dao.CategoriaDao;
import loja.dao.ProdutoDao;
import loja.modelo.Categoria;
import loja.modelo.Produto;
import loja.util.JPAUtil;

import javax.persistence.EntityManager;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.math.BigDecimal;
import java.util.List;

public class CadastroDeProduto {

    public static void main(String[] args) {

        cadastrarProduto();

        EntityManager em = JPAUtil.getEntityManager();
        ProdutoDao produtoDao = new ProdutoDao(em);

        Produto p = produtoDao.buscarPorId(2l);
        System.out.println("DIFERENTE -> " + p.getNome());

        List<Produto> todos = produtoDao.buscarTodos();
        todos.forEach(p2 -> System.out.println(p2.getNome()));
    }

    private static void cadastrarProduto() {
        Categoria celulares = new Categoria("CELULARES");
        Produto celular = new Produto("Ronaldo", "To com fome pra krl", new BigDecimal("50"),celulares);

        EntityManager em = JPAUtil.getEntityManager();
        ProdutoDao produtoDao = new ProdutoDao(em);
        CategoriaDao categoriaDao = new CategoriaDao(em);

        em.getTransaction().begin();

        categoriaDao.cadastrar(celulares);
        produtoDao.cadastrar(celular);

        em.getTransaction().commit();
        em.close();
    }

}
