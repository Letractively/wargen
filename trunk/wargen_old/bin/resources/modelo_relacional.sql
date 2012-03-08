DROP TABLE IF EXISTS wargen.TAB_OBJETO;
DROP TABLE IF EXISTS wargen.TAB_MARCADOR;
DROP TABLE IF EXISTS wargen.TAB_MODELO;
DROP TABLE IF EXISTS wargen.TAB_ARQUIVO;
DROP TABLE IF EXISTS wargen.TAB_USUARIO;

CREATE TABLE wargen.TAB_USUARIO (
       id INT(7) NOT NULL AUTO_INCREMENT
     , nome VARCHAR(200)
     , login VARCHAR(20) NOT NULL
     , senha VARCHAR(35) NOT NULL
     , PRIMARY KEY (id)
);

CREATE TABLE wargen.TAB_ARQUIVO (
       id INT(7) NOT NULL AUTO_INCREMENT
     , nome_arquivo VARCHAR(120) NOT NULL
     , extensao_arquivo VARCHAR(6) NOT NULL
     , nome_apresentacao VARCHAR(120) NOT NULL
     , PRIMARY KEY (id)
);

CREATE TABLE wargen.TAB_MODELO (
       id INT(7) NOT NULL AUTO_INCREMENT
     , usuario INT(7) NOT NULL
     , modelo INT(7) NOT NULL
     , imagem INT(7)
     , PRIMARY KEY (id)
     , INDEX (modelo)
     , CONSTRAINT FK_TAB_MODELO_1 FOREIGN KEY (modelo)
                  REFERENCES wargen.TAB_ARQUIVO (id)
     , INDEX (imagem)
     , CONSTRAINT FK_TAB_MODELO_2 FOREIGN KEY (imagem)
                  REFERENCES wargen.TAB_ARQUIVO (id)
     , INDEX (usuario)
     , CONSTRAINT FK_TAB_MODELO_3 FOREIGN KEY (usuario)
                  REFERENCES wargen.TAB_USUARIO (id)
);

CREATE TABLE wargen.TAB_MARCADOR (
       id SMALLINT(3) NOT NULL AUTO_INCREMENT
     , usuario INT(7) NOT NULL
     , marcador INT(7) NOT NULL
     , arquivo_imprimir INT(7)
     , PRIMARY KEY (id)
     , INDEX (marcador)
     , CONSTRAINT FK_TAB_MARCADOR_1 FOREIGN KEY (marcador)
                  REFERENCES wargen.TAB_ARQUIVO (id)
     , INDEX (arquivo_imprimir)
     , CONSTRAINT FK_TAB_MARCADOR_2 FOREIGN KEY (arquivo_imprimir)
                  REFERENCES wargen.TAB_ARQUIVO (id)
     , INDEX (usuario)
     , CONSTRAINT FK_TAB_MARCADOR_3 FOREIGN KEY (usuario)
                  REFERENCES wargen.TAB_USUARIO (id)
);

CREATE TABLE wargen.TAB_OBJETO (
       id INT(10) NOT NULL AUTO_INCREMENT
     , usuario INT(7) NOT NULL
     , modelo INT(7) NOT NULL
     , marcador SMALLINT(3) NOT NULL
     , publico BOOLEAN NOT NULL
     , descricao VARCHAR(120) NOT NULL
     , PRIMARY KEY (id)
     , INDEX (modelo)
     , CONSTRAINT FK_TAB_OBJETO_1 FOREIGN KEY (modelo)
                  REFERENCES wargen.TAB_MODELO (id)
     , INDEX (marcador)
     , CONSTRAINT FK_TAB_OBJETO_2 FOREIGN KEY (marcador)
                  REFERENCES wargen.TAB_MARCADOR (id)
);

