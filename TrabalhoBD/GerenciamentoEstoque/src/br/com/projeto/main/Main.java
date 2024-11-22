package br.com.projeto.main;

import br.com.projeto.controller.CategoriaController;
import br.com.projeto.controller.MovimentacaoController;
import br.com.projeto.controller.ProdutoController;
import br.com.projeto.controller.RelatorioController;
import br.com.projeto.dao.CategoriaDAO;
import br.com.projeto.dao.MovimentacaoDAO;
import br.com.projeto.dao.ProdutoDAO;
import br.com.projeto.dao.RelatorioDAO;
import br.com.projeto.model.Categoria;
import br.com.projeto.model.Produto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Conexão com o banco de dados
        String url = "jdbc:mysql://localhost:3306/gerenciamentodeestoque";
        String user = "root";
        String password = "admin";
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            // Instanciando DAOs e Controllers
            CategoriaDAO categoriaDAO = new CategoriaDAO(connection);
            CategoriaController categoriaController = new CategoriaController(categoriaDAO);

            ProdutoDAO produtoDAO = new ProdutoDAO();
            ProdutoController produtoController = new ProdutoController();

            MovimentacaoDAO movimentacaoDAO = new MovimentacaoDAO(connection);
            MovimentacaoController movimentacaoController = new MovimentacaoController(movimentacaoDAO);

            RelatorioDAO relatorioDAO = new RelatorioDAO(connection);
            RelatorioController relatorioController = new RelatorioController(relatorioDAO);

            // Menu
            while (true) {
                System.out.println("=== Menu ===");
                System.out.println("1. Gerenciar Categorias");
                System.out.println("2. Gerenciar Produtos");
                System.out.println("3. Registrar Movimentação");
                System.out.println("4. Gerar Relatórios");
                System.out.println("0. Sair");
                System.out.print("Escolha uma opção: ");
                int opcao = scanner.nextInt();
                scanner.nextLine(); // Limpar buffer

                switch (opcao) {
                    case 1:
                        // Gerenciar Categorias
                        System.out.println("=== Gerenciar Categorias ===");
                        System.out.println("1. Cadastrar Categoria");
                        System.out.println("2. Listar Categorias");
                        System.out.println("3. Editar Categoria");
                        System.out.println("4. Excluir Categoria");
                        System.out.print("Escolha uma opção: ");
                        int opcaoCategoria = scanner.nextInt();
                        scanner.nextLine();

                        switch (opcaoCategoria) {
                            case 1:
                                System.out.print("Nome da Categoria: ");
                                String nomeCategoria = scanner.nextLine();
                                System.out.print("Descrição da Categoria: ");
                                String descricaoCategoria = scanner.nextLine();
                                try {
                                    categoriaController.cadastrarCategoria(nomeCategoria, descricaoCategoria);
                                    System.out.println("Categoria cadastrada com sucesso!");
                                } catch (SQLException e) {
                                    System.out.println("Erro ao cadastrar categoria: " + e.getMessage());
                                }
                                break;
                            case 2:
                                try {
                                    List<Categoria> categorias = categoriaController.listarCategorias();
                                    if (categorias.isEmpty()) {
                                        System.out.println("Nenhuma categoria cadastrada.");
                                    } else {
                                        for (Categoria categoria : categorias) {
                                            System.out.println("ID: " + categoria.getIdCategoria() + ", Nome: " + categoria.getNome());
                                        }
                                    }
                                } catch (SQLException e) {
                                    System.out.println("Erro ao listar categorias: " + e.getMessage());
                                }
                                break;
                            case 3:
                                System.out.print("ID da Categoria para Editar: ");
                                int idCategoriaEditar = scanner.nextInt();
                                scanner.nextLine(); // Limpar buffer
                                System.out.print("Novo Nome da Categoria: ");
                                String novoNomeCategoria = scanner.nextLine();
                                System.out.print("Nova Descrição da Categoria: ");
                                String novaDescricaoCategoria = scanner.nextLine();
                                try {
                                    categoriaController.editarCategoria(idCategoriaEditar, novoNomeCategoria, novaDescricaoCategoria);
                                    System.out.println("Categoria editada com sucesso!");
                                } catch (SQLException e) {
                                    System.out.println("Erro ao editar categoria: " + e.getMessage());
                                }
                                break;
                            case 4:
                                System.out.print("ID da Categoria para Excluir: ");
                                int idCategoriaExcluir = scanner.nextInt();
                                try {
                                    categoriaController.excluirCategoria(idCategoriaExcluir);
                                    System.out.println("Categoria excluída com sucesso!");
                                } catch (SQLException e) {
                                    System.out.println("Erro ao excluir categoria: " + e.getMessage());
                                }
                                break;
                            default:
                                System.out.println("Opção inválida.");
                        }
                        break;

                    case 2:
                        // Gerenciar Produtos
                        System.out.println("=== Gerenciar Produtos ===");
                        System.out.println("1. Cadastrar Produto");
                        System.out.println("2. Listar Produtos");
                        System.out.println("3. Editar Produto");
                        System.out.println("4. Excluir Produto");
                        System.out.print("Escolha uma opção: ");
                        int opcaoProduto = scanner.nextInt();
                        scanner.nextLine(); // Limpar buffer

                        switch (opcaoProduto) {
                            case 1:
                                System.out.print("Nome do Produto: ");
                                String nomeProduto = scanner.nextLine();
                                System.out.print("Descrição do Produto: ");
                                String descricaoProduto = scanner.nextLine();
                                System.out.print("Quantidade do Produto: ");
                                int quantidadeProduto = scanner.nextInt();
                                System.out.print("Preço de Compra: ");
                                double precoCompra = scanner.nextDouble();
                                System.out.print("Preço de Venda: ");
                                double precoVenda = scanner.nextDouble();
                                System.out.print("ID da Categoria: ");
                                int idCategoriaProduto = scanner.nextInt();
                                try {
                                    produtoController.cadastrarProduto(nomeProduto, descricaoProduto, quantidadeProduto, precoCompra, precoVenda, idCategoriaProduto);
                                    System.out.println("Produto cadastrado com sucesso!");
                                } catch (SQLException e) {
                                    System.out.println("Erro ao cadastrar produto: " + e.getMessage());
                                }
                                break;
                            case 2:
                                try {
                                    List<Produto> produtos = produtoController.consultarProdutos(null);
                                    if (produtos.isEmpty()) {
                                        System.out.println("Nenhum produto cadastrado.");
                                    } else {
                                        for (Produto produto : produtos) {
                                            System.out.println("ID: " + produto.getId() + ", Nome: " + produto.getNome());
                                        }
                                    }
                                } catch (SQLException e) {
                                    System.out.println("Erro ao listar produtos: " + e.getMessage());
                                }
                                break;
                            case 3:
                                System.out.print("ID do Produto para Editar: ");
                                int idProdutoEditar = scanner.nextInt();
                                scanner.nextLine(); // Limpar buffer
                                System.out.print("Novo Nome do Produto: ");
                                String novoNomeProduto = scanner.nextLine();
                                System.out.print("Nova Descrição do Produto: ");
                                String novaDescricaoProduto = scanner.nextLine();
                                System.out.print("Nova Quantidade do Produto: ");
                                int novaQuantidadeProduto = scanner.nextInt();
                                System.out.print("Novo Preço de Compra: ");
                                double novoPrecoCompra = scanner.nextDouble();
                                System.out.print("Novo Preço de Venda: ");
                                double novoPrecoVenda = scanner.nextDouble();
                                System.out.print("Novo ID da Categoria: ");
                                int novoIdCategoriaProduto = scanner.nextInt();
                                try {
                                    produtoController.editarProduto(idProdutoEditar, novoNomeProduto, novaDescricaoProduto, novaQuantidadeProduto, novoPrecoCompra, novoPrecoVenda, novoIdCategoriaProduto);
                                    System.out.println("Produto editado com sucesso!");
                                } catch (SQLException e) {
                                    System.out.println("Erro ao editar produto: " + e.getMessage());
                                }
                                break;
                            case 4:
                                System.out.print("ID do Produto para Excluir: ");
                                int idProdutoExcluir = scanner.nextInt();
                                try {
                                    produtoController.excluirProduto(idProdutoExcluir);
                                    System.out.println("Produto excluído com sucesso!");
                                } catch (SQLException e) {
                                    System.out.println("Erro ao excluir produto: " + e.getMessage());
                                }
                                break;
                            default:
                                System.out.println("Opção inválida.");
                        }
                        break;

                    case 3:
                        // Registrar movimentação
                        System.out.print("ID do Produto: ");
                        int idProduto = scanner.nextInt();
                        scanner.nextLine(); // Limpar buffer
                        System.out.print("Tipo de Movimentação (entrada/saída): ");
                        String tipoMovimentacao = scanner.nextLine();
                        System.out.print("Quantidade: ");
                        int quantidade = scanner.nextInt();
                        try {
                            movimentacaoController.registrarMovimentacao(idProduto, tipoMovimentacao, quantidade);
                            System.out.println("Movimentação registrada com sucesso!");
                        } catch (SQLException e) {
                            System.out.println("Erro ao registrar movimentação: " + e.getMessage());
                        }
                        break;

                    case 4:
                        // Gerar Relatórios
                        System.out.println("1. Relatório de Baixo Estoque");
                        System.out.println("2. Relatório de Vendas e Lucros");
                        System.out.print("Escolha uma opção: ");
                        int opcaoRelatorio = scanner.nextInt();
                        scanner.nextLine(); // Limpar buffer

                        switch (opcaoRelatorio) {
                            case 1:
                                try {
                                    relatorioController.relatorioBaixoEstoque();
                                } catch (SQLException e) {
                                    System.out.println("Erro ao gerar relatório de baixo estoque: " + e.getMessage());
                                }
                                break;
                            case 2:
                                try {
                                    relatorioController.relatorioVendasLucros();
                                } catch (SQLException e) {
                                    System.out.println("Erro ao gerar relatório de vendas e lucros: " + e.getMessage());
                                }
                                break;
                            default:
                                System.out.println("Opção inválida.");
                        }
                        break;

                    case 0:
                        System.out.println("Saindo...");
                        return;

                    default:
                        System.out.println("Opção inválida.");
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro na conexão com o banco de dados: " + e.getMessage());
        }
    }
}