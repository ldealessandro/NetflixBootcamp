INSERT INTO AWARDS(ID, NAME, DESCRIPTION) VALUES 
	(1, 'award1', 'premio1'),
	(2, 'award2', 'premio2');

INSERT INTO CATEGORIES(ID, NAME) VALUES 
	(1, 'TERROR');

INSERT INTO TV_SHOWS(ID, NAME, SHORT_DESC, LONG_DESC, YEAR, RECOMMENDED_AGE) VALUES 
	(1, 'Juego de tronos', 'Descripción corta', 'Descripción larga', '2012', 16); 
	
INSERT INTO AWARD_CATEGORIES(ID, DESCRIPTION) VALUES 
	(1, 'MEJOR ACTOR PROTAGONISTA'),
	(2, 'MEJOR ACTRIZ PROTAGONISTA');
	
INSERT INTO TV_SHOW_AWARDS( TV_SHOW_ID, AWARD_ID,AWARD_CATEGORY_ID, YEAR) VALUES 
	(1, 1 , 1, 2010),
	(1, 1 , 2, 2010);