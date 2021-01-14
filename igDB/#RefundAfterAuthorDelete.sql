DROP PROCEDURE IF EXISTS RefundAfterAuthorDelete;
DELIMITER //

CREATE PROCEDURE RefundAfterAuthorDelete(IN id_ksiazki INT)
BEGIN
	DECLARE koszt INT;
	DECLARE counter INT;
	DECLARE i INT;
	DECLARE id_uzyt INT;
	DECLARE aktStanKonta INT;
	SET i = 1;
	SET koszt = (SELECT cena FROM ksiazki WHERE ksiazki.id=id_ksiazki LIMIT 1);
	SET counter = (SELECT COUNT(produktyzamowien.id_ksiazka) FROM produktyzamowien WHERE produktyzamowien.id_ksiazka=id_ksiazki);
	#Iteracja po wszystkich zamowieniach tej ksiazki i zwracanie kasy
	WHILE i<counter DO 
		SET id_uzyt = 
	  	#SET pom = SELECT uzytkownicy
	  	UPDATE uzytkownicy
	  	SET i = i + 1;
	END WHILE;
END //

DELIMITER ;