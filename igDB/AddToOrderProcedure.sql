DROP PROCEDURE IF EXISTS AddToOrder;
DELIMITER //
CREATE PROCEDURE AddToOrder (IN idUzytkownik INT,IN idZamowienia INT,IN idKsiazki INT)
BEGIN
	DECLARE kosztKsiazki FLOAT;
	DECLARE iloscSztuk INT;
	
	#Sprawdz czy sa dostepne sztuki
	SET iloscSztuk = (SELECT ksiazki.ilosc FROM ksiazki WHERE ksiazki.id = idKsiazki LIMIT 1);
	
	IF iloscSztuk < 1 THEN
		SELECT 0 AS result;
	END IF;
	
	IF iloscSztuk > 0 THEN
		INSERT INTO produktyzamowien VALUES (idZamowienia,idKsiazki);
		SET kosztKsiazki = (SELECT cena FROM ksiazki WHERE ksiazki.id = idKsiazki LIMIT 1);
		#SELECT pom + kosztKsiazki;
		UPDATE zamowienia SET koszt = koszt+kosztKsiazki WHERE zamowienia.id=idZamowienia;
		#Update stan konta
		UPDATE uzytkownicy SET stanKonta = stanKonta - kosztKsiazki WHERE uzytkownicy.id=idUzytkownik;
		#Update stan ksiazek
		UPDATE ksiazki SET ksiazki.ilosc = ksiazki.ilosc-1 WHERE ksiazki.id=idKsiazki;
		SELECT 1 AS result;
	END IF;
	
	
END //
DELIMITER ;
CALL AddToOrder(1,31,1)