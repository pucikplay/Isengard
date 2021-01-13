DROP PROCEDURE IF EXISTS AddToOrder;
DELIMITER //
CREATE PROCEDURE AddToOrder (IN idZamowienia INT,IN idKsiazki INT)
BEGIN
	DECLARE pom INT;
	DECLARE kosztKsiazki FLOAT;
	INSERT INTO produktyzamowien VALUES (idZamowienia,idKsiazki);
	SET pom = (SELECT koszt FROM zamowienia WHERE zamowienia.id=idZamowienia LIMIT 1);
	SET kosztKsiazki = (SELECT cena FROM ksiazki WHERE ksiazki.id = idKsiazki LIMIT 1);
	#SELECT pom + kosztKsiazki;
	UPDATE zamowienia SET koszt = pom+kosztKsiazki WHERE zamowienia.id=idZamowienia;
END //
DELIMITER ;