SET SCHEMA 'jviewer_main';

-- User --
CREATE TABLE IF NOT EXISTS "user" (
  username TEXT PRIMARY KEY,
  email TEXT UNIQUE NOT NULL,
  password TEXT NOT NULL,
  role TEXT NOT NULL DEFAULT 'ROLE_USER'
);

-- Rooms --
DROP TYPE IF EXISTS ROOM_TYPE;
CREATE TYPE ROOM_TYPE as ENUM ('HTML', 'JAVA');

DROP TABLE IF EXISTS room;
CREATE TABLE IF NOT EXISTS room (
  name TEXT PRIMARY KEY,
  type ROOM_TYPE NOT NULL
);

-- Quiz --
DROP TABLE IF EXISTS quiz;
CREATE TABLE IF NOT EXISTS quiz (
  id SERIAL PRIMARY KEY,
  name TEXT NOT NULL,
  questions_to_answer_to_pass INTEGER NOT NULL
);
INSERT INTO quiz (id, name, questions_to_answer_to_pass) VALUES (26, 'Java SE junior quiz', 2);
INSERT INTO quiz (id, name, questions_to_answer_to_pass) VALUES (27, 'Sample quiz', 3);

-- Question --
DROP TABLE IF EXISTS question;
CREATE TABLE IF NOT EXISTS question (
  id SERIAL PRIMARY KEY,
  quiz_id INTEGER NOT NULL,
  text TEXT NOT NULL,
  answers_type TEXT NOT NULL,
  correct_textual_answer TEXT NOT NULL
);
INSERT INTO question (id, quiz_id, text, answers_type, correct_textual_answer) VALUES (39, 26, 'What is better in sense of search: ArrayList or LinkedList?', 'RADIO_BUTTON', '');
INSERT INTO question (id, quiz_id, text, answers_type, correct_textual_answer) VALUES (59, 26, 'Choose JMS implementations from the list.', 'CHECK_BOX', '');
INSERT INTO question (id, quiz_id, text, answers_type, correct_textual_answer) VALUES (61, 26, 'What is not the method of the Object class?', 'RADIO_BUTTON', '');
INSERT INTO question (id, quiz_id, text, answers_type, correct_textual_answer) VALUES (62, 27, 'To be or not to be?', 'RADIO_BUTTON', '');
INSERT INTO question (id, quiz_id, text, answers_type, correct_textual_answer) VALUES (63, 27, 'Who lives in a pineapple under the sea?', 'CHECK_BOX', '');
INSERT INTO question (id, quiz_id, text, answers_type, correct_textual_answer) VALUES (64, 27, '2 + 2 = ?', 'TEXT_FIELD', '4');

-- Answer --
DROP TABLE IF EXISTS answer;
CREATE TABLE IF NOT EXISTS answer (
  id SERIAL PRIMARY KEY,
  question_id INTEGER NOT NULL,
  text TEXT NOT NULL,
  correct BOOLEAN NOT NULL
);
INSERT INTO answer (id, question_id, text, correct) VALUES (25, 39, 'ArrayList', TRUE);
INSERT INTO answer (id, question_id, text, correct) VALUES (26, 39, 'LinkedList', FALSE);
INSERT INTO answer (id, question_id, text, correct) VALUES (34, 59, 'TIBCO EMS', TRUE);
INSERT INTO answer (id, question_id, text, correct) VALUES (35, 59, 'Websphere MQ', TRUE);
INSERT INTO answer (id, question_id, text, correct) VALUES (36, 59, 'Solace JMS', TRUE);
INSERT INTO answer (id, question_id, text, correct) VALUES (37, 59, 'Fiorano MQ', TRUE);
INSERT INTO answer (id, question_id, text, correct) VALUES (38, 59, 'Sonic MQ', TRUE);
INSERT INTO answer (id, question_id, text, correct) VALUES (39, 59, 'Active MQ', TRUE);
INSERT INTO answer (id, question_id, text, correct) VALUES (40, 61, 'clone', FALSE);
INSERT INTO answer (id, question_id, text, correct) VALUES (41, 61, 'equals', FALSE);
INSERT INTO answer (id, question_id, text, correct) VALUES (42, 61, 'finalize', FALSE);
INSERT INTO answer (id, question_id, text, correct) VALUES (43, 61, 'close', TRUE);
INSERT INTO answer (id, question_id, text, correct) VALUES (44, 62, 'To be', TRUE);
INSERT INTO answer (id, question_id, text, correct) VALUES (45, 62, 'Not to be', FALSE);
INSERT INTO answer (id, question_id, text, correct) VALUES (46, 63, 'Sponge Bob Square Pants', TRUE);
INSERT INTO answer (id, question_id, text, correct) VALUES (47, 63, 'Earthworm Jim', FALSE);
INSERT INTO answer (id, question_id, text, correct) VALUES (48, 63, 'Princess Nesmeyana', FALSE);

-- Localization --
DROP TABLE IF EXISTS localization;

CREATE TABLE localization (
  id SERIAL PRIMARY KEY,
  key TEXT NOT NULL,
  value TEXT NOT NULL,
  locale TEXT NOT NULL,
  dialog_name TEXT NOT NULL
);

-- English --
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J1', 'Welcome to JViewer', 'en', 'index');
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J2', 'Create new account', 'en', 'index');
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J3', 'Login', 'en', 'index');
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J4', 'Username', 'en', 'index');
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J5', 'Password', 'en', 'index');
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J6', 'Registration was successful, now you can login.', 'en', 'index');
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J7', 'Create new account', 'en', 'registration');
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J8', 'Enter your data', 'en', 'registration');
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J9', 'Username', 'en', 'registration');
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J10', 'Password', 'en', 'registration');
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J11', 'Confirm password', 'en', 'registration');
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J12', 'Invitation ID', 'en', 'registration');
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J13', 'Department', 'en', 'registration');
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J14', 'Name must be less than 5 characters', 'en', 'registration');
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J16', 'Create account', 'en', 'registration');
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J17', 'Go back', 'en', 'registration');
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J18', 'Field cannot be empty.', 'en', 'registration');
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J19', 'Name must be less than 5 characters', 'en', 'index');
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J20', 'Password must be less than 6 characters.', 'en', 'registration');
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J21', 'Captcha is wrong.', 'en', 'registration');
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J22', 'User with specified name or email is already exist.', 'en', 'registration');
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J23', 'Invitation ID is wrong.', 'en', 'registration');
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J45', 'Rooms management page', 'en', 'rooms');
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J46', 'Rooms management page', 'en', 'rooms');
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J47', 'Welcome back', 'en', 'rooms');
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J49', 'Create new room', 'en', 'rooms');
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J50', 'Delete', 'en', 'rooms');
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J51', 'Enter', 'en', 'rooms');
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J52', 'Logout', 'en', 'rooms');
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J53', 'Create new room', 'en', 'newroom');
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J54', 'Rooms management page', 'en', 'newroom');
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J55', 'Room name', 'en', 'newroom');
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J56', 'Create', 'en', 'newroom');
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J57', 'Go back', 'en', 'newroom');
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J58', 'Delete selected room', 'en', 'delroom');
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J59', 'Rooms management page', 'en', 'delroom');
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J60', 'Are you sure that you want to delete', 'en', 'delroom');
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J61', 'Yes', 'en', 'delroom');
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J62', 'No', 'en', 'delroom');
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J63', 'Current room', 'en', 'viewer');
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J64', 'Execute', 'en', 'viewer');
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J65', 'Clear', 'en', 'viewer');
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J66', 'Rooms', 'en', 'viewer');
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J67', 'Logout', 'en', 'viewer');
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J68', 'JViewer. Room:', 'en', 'viewer');
INSERT INTO localization (key, value, locale, dialog_name) VALUES ('J69', 'Export result', 'en', 'viewer');

-- News --
CREATE TABLE IF NOT EXISTS news (
  id SERIAL PRIMARY KEY,
  topic TEXT NOT NULL,
  text TEXT NOT NULL,
  date DATE NOT NULL,
  author TEXT NOT NULL
)