--create user roles database with one to many relationship with users table
CREATE TABLE ERS_USER_ROLES(
	ERS_USER_ROLE_ID int PRIMARY KEY, 
	USER_ROLE TEXT NOT NULL
);

--create ers_users table with many to one? relationship to ers_reimbursement table
CREATE TABLE ERS_USERS(
	ERS_USERS_ID int PRIMARY KEY, 
	ERS_USERNAME TEXT UNIQUE NOT NULL, --WHAT DOES THE DIAMOND MEAN?????
	ERS_PASSWORD VARCHAR(20) NOT NULL, --salt concatenated with passhass??? implement AT SOME point
	USER_FIRST_NAME TEXT NOT NULL, 
	USER_LAST_NAME TEXT NOT NULL, 
	USER_EMAIL TEXT UNIQUE NOT NULL, --WHAT DOES THE DIAMOND MEAN?????
	USER_ROLE_ID int NOT NULL REFERENCES ERS_USER_ROLES(ERS_USER_ROLE_ID) -- figure OUT IF this has TO be NOT null
	
);

--START HERE create ers_reimbursement table many to one? relationship with ers_reimbursement_status
CREATE TABLE ERS_REIMBURSEMENT(
	
);

--DROP TABLE ers_users; 