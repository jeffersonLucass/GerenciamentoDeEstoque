 -- Criar banco de dados
create database GerenciamentoDeEstoque;
 -- Criar tabela categorias 
CREATE TABLE Categorias (
    id_categoria INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    descricao TEXT
);
 -- Criar tabela produto 
CREATE TABLE Produtos (
    id_produto INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    descricao TEXT,
    quantidade INT NOT NULL,
    preco_compra DECIMAL(10, 2) NOT NULL,
    preco_venda DECIMAL(10, 2) NOT NULL,
    id_categoria INT,
    FOREIGN KEY (id_categoria) REFERENCES Categorias(id_categoria)
        ON DELETE SET NULL ON UPDATE CASCADE
);
-- Tabela de Movimentação de Estoque
CREATE TABLE Movimentacao_Estoque (
    id_movimentacao INT AUTO_INCREMENT PRIMARY KEY,
    id_produto INT NOT NULL,
    tipo_movimentacao ENUM('entrada', 'saida') NOT NULL,
    quantidade INT NOT NULL,
    data_movimentacao DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (id_produto) REFERENCES Produtos(id_produto)
        ON DELETE CASCADE ON UPDATE CASCADE
);
-- Trigger para alertar sobre estoque baixo
DELIMITER //
CREATE TRIGGER alerta_estoque_baixo
AFTER UPDATE ON Produtos
FOR EACH ROW
BEGIN
    IF NEW.quantidade < 10 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Estoque abaixo do mínimo permitido!';
    END IF;
END;
//
DELIMITER ;
-- criar a procedure que cadastra os produtos 
DELIMITER //
CREATE PROCEDURE cadastrarProduto(
    IN nome_produto VARCHAR(255),
    IN descricao_produto TEXT,
    IN quantidade INT,
    IN preco_compra DECIMAL(10, 2),
    IN preco_venda DECIMAL(10, 2),
    IN id_categoria INT
)
BEGIN
    INSERT INTO Produtos (nome, descricao, quantidade, preco_compra, preco_venda, id_categoria)
    VALUES (nome_produto, descricao_produto, quantidade, preco_compra, preco_venda, id_categoria);
END;
//
DELIMITER ;
-- CREATE PROCEDURE cadastrarCategoria 
DELIMITER //
CREATE PROCEDURE cadastrarCategoria(
    IN nome_categoria VARCHAR(255),
    IN descricao_categoria TEXT
)
BEGIN
    INSERT INTO Categorias (nome, descricao)
    VALUES (nome_categoria, descricao_categoria);
END;
//
DELIMITER ;
-- CREATE PROCEDURE consultarProdutos
DELIMITER //
CREATE PROCEDURE consultarProdutos(
    IN filtro_nome VARCHAR(255),
    IN filtro_categoria INT
)
BEGIN
    SELECT p.id_produto, p.nome, p.descricao, p.quantidade, p.preco_compra, p.preco_venda, c.nome AS categoria
    FROM Produtos p
    LEFT JOIN Categorias c ON p.id_categoria = c.id_categoria
    WHERE (filtro_nome IS NULL OR p.nome LIKE CONCAT('%', filtro_nome, '%'))
      AND (filtro_categoria IS NULL OR p.id_categoria = filtro_categoria);
END;
//
DELIMITER ;

-- CRiar  PROCEDURE relatorioProdutos
DELIMITER //
CREATE PROCEDURE relatorioProdutos()
BEGIN
    SELECT p.id_produto, p.nome, p.quantidade, p.preco_compra, p.preco_venda, c.nome AS categoria
    FROM Produtos p
    LEFT JOIN Categorias c ON p.id_categoria = c.id_categoria;
END;
//
DELIMITER ;

--  criado a PROCEDURE de  relatorioMovimentacao
DELIMITER //
CREATE PROCEDURE relatorioMovimentacao()
BEGIN
    SELECT m.id_movimentacao, p.nome AS produto, m.tipo_movimentacao, m.quantidade, m.data_movimentacao
    FROM Movimentacao_Estoque m
    INNER JOIN Produtos p ON m.id_produto = p.id_produto;
END;
//
DELIMITER ;

-- CREATE PROCEDURE relatorioBaixoEstoque

DELIMITER //
CREATE PROCEDURE relatorioBaixoEstoque()
BEGIN
    SELECT id_produto, nome, quantidade
    FROM Produtos
    WHERE quantidade < 10;
END;
//
DELIMITER ;

-- CREATE PROCEDURE relatorioVendasLucros

DELIMITER //
CREATE PROCEDURE relatorioVendasLucros()
BEGIN
    SELECT id_produto, nome, (preco_venda - preco_compra) AS lucro
    FROM Produtos
    WHERE quantidade > 0;
END;
//
DELIMITER ;

-- CREATE PROCEDURE editarProduto
DELIMITER //
CREATE PROCEDURE editarProduto(
    IN id_produto INT,
    IN nome_produto VARCHAR(255),
    IN descricao_produto TEXT,
    IN quantidade INT,
    IN preco_compra DECIMAL(10, 2),
    IN preco_venda DECIMAL(10, 2),
    IN id_categoria INT
)
BEGIN
    UPDATE Produtos
    SET nome = nome_produto,
        descricao = descricao_produto,
        quantidade = quantidade,
        preco_compra = preco_compra,
        preco_venda = preco_venda,
        id_categoria = id_categoria
    WHERE id_produto = id_produto;
END;
//
DELIMITER ;

-- CREATE PROCEDURE editarCategoriaeditarCategoria

DELIMITER //
CREATE PROCEDURE editarCategoria(
    IN id_categoria INT,
    IN nome_categoria VARCHAR(255),
    IN descricao_categoria TEXT
)
BEGIN
    UPDATE Categorias
    SET nome = nome_categoria,
        descricao = descricao_categoria
    WHERE id_categoria = id_categoria;
END;
//
DELIMITER ;

-- CREATE PROCEDURE consultarCategorias
DELIMITER //
CREATE PROCEDURE consultarCategorias(
    IN filtro_nome VARCHAR(255)
)
BEGIN
    SELECT id_categoria, nome, descricao
    FROM Categorias
    WHERE filtro_nome IS NULL OR nome LIKE CONCAT('%', filtro_nome, '%');
END;
//
DELIMITER ;
-- CREATE PROCEDURE excluirProduto
DELIMITER //
CREATE PROCEDURE excluirProduto(IN id_produto INT)
BEGIN
    DELETE FROM Produtos WHERE id_produto = id_produto;
END;
//
DELIMITER ;
-- CREATE PROCEDURE excluirCategoria
DELIMITER //
CREATE PROCEDURE excluirCategoria(IN id_categoria INT)
BEGIN
    DELETE FROM Categorias WHERE id_categoria = id_categoria;
END;
//
DELIMITER ;