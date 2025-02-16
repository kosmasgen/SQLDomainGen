CREATE TABLE public.announcements_theme (
	"type" varchar(255) NOT NULL,
	description varchar(255) NOT NULL,
	el_desc varchar(255) NULL,
	tr_desc varchar(255) NULL,
	en_desc varchar(255) NULL,
	hr_desc varchar(255) NULL,
	CONSTRAINT announcements_theme_pkey PRIMARY KEY (type)
);
