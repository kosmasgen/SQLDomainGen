CREATE TABLE public.announcements_theme (
	"type" VARCHAR(255) NOT NULL,
	description VARCHAR(255) NOT NULL,
	el_desc VARCHAR(255) NULL,
	tr_desc VARCHAR(255) NULL,
	en_desc VARCHAR(255) NULL,
	hr_desc VARCHAR(255) NULL,
	CONSTRAINT announcements_theme_pkey PRIMARY KEY (type)
);