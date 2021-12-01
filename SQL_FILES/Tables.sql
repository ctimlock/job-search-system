DROP TABLE IF EXISTS category;
CREATE TABLE IF NOT EXISTS category ( 
	id INTEGER PRIMARY KEY AUTOINCREMENT, 
	category TEXT NOT NULL);

DROP TABLE IF EXISTS location;
CREATE TABLE IF NOT EXISTS location ( 
	id INTEGER PRIMARY KEY AUTOINCREMENT, 
	country TEXT NOT NULL, 
	state TEXT, 
	city TEXT, 
	postcode TEXT );

DROP TABLE IS EXISTS session;
CREATE TABLE IF NOT EXISTS session ( 
	id INTEGER PRIMARY KEY AUTOINCREMENT, 
	userId INTEGER NOT NULL, 
	logInTime TEXT, 
	logoutTime TEXT, 
	FOREIGN KEY (userId) REFERENCES user(id) ON DELETE CASCADE);

DROP TABLE IF EXISTS application;
DROP TABLE IF EXISTS job;
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

DROP TABLE IF EXISTS application;
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

DROP TABLE IF EXISTS message;
CREATE TABLE IF NOT EXISTS message ( 
	id INTEGER PRIMARY KEY AUTOINCREMENT, 
	userTo INTEGER NOT NULL, 
	userFrom INTEGER NOT NULL, 
	dateTime TEXT, 
	message TEXT, 
	FOREIGN KEY (userTo) REFERENCES user(id) ON DELETE CASCADE,
	FOREIGN KEY (userFrom) REFERENCES user(id)  ON DELETE CASCADE);

DROP TABLE IF EXISTS application;
CREATE TABLE IF NOT EXISTS application (
	id INTEGER PRIMARY KEY AUTOINCREMENT,
	userEmail TEXT NOT NULL, 
	jobId INTEGER NOT NULL, 
	coverLetterDir TEXT NOT NULL, 
	resumeDir TEXT NOT NULL, 
	status TEXT NOT NULL, 
	date TEXT NOT NULL,
	FOREIGN KEY (userEmail) REFERENCES user(userEmail) ON DELETE CASCADE, 
	FOREIGN KEY (jobId) REFERENCES job(id) ON DELETE CASCADE);

Drop table if exists user_keyword;
CREATE TABLE IF NOT EXISTS user_keyword (
	userEmail TEXT NOT NULL,
	keywordId INTEGER NOT NULL,
	FOREIGN KEY (userEmail) REFERENCES user(email) ON DELETE CASCADE,
	FOREIGN KEY (keywordId) REFERENCES keyword(id) ON DELETE CASCADE,
	PRIMARY KEY (userEmail, keywordId));
	
DROP TABLE IF EXISTS job_keyword;
CREATE TABLE IF NOT EXISTS job_keyword (
	jobId INTEGER NOT NULL,
	keywordId INTEGER NOT NULL,
	FOREIGN KEY (jobId) REFERENCES job(id) ON DELETE CASCADE,
	FOREIGN KEY (keywordId) REFERENCES keyword(id) ON DELETE CASCADE,
	PRIMARY KEY (jobId, keywordId));

DROP TABLE IF EXISTS job_category;
CREATE TABLE IF NOT EXISTS job_category (
	jobId INTEGER NOT NULL,
	categoryId INTEGER NOT NULL,
	FOREIGN KEY (jobId) REFERENCES job(id) ON DELETE CASCADE,
	FOREIGN KEY (categoryId) REFERENCES category(id) ON DELETE CASCADE,
	PRIMARY KEY (jobId, categoryId));
	
DROP TABLE IF EXISTS invitation;
CREATE TABLE IF NOT EXISTS invitation (
	id INTEGER PRIMARY KEY AUTOINCREMENT,
	jobSeekerId INTEGER NOT NULL,
    RecruiterId INTEGER NOT NULL,
    jobId INTEGER NOT NULL,
    dateSent TEXT NOT NULL,
    dateOfInterview TEXT,
    locationOfInterviewId INTEGER,
    attachedMessage TEXT,
    typeOfInterview TEXT,
    accepted TEXT,
	FOREIGN KEY (jobSeekerId) REFERENCES user(id) ON DELETE CASCADE,
	FOREIGN KEY (RecruiterId) REFERENCES user(id) ON DELETE CASCADE,
	FOREIGN KEY (jobId) REFERENCES job(id) ON DELETE CASCADE,
	FOREIGN KEY (locationOfInterviewId) REFERENCES location(id) ON DELETE CASCADE);
	