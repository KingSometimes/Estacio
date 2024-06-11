INSERT INTO Usuarios (ID, Nome, Senha, Papel)
VALUES
(1, 'op1', 'op1', 'Operador'),
(2, 'op2', 'op2', 'Operador');

INSERT INTO Produtos (ID, Nome, Quantidade, Preco)
VALUES
(1, 'Produto A', 100, 10.99),
(2, 'Produto B', 50, 20.49),
(3, 'Produto C', 200, 5.99);

INSERT INTO Pessoas (ID, Nome, Endereco, Telefone)
VALUES (NEXT VALUE FOR Seq_Pessoa_ID, 'joao', 'rua dos palmeiras', '11992999');

INSERT INTO Pessoas (ID, Nome, Endereco, Telefone)
VALUES (NEXT VALUE FOR Seq_Pessoa_ID, 'JC', 'rua dos altos', '3137121123');

INSERT INTO PessoasFisicas (ID, CPF)
VALUES ('1', '123.456.789-00');

INSERT INTO PessoasJuridicas (ID, CNPJ)
VALUES ('2', '12.345.678/0001-00');

-- Inserir movimentações de compra
INSERT INTO MovimentosCompra (ID, ID_Usuario, ID_Produto, ID_PessoaJuridica, Quantidade, PrecoUnitario)
VALUES
(1, 1, 1, 1, 10, 15.99),  -- Exemplo de compra do produto ID 1 (10 unidades por R$ 15.99)
(2, 2, 2, 2, 5, 25.49);     -- Exemplo de compra do produto ID 2 (5 unidades por R$ 25.49)


-- Inserir movimentações de venda
INSERT INTO MovimentosVenda (ID, ID_Usuario, ID_Produto, ID_PessoaFisica, Quantidade, PrecoUnitario)
VALUES
(1, 1, 1, 1, 3, 19.99),   -- Exemplo de venda do produto ID 1 (3 unidades por R$ 19.99)
(2, 2, 2, 2, 2, 29.99);    -- Exemplo de venda do produto ID 2 (2 unidades por R$ 29.99)

