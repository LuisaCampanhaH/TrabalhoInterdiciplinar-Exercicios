package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    // Método público que retorna um objeto de conexão com o banco de dados
    public Connection getConnection() {
        try {
            // ATENÇÃO!
            // Substitua os valores abaixo pelos dados do seu banco de dados PostgreSQL.
            String url = "jdbc:postgresql://localhost:5432/loga_db"; // Verifique o nome do seu banco
            String usuario = "postgres"; // Usuário padrão do PostgreSQL
            String senha = "yelena0812"; // <-- COLOQUE A SENHA QUE VOCÊ DEFINIU NA INSTALAÇÃO

            return DriverManager.getConnection(url, usuario, senha);

        } catch (SQLException e) {
            // Se ocorrer um erro de SQL (ex: senha errada, banco offline),
            // a aplicação irá parar e mostrar o erro.
            throw new RuntimeException("Erro ao conectar ao banco de dados", e);
        }
    }
}