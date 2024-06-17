CREATE TABLE IF NOT EXISTS Users (
	id IDENTITY NOT NULL,
	name VARCHAR NOT NULL,
	password varchar not null,
	flow_id INT NOT NULL,
	admission_year SMALLINT NOT NULL,
	course_num TINYINT NOT NULL,
	default_folder_id int not null default 0
);

create table if not exists Flows (
	id identity,
	code char(3) not null,
	name varchar not null
);

create table if not exists Folders (
	id identity,
	name varchar not null,
	author_id integer,
	parent_folder_id integer not null
);

create table if not exists Documents (
	id identity,
	name varchar not null,
	author_id integer,
	parent_folder_id integer not null
);