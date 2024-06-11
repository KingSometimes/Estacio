SELECT *
FROM PessoasFisicas;

SELECT *
FROM PessoasJuridicas;

SELECT MC.ID AS MovimentoID, P.Nome AS Produto, PJ.Nome AS Fornecedor, MC.Quantidade, MC.PrecoUnitario, MC.Quantidade * MC.PrecoUnitario AS ValorTotal
FROM MovimentosCompra MC
JOIN Produtos P ON MC.ID_Produto = P.ID
JOIN PessoasJuridicas PJ ON MC.ID_PessoaJuridica = PJ.ID;

SELECT MV.ID AS MovimentoID, P.Nome AS Produto, PF.Nome AS Comprador, MV.Quantidade, MV.PrecoUnitario, MV.Quantidade * MV.PrecoUnitario AS ValorTotal
FROM MovimentosVenda MV
JOIN Produtos P ON MV.ID_Produto = P.ID
JOIN PessoasFisicas PF ON MV.ID_PessoaFisica = PF.ID;

SELECT P.Nome AS Produto, SUM(MC.Quantidade * MC.PrecoUnitario) AS TotalEntrada
FROM MovimentosCompra MC
JOIN Produtos P ON MC.ID_Produto = P.ID
GROUP BY P.Nome;

SELECT P.Nome AS Produto, SUM(MV.Quantidade * MV.PrecoUnitario) AS TotalSaida
FROM MovimentosVenda MV
JOIN Produtos P ON MV.ID_Produto = P.ID
GROUP BY P.Nome;

SELECT *
FROM Usuarios U
WHERE NOT EXISTS (
    SELECT 1
    FROM MovimentosCompra MC
    WHERE MC.ID_Usuario = U.ID
);

SELECT U.Nome AS Operador, SUM(MC.Quantidade * MC.PrecoUnitario) AS TotalEntrada
FROM MovimentosCompra MC
JOIN Usuarios U ON MC.ID_Usuario = U.ID
GROUP BY U.Nome;

SELECT U.Nome AS Operador, SUM(MV.Quantidade * MV.PrecoUnitario) AS TotalSaida
FROM MovimentosVenda MV
JOIN Usuarios U ON MV.ID_Usuario = U.ID
GROUP BY U.Nome;

SELECT P.Nome AS Produto, SUM(MV.Quantidade * MV.PrecoUnitario) / SUM(MV.Quantidade) AS ValorMedioVenda
FROM MovimentosVenda MV
JOIN Produtos P ON MV.ID_Produto = P.ID
GROUP BY P.Nome;