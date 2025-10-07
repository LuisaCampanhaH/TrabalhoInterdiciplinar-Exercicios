package app;

import dao.ProdutoDAO;
import model.Produto;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

public class Principal {

    public static void main(String[] args) {
        // Cria uma instância do DAO para podermos usar seus métodos
        ProdutoDAO produtoDAO = new ProdutoDAO();
        // Cria uma instância do Scanner para ler a entrada do usuário no console
        Scanner scanner = new Scanner(System.in);
        int opcao = 0;

        // Loop principal do menu, continua até o usuário digitar 5 para sair
        while (opcao != 5) {
            System.out.println("\n--- MENU DE GERENCIAMENTO DE PRODUTOS ---");
            System.out.println("1. Listar Produtos");
            System.out.println("2. Inserir Novo Produto");
            System.out.println("3. Atualizar Produto Existente");
            System.out.println("4. Excluir Produto");
            System.out.println("5. Sair do Sistema");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine(); // <-- MUITO IMPORTANTE: Consome a nova linha deixada pelo nextInt()

            switch (opcao) {
                case 1:
                    // --- LISTAR PRODUTOS ---
                    System.out.println("\n--- LISTA DE PRODUTOS CADASTRADOS ---");
                    List<Produto> produtos = produtoDAO.listar();
                    if (produtos.isEmpty()) {
                        System.out.println("Nenhum produto cadastrado no momento.");
                    } else {
                        // Imprime cada produto da lista usando o método toString() que criamos
                        produtos.forEach(System.out::println);
                    }
                    break;
                case 2:
                    // --- INSERIR PRODUTO ---
                    System.out.println("\n--- INSERIR NOVO PRODUTO ---");
                    Produto novoProduto = new Produto();
                    System.out.print("Digite o nome do produto: ");
                    novoProduto.setNome(scanner.nextLine());
                    System.out.print("Digite o preço do produto (ex: 19.99): ");
                    novoProduto.setPreco(scanner.nextBigDecimal());
                    produtoDAO.inserir(novoProduto);
                    break;
                case 3:
                    // --- ATUALIZAR PRODUTO ---
                    System.out.println("\n--- ATUALIZAR PRODUTO ---");
                    System.out.print("Digite o ID do produto que deseja atualizar: ");
                    int idAtualizar = scanner.nextInt();
                    scanner.nextLine(); // Consome a nova linha

                    Produto produtoParaAtualizar = new Produto();
                    produtoParaAtualizar.setId(idAtualizar);

                    System.out.print("Digite o novo nome do produto: ");
                    produtoParaAtualizar.setNome(scanner.nextLine());
                    System.out.print("Digite o novo preço do produto: ");
                    produtoParaAtualizar.setPreco(scanner.nextBigDecimal());

                    produtoDAO.atualizar(produtoParaAtualizar);
                    break;
                case 4:
                    // --- EXCLUIR PRODUTO ---
                    System.out.println("\n--- EXCLUIR PRODUTO ---");
                    System.out.print("Digite o ID do produto que deseja excluir: ");
                    int idExcluir = scanner.nextInt();
                    produtoDAO.excluir(idExcluir);
                    break;
                case 5:
                    System.out.println("Encerrando o sistema. Até logo!");
                    break;
                default:
                    System.out.println("Opção inválida! Por favor, tente novamente.");
                    break;
            }
        }
        // Fecha o scanner para liberar os recursos do sistema
        scanner.close();
    }
}