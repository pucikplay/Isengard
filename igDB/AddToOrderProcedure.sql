DROP PROCEDURE IF EXISTS AddToOrder;
DELIMITER //
CREATE PROCEDURE AddToOrder (IN idUzytkownik INT,IN idZamowienia INT,IN idKsiazki INT)
BEGIN
	DECLARE aktKoszt INT;
	DECLARE aktStanKonta INT;
	DECLARE kosztKsiazki FLOAT;
	INSERT INTO produktyzamowien VALUES (idZamowienia,idKsiazki);
	SET aktKoszt = (SELECT koszt FROM zamowienia WHERE zamowienia.id=idZamowienia LIMIT 1);
	SET kosztKsiazki = (SELECT cena FROM ksiazki WHERE ksiazki.id = idKsiazki LIMIT 1);
	#SELECT pom + kosztKsiazki;
	UPDATE zamowienia SET koszt = aktKoszt+kosztKsiazki WHERE zamowienia.id=idZamowienia;
	#Update stan konta
	UPDATE uzytkownicy SET stanKonta = stanKonta - kosztKsiazki WHERE uzytkownicy.id=idUzytkownik;
END //
DELIMITER ;