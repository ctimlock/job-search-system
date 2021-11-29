CREATE TABLE IF NOT EXISTS category ( 
	id INTEGER PRIMARY KEY AUTOINCREMENT, 
	category TEXT NOT NULL);

CREATE TABLE IF NOT EXISTS location ( 
	id INTEGER PRIMARY KEY AUTOINCREMENT, 
	country TEXT NOT NULL, 
	state TEXT, 
	city TEXT, 
	postcode TEXT );

CREATE TABLE IF NOT EXISTS session ( 
	id INTEGER PRIMARY KEY AUTOINCREMENT, 
	userId INTEGER NOT NULL, 
	logInTime TEXT, 
	logoutTime TEXT, 
	FOREIGN KEY (userId) REFERENCES user(id) ON DELETE CASCADE);

CREATE TABLE IF NOT EXISTS job ( 
	id INTEGER PRIMARY KEY AUTOINCREMENT, 
	jobTitle TEXT NOT NULL, 
	companyName TEXT NOT NULL, 
	categoryId INTEGER, 
	locationId INTEGER, 
	workType TEXT, 
	workingArrangement TEXT, 
	compensation INTEGER, 
	jobLevel TEXT, 
	description TEXT, 
	dateCreated TEXT, 
	FOREIGN KEY (categoryId) REFERENCES category(id) ON DELETE CASCADE ,
	FOREIGN KEY (locationId) REFERENCES Location(id) ON DELETE CASCADE);

	DROP TABLE IF EXISTS user;
CREATE TABLE IF NOT EXISTS user ( 
	id INTEGER PRIMARY KEY AUTOINCREMENT, 
	accountType TEXT NOT NULL, 
	firstName TEXT NOT NULL, 
	lastName TEXT NOT NULL, 
	email TEXT NOT NULL, 
	password TEXT NOT NULL, 
	locationId INTEGER, 
	contactNumber TEXT, 
	dateCreated TEXT, 
	dateOfBirth TEXT, 
	currentJobName TEXT, 
	currentJobLevel TEXT, 
	expectedCompensation INT, 
	resumeDir TEXT, 
	coverLetterDir TEXT, 
	companyName TEXT, 
	recruitingSpecialty TEXT, 
	FOREIGN KEY (locationId) REFERENCES Location(id) ON DELETE CASCADE);

CREATE TABLE IF NOT EXISTS message ( 
	id INTEGER PRIMARY KEY AUTOINCREMENT, 
	userTo INTEGER NOT NULL, 
	userFrom INTEGER NOT NULL, 
	dateTime TEXT, 
	message TEXT, 
	FOREIGN KEY (userTo) REFERENCES user(id) ON DELETE CASCADE,
	FOREIGN KEY (userFrom) REFERENCES user(id)  ON DELETE CASCADE);

CREATE TABLE IF NOT EXISTS application (
	userId INTEGER NOT NULL, 
	jobId INTEGER NOT NULL, 
	coverLetter TEXT NOT NULL, 
	resume TEXT NOT NULL, 
	status TEXT NOT NULL, 
	FOREIGN KEY (userId) REFERENCES user(id) ON DELETE CASCADE, 
	FOREIGN KEY (jobId) REFERENCES job(id) ON DELETE CASCADE, 
	PRIMARY KEY (userId, jobId));

CREATE TABLE IF NOT EXISTS user_keyword (
	userId INTEGER NOT NULL,
	keywordId INTEGER NOT NULL,
	FOREIGN KEY (userId) REFERENCES user(id) ON DELETE CASCADE,
	FOREIGN KEY (keywordId) REFERENCES keyword(id) ON DELETE CASCADE,
	PRIMARY KEY (userId, keywordId));
