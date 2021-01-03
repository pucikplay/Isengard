INSERT INTO autorzy (imie,nazwisko)
VALUES
	("Jan","Kowalski"),
	("Filip","Kowalski");
	
INSERT INTO kategorie (nazwa)
VALUES
	("Fajne"),
	("Nudne");
	
INSERT INTO ksiazki (tytul,autor,cena,kategoria,ilosc)
VALUES
	("Ksiazka1",1,20.50,1,2),
	("Ksiazka2",2,10.25,2,1);
	
INSERT INTO uzytkownicy (nazwa,haslo,rola,stanKonta)
VALUES
	("user1","abcd",0,0),
	("user2","qwerty",1,0);
	
INSERT INTO recenzje (id_ksiazka,id_uzytkownik,iloscGwiazdek,tekstowaRecenzja)
VALUES
	(1,1,5,"Git");
	
INSERT INTO zamowienia (id_uzytkownik,stan_zamowienia,adres,koszt)
VALUES
	(1,"Wyslane","adres",500.0);
	
INSERT INTO produktyzamowien
VALUES
	(1,1),
	(1,2);