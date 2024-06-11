CREATE TABLE Produtos (
  ID INTEGER    ,
  Nome VARCHAR(MAX)    ,
  Quantidade INTEGER    ,
  Preco DECIMAL     ,
PRIMARY KEY(ID));




CREATE TABLE Usuarios (
  ID INTEGER    ,
  Nome VARCHAR(MAX)    ,
  Senha VARCHAR(MAX)     ,
  Papel VARCHAR(MAX)       ,
PRIMARY KEY(ID));




CREATE TABLE MovimentosCompra (
  ID INTEGER    ,
  Usuarios_ID INTEGER    ,
  Produtos_ID INTEGER   ,
  ID_Usuario INTEGER    ,
  ID_Produto INTEGER     ,
  ID_PessoaJuridica INTEGER     ,
  Quantidade INTEGER     ,
  PrecoUnitario DECIMAL      ,
PRIMARY KEY(ID)    ,
  FOREIGN KEY(Produtos_ID)
    REFERENCES Produtos(ID),
  FOREIGN KEY(Usuarios_ID)
    REFERENCES Usuarios(ID));


CREATE INDEX MovimentosCompra_FKIndex1 ON MovimentosCompra (Produtos_ID);
CREATE INDEX MovimentosCompra_FKIndex2 ON MovimentosCompra (Usuarios_ID);


CREATE INDEX IFK_Rel_06 ON MovimentosCompra (Produtos_ID);
CREATE INDEX IFK_Rel_09 ON MovimentosCompra (Usuarios_ID);


CREATE TABLE MovimentosVenda (
  ID INTEGER   ,
  Usuarios_ID INTEGER    ,
  Produtos_ID INTEGER    ,
  ID_Usuario INTEGER    ,
  ID_Produto INTEGER    ,
  ID_PessoaFisica INTEGER    ,
  Quantidade INTEGER     ,
  PrecoUnitario DECIMAL       ,
PRIMARY KEY(ID)    ,
  FOREIGN KEY(Produtos_ID)
    REFERENCES Produtos(ID),
  FOREIGN KEY(Usuarios_ID)
    REFERENCES Usuarios(ID));


CREATE INDEX MovimentosVenda_FKIndex1 ON MovimentosVenda (Produtos_ID);
CREATE INDEX MovimentosVenda_FKIndex2 ON MovimentosVenda (Usuarios_ID);


CREATE INDEX IFK_Rel_07 ON MovimentosVenda (Produtos_ID);
CREATE INDEX IFK_Rel_08 ON MovimentosVenda (Usuarios_ID);


CREATE TABLE Pessoas (
  ID INTEGER    ,
  Nome VARCHAR(MAX)    ,
  Endereco VARCHAR(MAX)    ,
  Telefone VARCHAR(MAX)     ,
  Usuarios_ID INTEGER    ,
  MovimentosVenda_ID INTEGER   ,
PRIMARY KEY(ID)    ,
  FOREIGN KEY(MovimentosVenda_ID)
    REFERENCES MovimentosVenda(ID),
  FOREIGN KEY(Usuarios_ID)
    REFERENCES Usuarios(ID));


CREATE INDEX Pessoas_FKIndex1 ON Pessoas (MovimentosVenda_ID);
CREATE INDEX Pessoas_FKIndex2 ON Pessoas (Usuarios_ID);


CREATE INDEX IFK_Rel_04 ON Pessoas (MovimentosVenda_ID);
CREATE INDEX IFK_Rel_10 ON Pessoas (Usuarios_ID);


CREATE TABLE PessoasJuridicas (
  ID INTEGER   ,
  Pessoas_ID INTEGER   ,
  CNPJ VARCHAR(MAX)       ,
PRIMARY KEY(ID)  ,
  FOREIGN KEY(Pessoas_ID)
    REFERENCES Pessoas(ID));


CREATE INDEX PessoasJuridicas_FKIndex1 ON PessoasJuridicas (Pessoas_ID);


CREATE INDEX IFK_Rel_02 ON PessoasJuridicas (Pessoas_ID);


CREATE TABLE PessoasFisicas (
   ID INTEGER    ,
  Pessoas_ID INTEGER   ,
  CPF VARCHAR(MAX)      ,
PRIMARY KEY( ID)  ,
  FOREIGN KEY(Pessoas_ID)
    REFERENCES Pessoas(ID));


CREATE INDEX PessoasFisicas_FKIndex1 ON PessoasFisicas (Pessoas_ID);


CREATE INDEX IFK_Rel_03 ON PessoasFisicas (Pessoas_ID);


