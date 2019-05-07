CREATE DATABASE IF NOT EXISTS control_estoque;

USE control_estoque;

CREATE TABLE IF NOT EXISTS produto(
	id BIGINT AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    preco DOUBLE,
    CONSTRAINT PK_produto PRIMARY KEY(id)
); 

CREATE TABLE IF NOT EXISTS cliente(
	id BIGINT AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    CONSTRAINT PK_cliente PRIMARY KEY(id)
);

CREATE TABLE IF NOT EXISTS venda(
	id BIGINT AUTO_INCREMENT,
    data DATE NOT NULL,
    precoTotal DOUBLE,
    idCliente BIGINT,
    CONSTRAINT PK_venda PRIMARY KEY(id),
    CONSTRAINT FK_venda FOREIGN KEY(idCliente) REFERENCES cliente(id)
);

CREATE TABLE IF NOT EXISTS itemVenda(
	idVenda BIGINT,
    idProduto BIGINT,
    quantidade INT,
    CONSTRAINT PK_itemVenda PRIMARY KEY(idVenda, idProduto),
	CONSTRAINT FK_itemVenda1 FOREIGN KEY(idVenda) REFERENCES venda(id),
    CONSTRAINT FK_itemVenda2 FOREIGN KEY(idProduto) REFERENCES produto(id)
);

