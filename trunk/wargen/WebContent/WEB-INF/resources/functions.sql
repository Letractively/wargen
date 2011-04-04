DELIMITER //
CREATE FUNCTION getNumeracaoPatrimonio() RETURNS INT(7) ZEROFILL
BEGIN
	DECLARE numeracaoNova INT(7) ZEROFILL DEFAULT 0;
	DECLARE numeracaoAlvo INT(7) ZEROFILL DEFAULT 0;
	
	WHILE numeracaoNova = numeracaoAlvo DO
	
		SET numeracaoNova = numeracaoNova + 1;
		SELECT numeracao INTO numeracaoAlvo FROM tab_patrimonio WHERE numeracao = numeracaoNova LIMIT 1;
		
	END WHILE;
	
	RETURN numeracaoNova;
END//
DELIMITER ;