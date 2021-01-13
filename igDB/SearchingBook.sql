DROP PROCEDURE IF EXISTS GetSearchResults;
DELIMITER //

CREATE PROCEDURE GetSearchResults(IN title VARCHAR(120),IN author VARCHAR(120),IN category VARCHAR(120),IN priceOrder INT,IN ratingOrder INT)
BEGIN
	DECLARE orderByText VARCHAR(120) DEFAULT "";
	#Creating Sort with Concat
	#Price
	IF priceOrder = 1 AND ratingOrder = 1 THEN
		SET orderByText = " ORDER BY sredniaOcena ASC, ksiazki.cena ASC priceText;";
	END IF;
	IF priceOrder = -1 AND ratingOrder = -1 THEN
		SET orderByText = " ORDER BY sredniaOcena DESC, ksiazki.cena DESC priceText;";
	END IF;
	IF priceOrder = 1 AND ratingOrder = -1 THEN
		SET orderByText = " ORDER BY sredniaOcena DESC, ksiazki.cena ASC priceText;";
	END IF;
	IF priceOrder = -1 AND ratingOrder = 1 THEN
		SET orderByText = " ORDER BY sredniaOcena ASC, ksiazki.cena DESC priceText;";
	END IF;
	IF priceOrder = 0 AND ratingOrder = 0 THEN
		SET orderByText = ";";
	END IF;
	IF priceOrder = 0 AND ratingOrder = 1 THEN
		SET orderByText = " ORDER BY sredniaOcena ASC;";
	END IF;
	IF priceOrder = 0 AND ratingOrder = -1 THEN
		SET orderByText = " ORDER BY sredniaOcena ASC;";
	END IF;
	IF priceOrder = 1 AND ratingOrder = 0 THEN
		SET orderByText = " ORDER BY ksiazki.cena ASC;";
	END IF;
	IF priceOrder = -1 AND ratingOrder = 0 THEN
		SET orderByText = " ORDER BY ksiazki.cena DESC;";
	END IF;
	
	#Left join
	PREPARE STMT1 FROM
	#SELECT
	CONCAT(
	'
		SELECT ksiazki.id,tytul,cena,ilosc,imie,nazwisko,nazwa,AVG(iloscgwiazdek) AS sredniaOcena,kategorie.nazwa as kategoria FROM ksiazki
		JOIN autorzy
		JOIN kategorie
		LEFT JOIN recenzje ON ksiazki.id=recenzje.id_ksiazka
		WHERE 
		autorzy.id = ksiazki.autor AND
		kategorie.id = ksiazki.kategoria AND
		tytul LIKE CONCAT("%",?,"%") AND
		kategorie.nazwa LIKE CONCAT("%",?,"%")AND
		CONCAT(autorzy.imie," ",autorzy.nazwisko) LIKE CONCAT("%",?,"%")
		GROUP BY ksiazki.id
	',orderByText);
	
	EXECUTE STMT1 USING title,category,author;
END //

DELIMITER ;

CALL GetSearchResults("","","",0,0);