DROP TABLE IF EXISTS tab_permissao;
DROP TABLE IF EXISTS tab_patrimonio;
DROP TABLE IF EXISTS tab_usuario;
DROP TABLE IF EXISTS tab_setor;
DROP TABLE IF EXISTS tab_log;
DROP TABLE IF EXISTS tab_permissao_alvo;
DROP TABLE IF EXISTS tab_permissao_tipo;

CREATE TABLE tab_permissao_tipo (
       id SMALLINT(1) NOT NULL
     , tipo VARCHAR(30) NOT NULL
     , PRIMARY KEY (id)
);

CREATE TABLE tab_permissao_alvo (
       id INT(2) NOT NULL AUTO_INCREMENT
     , alvo VARCHAR(80) NOT NULL
     , PRIMARY KEY (id)
);

CREATE TABLE tab_log (
       id INT(9) NOT NULL AUTO_INCREMENT
     , usuario_login VARCHAR(30) NOT NULL
     , usuario_nome CHAR(10) NOT NULL
     , alvo VARCHAR(80) NOT NULL
     , acao VARCHAR(30) NOT NULL
     , data DATETIME NOT NULL
     , PRIMARY KEY (id)
);

CREATE TABLE tab_setor (
       id SMALLINT(1) NOT NULL AUTO_INCREMENT
     , nome VARCHAR(80) NOT NULL
     , PRIMARY KEY (id)
);

CREATE TABLE tab_usuario (
       id SMALLINT(3) NOT NULL AUTO_INCREMENT
     , nome VARCHAR(180) NOT NULL
     , login VARCHAR(30) NOT NULL
     , senha VARCHAR(32) NOT NULL
     , status SMALLINT(1) NOT NULL
     , PRIMARY KEY (id)
);

CREATE TABLE tab_patrimonio (
       id INT(6) NOT NULL AUTO_INCREMENT
     , setor SMALLINT(1) NOT NULL
     , numeracao INT(7) ZEROFILL NOT NULL
     , descricao VARCHAR(180) NOT NULL
     , PRIMARY KEY (id)
     , INDEX (setor)
     , CONSTRAINT FK_PATRIMONIO_SETOR FOREIGN KEY (setor)
                  REFERENCES tab_setor (id)
);

CREATE TABLE tab_permissao (
       id INT(9) NOT NULL AUTO_INCREMENT
     , usuario SMALLINT(3) NOT NULL
     , tipo SMALLINT(1) NOT NULL
     , alvo INT(2) NOT NULL
     , PRIMARY KEY (id)
     , INDEX (usuario)
     , CONSTRAINT FK_PERMISSAO_USUARIO FOREIGN KEY (usuario)
                  REFERENCES tab_usuario (id)
     , INDEX (alvo)
     , CONSTRAINT FK_PERMISSAO_ALVO FOREIGN KEY (alvo)
                  REFERENCES tab_permissao_alvo (id)
     , INDEX (tipo)
     , CONSTRAINT FK_PERMISSAO_TIPO FOREIGN KEY (tipo)
                  REFERENCES tab_permissao_tipo (id)
);

