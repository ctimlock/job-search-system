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

DROP TABLE IF EXISTS application;
DROP TABLE IF EXISTS job;
CREATE TABLE IF NOT EXISTS job ( 
	id INTEGER PRIMARY KEY AUTOINCREMENT, 
	jobTitle TEXT NOT NULL, 
	recruiterEmail TEXT NOT NULL,
	dateCreated TEXT NOT NULL,
	dateListed TEXT,
	dateDelisted TEXT,
	companyName TEXT,  
	locationId INTEGER NOT NULL, 
	workType TEXT, 
	workingArrangement TEXT, 
	compensation INTEGER, 
	jobLevel TEXT, 
	description TEXT, 
	isAdvertised INTEGER,
	FOREIGN KEY (recruiterEmail) REFERENCES user(email) ON DELETE CASCADE ,
	FOREIGN KEY (locationId) REFERENCES location(id) ON DELETE CASCADE);

	DROP TABLE IF EXISTS user;
CREATE TABLE IF NOT EXISTS user ( 
	email TEXT NOT NULL PRIMARY KEY, 
	accountType TEXT NOT NULL, 
	firstName TEXT NOT NULL, 
	lastName TEXT NOT NULL, 
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
	FOREIGN KEY (locationId) REFERENCES location(id) ON DELETE CASCADE);

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

	Drop table if exists user_keyword;
CREATE TABLE IF NOT EXISTS user_keyword (
	userEmail TEXT NOT NULL,
	keywordId INTEGER NOT NULL,
	FOREIGN KEY (userEmail) REFERENCES user(email) ON DELETE CASCADE,
	FOREIGN KEY (keywordId) REFERENCES keyword(id) ON DELETE CASCADE,
	PRIMARY KEY (userEmail, keywordId));
	
CREATE TABLE IF NOT EXISTS job_keyword (
	jobId INTEGER NOT NULL,
	keywordId INTEGER NOT NULL,
	FOREIGN KEY (jobId) REFERENCES job(id) ON DELETE CASCADE,
	FOREIGN KEY (keywordId) REFERENCES keyword(id) ON DELETE CASCADE,
	PRIMARY KEY (jobId, keywordId));

	
CREATE TABLE IF NOT EXISTS job_category (
	jobId INTEGER NOT NULL,
	categoryId INTEGER NOT NULL,
	FOREIGN KEY (jobId) REFERENCES job(id) ON DELETE CASCADE,
	FOREIGN KEY (categoryId) REFERENCES category(id) ON DELETE CASCADE,
	PRIMARY KEY (jobId, categoryId));