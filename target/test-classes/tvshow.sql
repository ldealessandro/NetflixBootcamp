INSERT INTO CATEGORIES(ID, NAME) VALUES 
	(1, 'TERROR'),
	(2, 'otra');

INSERT INTO TV_SHOWS(ID, NAME, SHORT_DESC, LONG_DESC, YEAR, RECOMMENDED_AGE) VALUES 
	(1, 'TvShow1', 'Descripción corta', 'Descripción larga', '2012', 16),
	(2, 'TvShow2', 'Descripción corta', 'Descripción larga', '2012', 16); 

INSERT INTO TV_SHOW_CATEGORIES( ID, TV_SHOW_ID, CATEGORY_ID) VALUES 
	(1, 1, 1),
	(2, 1, 2),
	(3, 2, 1),
	(4, 2, 2);
	