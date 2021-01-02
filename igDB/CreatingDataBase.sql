DROP DATABASE IF EXISTS isengardBookDB;
CREATE DATABASE isengardBookDB;

USE isengardBookDB;

###TABELA KSIAZKI
CREATE TABLE ksiazki
(
	id INT NOT NULL AUTO_INCREMENT,
	tytul VARCHAR(180) NOT NULL,
	autor INT NOT NULL,
	cena FLOAT NOT NULL,
	kategoria INT,
	ilosc INT NOT NULL DEFAULT 0,
	PRIMARY KEY (id)
);
#Constraint Cena > 0 
ALTER TABLE ksiazki ADD CONSTRAINT cenaZeroConstraint CHECK (cena >= 0);


###TABELA RECENZJA
CREATE TABLE recenzje
(
	#id_recenzji INT NOT NULL AUTO_INCREMENT,
	id_ksiazka INT NOT NULL,
	id_uzytkownik INT NOT NULL,
	iloscGwiazdek INT NOT NULL,
	tekstowaRecenzja VARCHAR(500)#,
	#PRIMARY KEY (id_recenzji)
);
#Constraint Gwiazdki e <0,5>
ALTER TABLE recenzje ADD CONSTRAINT recenzjaZeroConstraint CHECK (iloscGwiazdek >= 0);
ALTER TABLE recenzje ADD CONSTRAINT recenzjaFiveConstraint CHECK (iloscGwiazdek <= 5);


###TABELA Kategorie
CREATE TABLE kategorie
(
	id INT NOT NULL AUTO_INCREMENT,
	nazwa VARCHAR(120) NOT NULL,
	PRIMARY KEY (id)
);


###TABELA Autorzy
CREATE TABLE autorzy
(
	id INT NOT NULL AUTO_INCREMENT,
	imie VARCHAR(120) NOT NULL,
	nazwisko VARCHAR(120) NOT NULL,
	PRIMARY KEY (id)
);


###TABELA Uzytkownicy
CREATE TABLE uzytkownicy
(
	id INT NOT NULL AUTO_INCREMENT,
	nazwa VARCHAR(180) NOT NULL,
	haslo VARCHAR(180) NOT NULL,
	rola INT NOT NULL,
	stanKonta INT NOT NULL DEFAULT 0,
	PRIMARY KEY (id)
);


###TABELA Zamowienia
CREATE TABLE zamowienia
(
	id INT NOT NULL AUTO_INCREMENT,
	id_uzytkownik INT NOT NULL,
	stan_zamowienia VARCHAR(90) NOT NULL,
	adres VARCHAR(270) NOT NULL,
	koszt INT NOT NULL,
	PRIMARY KEY (id)
);


###TABELA Produkty zamowien?
CREATE TABLE produktyZamowien
(
	id_zamowienia INT NOT NULL,
	id_ksiazki INT NOT NULL
);

#Klucze obce

##KSIAZKI
###Ksiazki -> Kategoria
ALTER TABLE ksiazki ADD CONSTRAINT FK_KsiazkiKategorie FOREIGN KEY (kategoria) REFERENCES kategorie(id);
###Ksiazki -> Autor
ALTER TABLE ksiazki ADD CONSTRAINT FK_KsiazkiAutorzy FOREIGN KEY (autor) REFERENCES autorzy(id);

##RECENZJE
###Recenzje -> Ksiazka
ALTER TABLE recenzje ADD CONSTRAINT FK_RecenzjaKsiazka FOREIGN KEY (id_ksiazka) REFERENCES ksiazki(id);
###Recenzje -> Ksiazka
ALTER TABLE recenzje ADD CONSTRAINT FK_RecenzjaUzytkownik FOREIGN KEY (id_uzytkownik) REFERENCES uzytkownicy(id);

##Zamowienia
###Zamowienia -> Uzytkownicy
ALTER TABLE zamowienia ADD CONSTRAINT FK_ZamowieniaUzytkownik FOREIGN KEY (id_uzytkownik) REFERENCES uzytkownicy(id);
##produktZamowien

###Produkt zamowienia -> Zamowienie
ALTER TABLE produktyZamowien ADD CONSTRAINT FK_ElementZamowienia FOREIGN KEY (id_zamowienia) REFERENCES zamowienia(id);
###Produkt zamowienia -> Ksiazka
ALTER TABLE produktyZamowien ADD CONSTRAINT FK_ElementKsiazka FOREIGN KEY (id_ksiazki) REFERENCES ksiazki(id);