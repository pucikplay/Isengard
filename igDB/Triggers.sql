###Triggery przed usunięciem autora

#usunięcie ksiazki wybranego autora
DROP TRIGGER IF EXISTS usun_autor_ksiazki;
CREATE TRIGGER usun_autor_ksiazki
	BEFORE DELETE ON autorzy
	FOR EACH ROW
DELETE FROM ksiazki WHERE ksiazki.autor=OLD.id;

DROP TRIGGER IF EXISTS usun_ksiazka_recenzja;
CREATE TRIGGER usun_ksiazka_recenzja
	BEFORE DELETE ON ksiazki
	FOR EACH ROW
DELETE FROM recenzje WHERE recenzje.id_ksiazka=OLD.id;

#usuwanie produktow zamowien z ta ksiazka
DROP TRIGGER IF EXISTS usun_ksiazka_produkt;
CREATE TRIGGER usun_ksiazka_produkt
	BEFORE DELETE ON ksiazki
	FOR EACH ROW
DELETE FROM produktyzamowien WHERE produktyzamowien.id_ksiazka=OLD.id;

###Triggery przed usunięciem użytkownika

#usuwanie recenzji
DROP TRIGGER IF EXISTS usun_uzytkownik_recenzje;
CREATE TRIGGER usun_uzytkownik_recenzje
	BEFORE DELETE ON uzytkownicy
	FOR EACH ROW
DELETE FROM recenzje WHERE recenzje.id_uzytkownik=OLD.id;

#usuwanie zamowien
DROP TRIGGER IF EXISTS usun_uzytkownik_zamowienia;
CREATE TRIGGER usun_uzytkownik_zamowienia
	BEFORE DELETE ON uzytkownicy
	FOR EACH ROW
DELETE FROM zamowienia WHERE zamowienia.id_uzytkownik=OLD.id;