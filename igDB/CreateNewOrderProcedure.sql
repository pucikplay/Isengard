DROP PROCEDURE IF EXISTS CreateNewOrder;
DELIMITER //
CREATE PROCEDURE CreateNewOrder (IN idUzytkownika INT,IN podanyAdres VARCHAR(120))
BEGIN
	SELECT AUTO_INCREMENT
	FROM information_schema.TABLES
	WHERE TABLE_SCHEMA = "isengardbookdb"
	AND TABLE_NAME = "zamowienia";
	INSERT INTO zamowienia (id_uzytkownik,stan_zamowienia,adres,koszt)
	VALUES (idUzytkownika,'Zamowione',podanyAdres,0);
END //
DELIMITER ;
CALL CreateNewOrder(2,"Ulica sezamkowa");