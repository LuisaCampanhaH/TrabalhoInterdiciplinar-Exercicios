package dao;

import model.Produto;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {

    private Connection connection;

    public ProdutoDAO() {
        this.connection = new ConnectionFactory().getConnection();
    }

    // --- C - CREATE ---
    public void inserir(Produto produto) {
        String sql = "INSERT INTO produtos (nome, preco) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, produto.getNome());
            stmt.setBigDecimal(2, produto.getPreco());
            stmt.execute();
            System.out.println("Produto inserido com sucesso!");
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir produto", e);
        }
    }

    // --- R - READ ---
    public List<Produto> listar() {
        List<Produto> produtos = new ArrayList<>();
        String sql = "SELECT * FROM produtos ORDER BY id";
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Produto produto = new Produto();
                produto.setId(rs.getInt("id"));
                produto.setNome(rs.getString("nome"));
                produto.setPreco(rs.getBigDecimal("preco"));
                produtos.add(produto);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar produtos", e);
        }
        return produtos;
    }

    // --- U - UPDATE ---
    public void atualizar(Produto produto) {
        String sql = "UPDATE produtos SET nome = ?, preco = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, produto.getNome());
            stmt.setBigDecimal(2, produto.getPreco());
            stmt.setInt(3, produto.getId()); // O ID é usado para identificar o registro a ser atualizado
            int affectedRows = stmt.executeUpdate();

            if (affectedRows > 0) {
                System.out.println("Produto atualizado com sucesso!");
            } else {
                System.out.println("Nenhum produto encontrado com o ID fornecido.");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar produto", e);
        }
    }

    // --- D - DELETE ---
    public void excluir(int id) {
        String sql = "DELETE FROM produtos WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int affectedRows = stmt.executeUpdate();

            if (affectedRows > 0) {
                System.out.println("Produto excluído com sucesso!");
            } else {
                System.out.println("Nenhum produto encontrado com o ID fornecido para exclusão.");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir produto", e);
        }
    }
}