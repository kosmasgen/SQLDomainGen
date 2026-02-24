CREATE TABLE pep_schema.bg_poi (
                                   id uuid  NOT NULL,
                                   chamber_id int4 NOT NULL,
                                   date_created timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL,
                                   last_updated timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL,
                                   rec_deleted bool DEFAULT FALSE NOT NULL,
                                   latitude varchar(255) NOT NULL,
                                   longitude varchar(255) NOT NULL,
                                   CONSTRAINT pk_bg_poi PRIMARY KEY (id)
);

CREATE TABLE pep_schema.business_location (
                                              id uuid NOT NULL,
                                              code varchar(255) NOT NULL,
                                              date_created timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL,
                                              last_updated timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL,
                                              rec_deleted bool DEFAULT false NOT NULL,
                                              blob_uri text NULL,
                                              CONSTRAINT business_location_pkey PRIMARY KEY (id)
);

CREATE TABLE pep_schema.chamber_department (
                                               id uuid NOT NULL,
                                               chamber_department_id int4 NULL,
                                               chamber_id int4 NULL,
                                               date_created timestamp DEFAULT CURRENT_TIMESTAMP NULL,
                                               last_updated timestamp DEFAULT CURRENT_TIMESTAMP NULL,
                                               rec_deleted bool DEFAULT FALSE NOT NULL,
                                               cd varchar(5) NOT NULL,
                                               CONSTRAINT chamber_department_pkey PRIMARY KEY (id),
                                               CONSTRAINT uk_chamber_department UNIQUE (chamber_id, chamber_department_id)
);

CREATE TABLE pep_schema.company_contact_message (
                                                    id uuid NOT NULL,
                                                    full_name varchar(100) NULL,
                                                    sender_email varchar(255) NULL,
                                                    subject varchar(150) NULL,
                                                    message varchar(5000) NULL,
                                                    date_created timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL,
                                                    company_id uuid NULL,
                                                    CONSTRAINT company_contact_message_pkey PRIMARY KEY (id)
);

CREATE TABLE pep_schema.company_status (
                                           id uuid NOT NULL,
                                           chamber_id int4 NOT NULL,
                                           chamber_company_status_id int4 NULL,
                                           date_created timestamp NOT NULL,
                                           last_updated timestamp NOT NULL,
                                           rec_deleted bool DEFAULT FALSE NULL,
                                           CONSTRAINT company_status_pkey PRIMARY KEY (id),
                                           CONSTRAINT uk_company_status UNIQUE (chamber_id, chamber_company_status_id)
);

CREATE TABLE pep_schema.company_view_rules (
                                               id uuid NOT NULL,
                                               date_created timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL,
                                               last_updated timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL,
                                               chamber_id int8 NULL,
                                               show_mobile_phone bool NULL,
                                               show_phones bool NULL,
                                               show_email bool NULL,
                                               show_afm bool NULL,
                                               show_business_information bool NULL,
                                               CONSTRAINT company_view_rules_pkey PRIMARY KEY (id)
);

CREATE TABLE pep_schema.corporate_status (
                                             id uuid NOT NULL,
                                             chamber_corporate_status_id int4 NULL,
                                             chamber_id int4 NULL,
                                             cd varchar(5) NOT NULL,
                                             date_created timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL,
                                             last_updated timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL,
                                             rec_deleted bool DEFAULT FALSE NOT NULL,
                                             CONSTRAINT pk_corporate_status PRIMARY KEY (id),
                                             CONSTRAINT uk_corporate_status UNIQUE (chamber_id, chamber_corporate_status_id)
);

CREATE TABLE pep_schema.country (
                                    id uuid NOT NULL,
                                    date_created timestamp DEFAULT CURRENT_TIMESTAMP NULL,
                                    last_updated timestamp DEFAULT CURRENT_TIMESTAMP NULL,
                                    rec_deleted bool DEFAULT FALSE NOT NULL,
                                    chamber_id int4 NOT NULL,
                                    region_id uuid NULL,
                                    chamber_country_id int4 NULL,
                                    CONSTRAINT country_chamber_unique UNIQUE (chamber_id, chamber_country_id),
                                    CONSTRAINT pk_country PRIMARY KEY (id)
);

CREATE TABLE pep_schema.data_staging (
                                         id long NOT NULL,
                                         legacy_table_name varchar(100) NOT NULL,
                                         legacy_record_id varchar(255) NOT NULL,
                                         raw_data jsonb NOT NULL,
                                         legacy_updated_at timestamp NOT NULL,
                                         pulled_at timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL,
                                         status varchar(20) DEFAULT 'PENDING'::character varying NOT NULL,
                                         CONSTRAINT data_staging_legacy_table_name_legacy_record_id_legacy_upda_key UNIQUE (legacy_table_name, legacy_record_id, legacy_updated_at),
                                         CONSTRAINT data_staging_pkey PRIMARY KEY (id)
);

CREATE TABLE pep_schema.folder (
                                   id uuid  NOT NULL,
                                   descr text NOT NULL,
                                   uri varchar(2000) NOT NULL,
                                   date_created timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL,
                                   last_updated timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL,
                                   rec_deleted bool DEFAULT FALSE NOT NULL,
                                   CONSTRAINT pk_folder PRIMARY KEY (id)
);

CREATE TABLE pep_schema.income_gemi_payment (
                                                id uuid NOT NULL,
                                                chamber_id int4 NOT NULL,
                                                payment_type varchar(255) NULL,
                                                sale_ts timestamp NULL,
                                                chamber_amount numeric(19, 2) NULL,
                                                chamber_amount_for_certs numeric(19, 2) NULL,
                                                chamber_amount_for_postal numeric(19, 2) NULL,
                                                total_amount_paid numeric(19, 2) NULL,
                                                descr varchar(500) NULL,
                                                payer varchar(500) NULL,
                                                gemi_payment_id numeric NOT NULL,
                                                company_gemi_id numeric NULL,
                                                co_name varchar(1000) NULL,
                                                company_chamber_id numeric NULL,
                                                payment_method varchar(255) NOT NULL,
                                                ri3 varchar(25) NULL,
                                                subscription_start_date timestamp NULL,
                                                subscription_end_date timestamp NULL,
                                                cancel_flag numeric NULL,
                                                refund_ts timestamp NULL,
                                                remittance_dt timestamp NULL,
                                                remittance_amount numeric(19, 2) NULL,
                                                remittance_reference varchar(20) NULL,
                                                last_updated timestamp NULL,
                                                CONSTRAINT pk_income_gemi_payment PRIMARY KEY (id),
                                                CONSTRAINT uk_income_gemi_payment UNIQUE (chamber_id, gemi_payment_id, payment_type, cancel_flag)
);

CREATE TABLE pep_schema.income_payment_method (
                                                  id uuid NOT NULL,
                                                  chamber_id int4 NOT NULL,
                                                  chamber_pay_method_id int4 NOT NULL,
                                                  description varchar(255) NOT NULL,
                                                  last_updated timestamp NOT NULL,
                                                  rec_deleted int4 NULL,
                                                  CONSTRAINT pk_income_payment_method PRIMARY KEY (id),
                                                  CONSTRAINT uk_in_pay_method UNIQUE (chamber_id, chamber_pay_method_id)
);

CREATE TABLE pep_schema.income_type (
                                        id uuid NOT NULL,
                                        chamber_id int4 NOT NULL,
                                        chamber_type_id int4 NOT NULL,
                                        description varchar(255) NOT NULL,
                                        last_updated timestamp NOT NULL,
                                        rec_deleted bool NULL,
                                        date_created timestamp NULL,
                                        CONSTRAINT pk_income_type PRIMARY KEY (id),
                                        CONSTRAINT uk_income_type UNIQUE (chamber_id, chamber_type_id)
);

CREATE TABLE pep_schema.languages (
                                      id uuid NOT NULL,
                                      chamber_id int4 NOT NULL,
                                      cd varchar(3) NOT NULL,
                                      descr varchar(50) NOT NULL,
                                      chamber_language_id int4 NULL,
                                      CONSTRAINT languages_chamber_language_id_key UNIQUE (chamber_language_id),
                                      CONSTRAINT pk_languages PRIMARY KEY (id),
                                      CONSTRAINT uk_languages_cd UNIQUE (cd)
);

CREATE TABLE pep_schema.municipality (
                                         id uuid NOT NULL,
                                         chamber_id int8 NULL,
                                         chamber_municipality_id int8 NULL,
                                         description varchar(255) NULL,
                                         date_created timestamp NULL,
                                         last_updated timestamp NULL,
                                         rec_deleted bool DEFAULT FALSE NULL,
                                         cd varchar(255) DEFAULT ''::character varying NOT NULL,
                                         is_proteas_data bool DEFAULT false NULL,
                                         CONSTRAINT municipality_chamber_unique UNIQUE (chamber_id, chamber_municipality_id),
                                         CONSTRAINT municipality_pkey PRIMARY KEY (id)
);

CREATE TABLE pep_schema.product (
                                    id uuid NOT NULL,
                                    chamber_id int4 NULL,
                                    chamber_product_id int8 NOT NULL,
                                    version int4 NOT NULL,
                                    cd varchar(20) NOT NULL,
                                    cd_gemi varchar(255) NULL,
                                    date_created timestamp NOT NULL,
                                    last_updated timestamp NOT NULL,
                                    parent_product_id int8 NULL,
                                    rec_deleted bool DEFAULT FALSE NOT NULL,
                                    CONSTRAINT product_pkey PRIMARY KEY (id)
);

CREATE TABLE pep_schema.profession_friendly_category (
                                                         id varchar(100) NOT NULL,
                                                         descr varchar(1000) NULL,
                                                         CONSTRAINT profession_friendly_category_pkey PRIMARY KEY (id)
);

CREATE TABLE pep_schema.profession_kind (
                                            id uuid DEFAULT NOT NULL,
                                            chamber_id int4 NOT NULL,
                                            chamber_prof_kind_id int4 NULL,
                                            cd varchar(255) NOT NULL,
                                            date_created timestamp DEFAULT CURRENT_TIMESTAMP NULL,
                                            last_updated timestamp DEFAULT CURRENT_TIMESTAMP NULL,
                                            rec_deleted bool DEFAULT FALSE NOT NULL,
                                            CONSTRAINT pk_profession_kind PRIMARY KEY (id),
                                            CONSTRAINT uk_chamber_prof_kind UNIQUE (chamber_id, chamber_prof_kind_id)
);

CREATE TABLE pep_schema.profession_system (
                                              id uuid NOT NULL,
                                              chamber_id int4 NOT NULL,
                                              chamber_prof_system_id int4 NULL,
                                              cd varchar(255) NOT NULL,
                                              description varchar(255) NOT NULL,
                                              date_created timestamp DEFAULT CURRENT_TIMESTAMP NULL,
                                              last_updated timestamp DEFAULT CURRENT_TIMESTAMP NULL,
                                              rec_deleted bool DEFAULT FALSE NOT NULL,
                                              CONSTRAINT pk_profession_system PRIMARY KEY (id),
                                              CONSTRAINT uk_chamber_prof_system UNIQUE (chamber_id, chamber_prof_system_id)
);

CREATE TABLE pep_schema.stats_expense (
                                          id uuid NOT NULL,
                                          chamber_id int4 NOT NULL,
                                          account_sum_id numeric NOT NULL,
                                          cd_use varchar(4) NOT NULL,
                                          group_descr varchar(300) NULL,
                                          mm varchar(2) NOT NULL,
                                          amount numeric(19, 2) NULL,
                                          last_updated timestamp NULL,
                                          rec_deleted numeric NULL,
                                          CONSTRAINT pk_stats_expense PRIMARY KEY (id),
                                          CONSTRAINT uk_stats_expense UNIQUE (chamber_id, account_sum_id)
);

CREATE TABLE pep_schema.sync_watermarks (
                                            id bigserial NOT NULL,
                                            table_name varchar(100) NOT NULL,
                                            last_sync_timestamp timestamp NOT NULL,
                                            updated_at timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL,
                                            CONSTRAINT sync_watermarks_pkey PRIMARY KEY (id),
                                            CONSTRAINT sync_watermarks_table_name_key UNIQUE (table_name)
);

CREATE TABLE pep_schema.syncruns (
                                     id long NOT NULL,
                                     lastrun timestamp NULL,
                                     trades_lastrun timestamp NULL,
                                     is_running bool DEFAULT false NULL,
                                     CONSTRAINT syncruns_pkey PRIMARY KEY (id)
);

CREATE TABLE pep_schema.syncruns_error_log (
                                               id serial4 NOT NULL,
                                               date_created NOT NULL,
                                               error_message text NULL,
                                               table_name varchar(100) NOT NULL,
                                               table_chamber_id numeric NOT NULL,
                                               email_send_date timestamp NULL,
                                               CONSTRAINT syn_error_pkey PRIMARY KEY (id)
);

CREATE TABLE pep_schema.temporary_company (
                                              id long NOT NULL,
                                              version numeric(19) NOT NULL,
                                              address_city varchar(50) NULL,
                                              address_country_id numeric(19) NULL,
                                              address_latitude varchar(255) NULL,
                                              address_longitude varchar(255) NULL,
                                              address_municipality_alt varchar(255) NULL,
                                              address_municipality_pri_id numeric(19) NULL,
                                              address_municipality_sec_id numeric(19) NULL,
                                              address_po_box varchar(255) NULL,
                                              address_prefecture_id numeric(19) NULL,
                                              address_region varchar(50) NULL,
                                              address_street varchar(255) NULL,
                                              address_street_number varchar(255) NULL,
                                              address_zip_code varchar(12) NULL,
                                              address_zoom_level numeric(10) NULL,
                                              address_indic_id numeric(19) NULL,
                                              afm varchar(9) NULL,
                                              am numeric(19) NULL,
                                              armae varchar(50) NULL,
                                              board_dur varchar(15) NULL,
                                              branch_type_id numeric(19) NULL,
                                              cancel_date timestamp(6) NULL,
                                              cancel_reason_id numeric(19) NULL,
                                              cd numeric(19) NOT NULL,
                                              chamber_department_id numeric(19) NULL,
                                              chamber_gemi_responsible_id numeric(19) NULL,
                                              chamber_registered_id numeric NOT NULL,
                                              co_name varchar(1000) NOT NULL,
                                              co_name_nrm varchar(1000) NULL,
                                              comerc_reg_code numeric(19) NULL,
                                              company_status_id numeric(19) NULL,
                                              contact_email varchar(100) NULL,
                                              contact_facebook varchar(255) NULL,
                                              contact_fax varchar(20) NULL,
                                              contact_mobile varchar(50) NULL,
                                              contact_phone1 varchar(20) NULL,
                                              contact_phone2 varchar(20) NULL,
                                              contact_phone3 varchar(20) NULL,
                                              contact_phone_area varchar(15) NULL,
                                              contact_telex varchar(50) NULL,
                                              contact_twitter varchar(255) NULL,
                                              contact_url varchar(256) NULL,
                                              corporate_status_id numeric(19) NOT NULL,
                                              date_created timestamp(6) NOT NULL,
                                              date_gemi_registered timestamp(6) NULL,
                                              date_incorporated timestamp(6) NULL,
                                              date_profession_started timestamp(6) NULL,
                                              date_registered timestamp(6) NULL,
                                              dispute_date timestamp(6) NULL,
                                              dispute_dec_date timestamp(6) NULL,
                                              dispute_number varchar(30) NULL,
                                              edra varchar(30) NULL,
                                              email2 varchar(100) NULL,
                                              email3 varchar(100) NULL,
                                              email4 varchar(100) NULL,
                                              endfirstfy timestamp(6) NULL,
                                              eu_commerce numeric(1) NULL,
                                              exp_management_dt timestamp(6) NULL,
                                              expire_date timestamp(6) NULL,
                                              financial_year_id numeric(19) NULL,
                                              foundation_date timestamp(6) NULL,
                                              gemh_other_per_cd numeric(19) NULL,
                                              gemi_number varchar(12) NULL,
                                              hp varchar(8) NULL,
                                              indefinite numeric(1) NULL,
                                              last_state_change_date timestamp(6) NULL,
                                              last_updated timestamp(6) NOT NULL,
                                              licence_exp_dt timestamp(6) NULL,
                                              licence_no varchar(25) NULL,
                                              mail_address numeric(1) NOT NULL,
                                              mail_name varchar(60) NULL,
                                              me_criteria1_id numeric(19) NULL,
                                              me_criteria2_id numeric(19) NULL,
                                              member numeric(1) NULL,
                                              member_dues timestamp(6) NULL,
                                              nationality_id numeric(19) NULL,
                                              nextam numeric(19) NULL,
                                              objective text NULL,
                                              oldam varchar(10) NULL,
                                              pendency varchar(500) NULL,
                                              pending numeric(1) NULL,
                                              previousam numeric(19) NULL,
                                              rec_type varchar(1) NULL,
                                              recdeleted numeric(19) NOT NULL,
                                              registration_type_id numeric(19) NULL,
                                              sale_type_id numeric(19) NULL,
                                              startfirstfy timestamp(6) NULL,
                                              subscr_cat numeric(19) NULL,
                                              tax_service_id numeric(19) NULL,
                                              user_ins varchar(255) NULL,
                                              user_last_upd varchar(255) NULL,
                                              vote_department_id numeric(19) NULL,
                                              votes numeric(19) NULL,
                                              management_dur varchar(15) NULL,
                                              receive_newsletter numeric(1) NULL,
                                              gemi_last_updated date NULL,
                                              gemi_last_state_change_date date NULL,
                                              gemi_parent_gemi_number varchar(12) NULL,
                                              gemi_municipality_id numeric(19) NULL,
                                              gemi_city varchar(50) NULL,
                                              gemi_region varchar(50) NULL,
                                              gemi_street varchar(255) NULL,
                                              gemi_street_number varchar(255) NULL,
                                              gemi_zip_code varchar(12) NULL,
                                              gemi_phone1 varchar(120) NULL,
                                              gemi_phone2 varchar(15) NULL,
                                              gemi_phone3 varchar(15) NULL,
                                              gemi_mobile varchar(50) NULL,
                                              gemi_fax varchar(120) NULL,
                                              gemi_email varchar(100) NULL,
                                              gemi_created numeric(1) DEFAULT 0 NOT NULL,
                                              gemi_id numeric(19) NULL,
                                              gemi_date_created date NULL,
                                              article text NULL,
                                              show_email numeric(1) NULL,
                                              gemi_id2 numeric(19) NULL,
                                              vote_dt date NULL,
                                              vote_flag bpchar(1) NULL,
                                              vote_etairia_flag bpchar(1) NULL,
                                              gemi_date_incorporated timestamp(6) NULL,
                                              me_criteria3_id numeric(19) NULL,
                                              date_interruption timestamp(6) NULL,
                                              cancel_reason_dscr varchar(300) NULL,
                                              bankrupt_date timestamp(6) NULL,
                                              start_dt_corp_status timestamp(6) NULL,
                                              end_dt_corp_status timestamp(6) NULL,
                                              bankrupt_number varchar(30) NULL,
                                              last_change_date timestamp(6) NULL,
                                              next_company_id numeric(19) NULL,
                                              parent_company_id numeric(19) NULL,
                                              previous_company_id numeric(19) NULL,
                                              transfer_flag numeric(1) NULL,
                                              transfer_am numeric(19) NULL,
                                              proeg_occupation_id numeric(19) NULL,
                                              proeg_subscr_amnt numeric(19, 2) NULL,
                                              proeg_subscr_year varchar(4) NULL,
                                              proeg_subscr_date timestamp(6) NULL,
                                              proeg_subscr_notes varchar(300) NULL,
                                              migr_capitol numeric NULL,
                                              migr_capitol2 numeric(19, 2) NULL,
                                              migr_many_children_flag varchar(1) NULL,
                                              migr_amea_flag varchar(1) NULL,
                                              migr_ypokat_flag varchar(1) NULL,
                                              migr_thrasher_flag varchar(1) NULL,
                                              migr_low_capital_flag varchar(1) NULL,
                                              migr_send_tax_serv_flag varchar(1) NULL,
                                              print_katast_flag numeric(1) DEFAULT 1 NOT NULL,
                                              subscr_calc_date varchar(255) NULL,
                                              show_business_guide numeric(1) DEFAULT 1 NOT NULL,
                                              CONSTRAINT pk_company PRIMARY KEY (id)
);

CREATE TABLE pep_schema.temporary_company_profession (
                                                         id long NOT NULL,
                                                         version numeric(19) NOT NULL,
                                                         company_id numeric(19) NOT NULL,
                                                         date_created timestamp(6) NOT NULL,
                                                         from_date timestamp(6) NULL,
                                                         last_updated timestamp(6) NOT NULL,
                                                         profession_id numeric(19) NOT NULL,
                                                         profession_kind_id numeric(19) NULL,
                                                         rec_eleted numeric(19) NOT NULL,
                                                         to_date timestamp(6) NULL,
                                                         gemi_id numeric(19) NULL,
                                                         gemi_date_created date NULL,
                                                         gemi_last_updated date NULL,
                                                         CONSTRAINT pk_company_profession PRIMARY KEY (id)
);

CREATE TABLE pep_schema.temporary_company_title (
                                                    id long NOT NULL,
                                                    version numeric(19) NOT NULL,
                                                    company_id numeric(19) NULL,
                                                    company_preregistration_id numeric(19) NULL,
                                                    date_created timestamp(6) NOT NULL,
                                                    from_date timestamp(6) NULL,
                                                    last_updated timestamp(6) NOT NULL,
                                                    order_seq numeric NOT NULL,
                                                    rec_deleted numeric(19) NOT NULL,
                                                    title varchar(1000) NULL,
                                                    title_latin varchar(255) NULL,
                                                    title_nrm varchar(1000) NULL,
                                                    title_status_id numeric(19) NULL,
                                                    to_date timestamp(6) NULL,
                                                    gemi_id numeric(19) NULL,
                                                    gemi_date_created date NULL,
                                                    gemi_last_updated date NULL,
                                                    CONSTRAINT pk_temporary_company_title PRIMARY KEY (id)

);

CREATE TABLE pep_schema.temporary_company_titlei18n (
                                                        id long NOT NULL,
                                                        version numeric(19) NOT NULL,
                                                        company_title_id numeric(19) NOT NULL,
                                                        date_created timestamp(6) NOT NULL,
                                                        language_id numeric(19) NOT NULL,
                                                        last_updated timestamp(6) NOT NULL,
                                                        rec_deleted numeric(19) NOT NULL,
                                                        title varchar(1000) NULL,
                                                        gemi_id numeric(19) NULL,
                                                        gemi_date_created date NULL,
                                                        gemi_last_updated date NULL,
                                                        CONSTRAINT pk_temporary_company_title_i18n PRIMARY KEY (id)
);

CREATE TABLE pep_schema.temporary_companyi18n (
                                                  id long NOT NULL,
                                                  version numeric(19) NOT NULL,
                                                  city varchar(50) NULL,
                                                  co_name varchar(1000) NULL,
                                                  company_id numeric(19) NOT NULL,
                                                  date_created timestamp(6) NOT NULL,
                                                  language_id numeric(19) NOT NULL,
                                                  last_updated timestamp(6) NOT NULL,
                                                  mail_name varchar(60) NULL,
                                                  objective text NULL,
                                                  rec_deleted numeric(19) NOT NULL,
                                                  street varchar(60) NULL,
                                                  comments text NULL,
                                                  gemi_id numeric(19) NULL,
                                                  gemi_date_created date NULL,
                                                  gemi_last_updated date NULL,
                                                  gemi_city varchar(24) NULL,
                                                  article text NULL,
                                                  CONSTRAINT pk_temporary_company_i18n PRIMARY KEY (id)
);

CREATE TABLE pep_schema.bg_poi_i18n (
                                        id uuid NOT NULL,
                                        date_created timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL,
                                        last_updated timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL,
                                        rec_deleted bool DEFAULT FALSE NOT NULL,
                                        title varchar(255) NOT NULL,
                                        poi_id uuid NOT NULL,
                                        language_id uuid NOT NULL,
                                        CONSTRAINT pk_bg_poi_i18n PRIMARY KEY (id),
                                        CONSTRAINT fk_language_id FOREIGN KEY (language_id) REFERENCES pep_schema.languages(id),
                                        CONSTRAINT fk_poi_id FOREIGN KEY (poi_id) REFERENCES pep_schema.bg_poi(id)
);

CREATE TABLE pep_schema.business_location_i18n (
                                                   description varchar(255) NOT NULL,
                                                   code varchar(255) NOT NULL,
                                                   date_created timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL,
                                                   last_updated timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL,
                                                   rec_deleted bool DEFAULT FALSE NOT NULL,
                                                   business_location_id uuid NOT NULL,
                                                   language_id uuid NOT NULL,
                                                   CONSTRAINT business_location_i18n_pkey PRIMARY KEY (business_location_id, language_id),
                                                   CONSTRAINT fk_business_location FOREIGN KEY (business_location_id) REFERENCES pep_schema.business_location(id) ON DELETE CASCADE,
                                                   CONSTRAINT fk_business_location_language FOREIGN KEY (language_id) REFERENCES pep_schema.languages(id)
);

CREATE TABLE pep_schema.chamber_departmenti18n (
                                                   department_id uuid NOT NULL,
                                                   language_id uuid NOT NULL,
                                                   description varchar(50) NOT NULL,
                                                   date_created timestamp DEFAULT CURRENT_TIMESTAMP NULL,
                                                   last_updated timestamp DEFAULT CURRENT_TIMESTAMP NULL,
                                                   rec_deleted bool DEFAULT FALSE NOT NULL,
                                                   chamber_i18n_id int4 NULL,
                                                   CONSTRAINT pk_chamber_departmenti18n PRIMARY KEY (department_id, language_id),
                                                   CONSTRAINT uk_chamber_dep_i18n UNIQUE (department_id, chamber_i18n_id),
                                                   CONSTRAINT fk_chamber_dep_i18n FOREIGN KEY (department_id) REFERENCES pep_schema.chamber_department(id),
                                                   CONSTRAINT fk_chamber_dep_i18n_lang FOREIGN KEY (language_id) REFERENCES pep_schema.languages(id)
);

CREATE TABLE pep_schema.company_status_view_rules (
                                                      company_status_id uuid NOT NULL,
                                                      company_view_rules_id uuid NOT NULL,
                                                      exclude_companies bool NULL,
                                                      date_created timestamp NULL,
                                                      last_updated timestamp NULL,
                                                      CONSTRAINT pk_company_status_view_rules PRIMARY KEY (company_status_id, company_view_rules_id),
                                                      CONSTRAINT fk_company_status_rules_company_status FOREIGN KEY (company_status_id) REFERENCES pep_schema.company_status(id),
                                                      CONSTRAINT fk_company_status_rules_company_view FOREIGN KEY (company_view_rules_id) REFERENCES pep_schema.company_view_rules(id)
);

CREATE TABLE pep_schema.company_statusi18n (
                                               company_status_id uuid NOT NULL,
                                               language_id uuid NOT NULL,
                                               description varchar(100) NOT NULL,
                                               date_created timestamp NULL,
                                               last_updated timestamp NULL,
                                               rec_deleted bool DEFAULT FALSE NOT NULL,
                                               chamber_i18n_id int4 NULL,
                                               CONSTRAINT pk_company_status_i18n PRIMARY KEY (company_status_id, language_id),
                                               CONSTRAINT uk_co_status_i18n_chamber UNIQUE (company_status_id, chamber_i18n_id),
                                               CONSTRAINT fk_co_status_i18n FOREIGN KEY (company_status_id) REFERENCES pep_schema.company_status(id),
                                               CONSTRAINT fk_co_status_i18n_lang FOREIGN KEY (language_id) REFERENCES pep_schema.languages(id)
);

CREATE TABLE pep_schema.corporate_status_view_rules (
                                                        corporate_status_id uuid NOT NULL,
                                                        company_view_rules_id uuid NOT NULL,
                                                        exclude_companies bool NULL,
                                                        show_contact_info bool NULL,
                                                        date_created timestamp NULL,
                                                        last_updated timestamp NULL,
                                                        CONSTRAINT pk_corporate_status_view_rules PRIMARY KEY (corporate_status_id, company_view_rules_id),
                                                        CONSTRAINT fk_corporate_status_rules_company_view FOREIGN KEY (company_view_rules_id) REFERENCES pep_schema.company_view_rules(id),
                                                        CONSTRAINT fk_corporate_status_rules_corporate_status FOREIGN KEY (corporate_status_id) REFERENCES pep_schema.corporate_status(id)
);

CREATE TABLE pep_schema.corporate_statusi18n (
                                                 corporate_status_id uuid NOT NULL,
                                                 language_id uuid NOT NULL,
                                                 description varchar(50) NOT NULL,
                                                 date_created timestamp NULL,
                                                 last_updated timestamp NULL,
                                                 rec_Deleted bool DEFAULT FALSE NOT NULL,
                                                 chamber_i18n_id int4 NULL,
                                                 grouped_description varchar(50) DEFAULT NULL::character varying NULL,
                                                 CONSTRAINT pk_corporate_status_i18n PRIMARY KEY (corporate_status_id, language_id),
                                                 CONSTRAINT uk_corp_status_i18n_chamber UNIQUE (corporate_status_id, chamber_i18n_id),
                                                 CONSTRAINT fk_corp_status_i18n FOREIGN KEY (corporate_status_id) REFERENCES pep_schema.corporate_status(id),
                                                 CONSTRAINT fk_corp_status_i18n_lang FOREIGN KEY (language_id) REFERENCES pep_schema.languages(id)
);

CREATE TABLE pep_schema.country_i18n (
                                         country_id uuid NOT NULL,
                                         language_id uuid NOT NULL,
                                         description varchar(400) NOT NULL,
                                         date_created timestamp DEFAULT CURRENT_TIMESTAMP NULL,
                                         last_updated timestamp DEFAULT CURRENT_TIMESTAMP NULL,
                                         rec_deleted bool DEFAULT FALSE NOT NULL,
                                         chamber_country_i18n_id int4 NULL,
                                         CONSTRAINT country_i18n_pkey PRIMARY KEY (country_id, language_id),
                                         CONSTRAINT country_i18n_unique UNIQUE (country_id, chamber_country_i18n_id),
                                         CONSTRAINT fk_country_i18n FOREIGN KEY (country_id) REFERENCES pep_schema.country(id),
                                         CONSTRAINT fk_country_i18n_lang FOREIGN KEY (language_id) REFERENCES pep_schema.languages(id)
);

CREATE TABLE pep_schema.income_transaction (
                                               id uuid DEFAULT NOT NULL,
                                               chamber_id int4 NOT NULL,
                                               chamber_in_transd_id numeric NOT NULL,
                                               cd_use varchar(4) NOT NULL,
                                               dt timestamp NOT NULL,
                                               is_member int4 NULL,
                                               company_id uuid NULL,
                                               account_cd varchar(255) NULL,
                                               income_type_id uuid NULL,
                                               amount numeric(19, 2) NULL,
                                               last_updated timestamp NOT NULL,
                                               rec_deleted numeric NOT NULL,
                                               income_pay_method_id uuid NULL,
                                               is_echamber int4 NULL,
                                               block_ser varchar(3) NULL,
                                               is_kratisi numeric NULL,
                                               chamber_comp_id numeric NULL,
                                               chamber_method numeric NULL,
                                               chamber_type numeric NULL,
                                               CONSTRAINT pk_income_transaction PRIMARY KEY (id),
                                               CONSTRAINT uk_income_trans UNIQUE (chamber_id, chamber_in_transd_id, is_kratisi),
                                               CONSTRAINT fk_income_pay_method FOREIGN KEY (income_pay_method_id) REFERENCES pep_schema.income_payment_method(id),
                                               CONSTRAINT fk_income_type FOREIGN KEY (income_type_id) REFERENCES pep_schema.income_type(id)
);

CREATE TABLE pep_schema.municipality_i18n (
                                              municipality_id uuid NOT NULL,
                                              language_id uuid NOT NULL,
                                              description varchar(255) NOT NULL,
                                              date_created timestamp NULL,
                                              last_updated timestamp NULL,
                                              rec_deleted bool DEFAULT FALSE NOT NULL,
                                              chamber_i18n_id int4 NULL,
                                              CONSTRAINT pk_municipality_i18n PRIMARY KEY (municipality_id, language_id),
                                              CONSTRAINT uk_municipality_i18n_chamber UNIQUE (municipality_id, chamber_i18n_id),
                                              CONSTRAINT fk_municipality_i18n FOREIGN KEY (municipality_id) REFERENCES pep_schema.municipality(id),
                                              CONSTRAINT fk_municipality_i18n_lang FOREIGN KEY (language_id) REFERENCES pep_schema.languages(id)
);

CREATE TABLE pep_schema.producti18n (
                                        version int4 NOT NULL,
                                        description varchar(500) NOT NULL,
                                        chamber_i18n_id int8 NOT NULL,
                                        language_id uuid NOT NULL,
                                        product_id uuid NOT NULL,
                                        short_description varchar(35) NULL,
                                        date_created timestamp NOT NULL,
                                        last_updated timestamp NOT NULL,
                                        rec_deleted bool DEFAULT FALSE NOT NULL,
                                        CONSTRAINT pk_producti18n PRIMARY KEY (product_id, language_id),
                                        CONSTRAINT fk_producti18n_language FOREIGN KEY (language_id) REFERENCES pep_schema.languages(id),
                                        CONSTRAINT fk_producti18n_product FOREIGN KEY (product_id) REFERENCES pep_schema.product(id)
);

CREATE TABLE pep_schema.profession (
                                       id uuid NOT NULL,
                                       chamber_id int4 NOT NULL,
                                       chamber_profession_id int4 NULL,
                                       parent_profession_id uuid NULL,
                                       profession_system_id uuid NOT NULL,
                                       code varchar(255) NOT NULL,
                                       date_created timestamp DEFAULT CURRENT_TIMESTAMP NULL,
                                       last_updated timestamp DEFAULT CURRENT_TIMESTAMP NULL,
                                       rec_deleted bool DEFAULT FALSE NOT NULL,
                                       proteas_id numeric(19) NULL,
                                       friendly_cat_id varchar(100) NULL,
                                       CONSTRAINT pk_profession PRIMARY KEY (id),
                                       CONSTRAINT uk_chamber_profession UNIQUE (chamber_id, chamber_profession_id),
                                       CONSTRAINT fk_parent_profession FOREIGN KEY (parent_profession_id) REFERENCES pep_schema.profession(id),
                                       CONSTRAINT fk_prof_system FOREIGN KEY (profession_system_id) REFERENCES pep_schema.profession_system(id),
                                       CONSTRAINT fk_profession_friendly_category FOREIGN KEY (friendly_cat_id) REFERENCES pep_schema.profession_friendly_category(id)
);

CREATE TABLE pep_schema.profession_kindi18n (
                                                profession_kind_id uuid NOT NULL,
                                                language_id uuid NOT NULL,
                                                rec_deleted bool DEFAULT FALSE NOT NULL,
                                                description varchar(255) NOT NULL,
                                                date_created timestamp DEFAULT CURRENT_TIMESTAMP NULL,
                                                last_updated timestamp DEFAULT CURRENT_TIMESTAMP NULL,
                                                chamber_i18n_id int4 NULL,
                                                CONSTRAINT pk_prof_kind_i18n PRIMARY KEY (profession_kind_id, language_id),
                                                CONSTRAINT uk_chamber_prof_kind_i18n UNIQUE (profession_kind_id, chamber_i18n_id),
                                                CONSTRAINT fk_prof_kind_i18n FOREIGN KEY (profession_kind_id) REFERENCES pep_schema.profession_kind(id),
                                                CONSTRAINT fk_prof_kind_i18n_lang FOREIGN KEY (language_id) REFERENCES pep_schema.languages(id)
);

CREATE TABLE pep_schema.professioni18n (
                                           profession_id uuid NOT NULL,
                                           language_id uuid NOT NULL,
                                           rec_deleted bool DEFAULT FALSE NOT NULL,
                                           description varchar(500) NOT NULL,
                                           date_created timestamp DEFAULT CURRENT_TIMESTAMP NULL,
                                           last_updated timestamp DEFAULT CURRENT_TIMESTAMP NULL,
                                           chamber_i18n_id int4 NULL,
                                           CONSTRAINT pk_profession_i18n PRIMARY KEY (profession_id, language_id),
                                           CONSTRAINT uk_chamber_profession_i18n UNIQUE (profession_id, chamber_i18n_id),
                                           CONSTRAINT fk_profession_i18n FOREIGN KEY (profession_id) REFERENCES pep_schema.profession(id),
                                           CONSTRAINT fk_profession_i18n_lang FOREIGN KEY (language_id) REFERENCES pep_schema.languages(id)
);

CREATE TABLE pep_schema.audit_trail (
                                        id uuid NOT NULL,
                                        date_created timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL,
                                        ip varchar(50) NOT NULL,
                                        complete_uri text NULL,
                                        company_id uuid NULL,
                                        profile_id uuid NULL,
                                        CONSTRAINT pk_audit_trail PRIMARY KEY (id)
);

CREATE TABLE pep_schema.ch_app_user_contact (
                                                id uuid NOT NULL,
                                                date_created timestamp DEFAULT CURRENT_TIMESTAMP NULL,
                                                last_updated timestamp DEFAULT CURRENT_TIMESTAMP NULL,
                                                chamber_app_user_id uuid NOT NULL,
                                                phone1 varchar(20) NULL,
                                                phone2 varchar(20) NULL,
                                                mobile1 varchar(20) NULL,
                                                mobile2 varchar(20) NULL,
                                                email1 varchar(100) NULL,
                                                email2 varchar(100) NULL,
                                                url varchar(255) NULL,
                                                zip_code varchar(12) NULL,
                                                latitude varchar(255) NULL,
                                                longitude varchar(255) NULL,
                                                street_number varchar(255) NULL,
                                                rec_deleted bool DEFAULT FALSE NOT NULL,
                                                listing_url varchar(255) NULL,
                                                CONSTRAINT pk_ch_app_user_contact PRIMARY KEY (id)
);

CREATE TABLE pep_schema.ch_app_user_contact_i18n (
                                                     ch_app_user_contact_id uuid NOT NULL,
                                                     language_id uuid NOT NULL,
                                                     date_created timestamp DEFAULT CURRENT_TIMESTAMP NULL,
                                                     last_updated timestamp DEFAULT CURRENT_TIMESTAMP NULL,
                                                     city varchar(50) NULL,
                                                     street varchar(255) NULL,
                                                     rec_deleted bool DEFAULT FALSE NOT NULL,
                                                     CONSTRAINT pk_ch_app_user_contact_i18n PRIMARY KEY (ch_app_user_contact_id, language_id)
);

CREATE TABLE pep_schema.chamber_app_user (
                                             id uuid NOT NULL,
                                             date_created timestamp DEFAULT CURRENT_TIMESTAMP NULL,
                                             last_updated timestamp DEFAULT CURRENT_TIMESTAMP NULL,
                                             chamber_id int4 NOT NULL,
                                             chamber_app_id uuid NOT NULL,
                                             company_id uuid NOT NULL,
                                             rec_deleted bool DEFAULT FALSE NOT NULL,
                                             profile_id uuid NULL,
                                             CONSTRAINT pk_chamber_app_user PRIMARY KEY (id)
);

CREATE TABLE pep_schema.company (
                                    id uuid NOT NULL,
                                    afm varchar(9) NULL,
                                    am numeric NULL,
                                    gemi_id numeric NULL,
                                    co_name varchar(1000) NOT NULL,
                                    chamber_company_id numeric NULL,
                                    chamber_id int4 NOT NULL,
                                    cancel_date timestamp DEFAULT CURRENT_TIMESTAMP NULL,
                                    date_interruption timestamp DEFAULT CURRENT_TIMESTAMP NULL,
                                    member numeric NULL,
                                    rec_type varchar(1) NULL,
                                    rec_deleted bool DEFAULT FALSE NULL,
                                    address_city varchar(50) NULL,
                                    address_latitude varchar(255) NULL,
                                    address_longitude varchar(255) NULL,
                                    address_municipality_pri_id uuid NULL,
                                    address_region varchar(50) NULL,
                                    address_street varchar(255) NULL,
                                    address_street_number varchar(255) NULL,
                                    address_zip_code varchar(12) NULL,
                                    branch_type_id numeric NULL,
                                    chamber_department_id uuid NULL,
                                    chamber_gemi_responsible_id numeric NULL,
                                    co_name_nrm varchar(1000) NULL,
                                    company_status_id uuid NULL,
                                    contact_email varchar(100) NULL,
                                    contact_mobile varchar(50) NULL,
                                    contact_phone1 varchar(20) NULL,
                                    contact_phone2 varchar(20) NULL,
                                    contact_url varchar(100) NULL,
                                    corporate_status_id uuid NULL,
                                    date_created timestamp NULL,
                                    last_updated timestamp NULL,
                                    date_incorporated timestamp NULL,
                                    date_registered timestamp NULL,
                                    gemi_number varchar(12) NULL,
                                    objective text NULL,
                                    receive_newsletter bool NULL,
                                    is_chamber_company bool DEFAULT true NOT NULL,
                                    is_trades_company bool DEFAULT false NOT NULL,
                                    show_business_guide bool DEFAULT true NOT NULL,
                                    business_location_id uuid NULL,
                                    has_active_profiles bool DEFAULT false NULL,
                                    is_proteas_data bool DEFAULT false NULL,
                                    responsible_name varchar(255) NULL,
                                    address_country_id uuid NULL,
                                    address_zoom_level numeric NULL,
                                    contact_phone3 varchar(20) NULL,
                                    date_profession_started timestamp NULL,
                                    foundation_date timestamp NULL,
                                    me_criteria1_id numeric NULL,
                                    me_criteria2_id numeric NULL,
                                    me_criteria3_id numeric NULL,
                                    member_dues timestamp NULL,
                                    jb_uuid uuid NULL,
                                    jb_description text NULL,
                                    jb_number_employees int8 NULL,
                                    jb_motto varchar(255) NULL,
                                    jb_email varchar(255) NULL,
                                    jb_linked_in_url varchar(255) NULL,
                                    jb_facebook_url varchar(255) NULL,
                                    jb_registration_status varchar(255) NULL,
                                    jb_logo_id varchar(255) NULL,
                                    jb_cover_id varchar(255) NULL,
                                    jb_location_id int4 NULL,
                                    jb_industry_id int4 NULL,
                                    jb_isvalid bool DEFAULT false NULL,
                                    jb_activation_status varchar NULL,
                                    CONSTRAINT company_pkey PRIMARY KEY (id),
                                    CONSTRAINT uk_company UNIQUE (chamber_id, chamber_company_id)
);

CREATE TABLE pep_schema.company_article_file (
                                                 id uuid NOT NULL,
                                                 article_id uuid NOT NULL,
                                                 file_id uuid NOT NULL,
                                                 order_seq int4 DEFAULT 0 NOT NULL,
                                                 date_created timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL,
                                                 last_updated timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL,
                                                 CONSTRAINT company_article_file_pkey PRIMARY KEY (id)
);

CREATE TABLE pep_schema.company_bg_cooperation (
                                                   id uuid NOT NULL,
                                                   chamber_id int4 NULL,
                                                   company_id uuid NOT NULL,
                                                   coop_company_id uuid NOT NULL,
                                                   date_created timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL,
                                                   last_updated timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL,
                                                   rec_deleted bool DEFAULT FALSE NOT NULL,
                                                   cooperation_status varchar(50) NULL,
                                                   CONSTRAINT company_bg_cooperation_pkey PRIMARY KEY (id)
);

CREATE TABLE pep_schema.company_bg_cooperation_i18n (
                                                        id uuid NOT NULL,
                                                        description varchar(1000) NULL,
                                                        date_created timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL,
                                                        last_updated timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL,
                                                        rec_deleted bool DEFAULT FALSE NOT NULL,
                                                        cooperation_id uuid NOT NULL,
                                                        language_id uuid NOT NULL,
                                                        CONSTRAINT company_bg_cooperation_i18n_pkey PRIMARY KEY (id)
);

CREATE TABLE pep_schema.company_favourites (
                                               id uuid NOT NULL,
                                               company_id uuid NOT NULL,
                                               favourite_company_id uuid NOT NULL,
                                               date_created timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL,
                                               last_updated timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL,
                                               notes text NULL,
                                               favourite_profile_id uuid NULL,
                                               CONSTRAINT company_favourites_pkey PRIMARY KEY (id)
);

CREATE TABLE pep_schema.company_file (
                                         id uuid NOT NULL,
                                         chamber_id int4 NULL,
                                         file_name varchar(100) NOT NULL,
                                         file_size int4 NOT NULL,
                                         blob_uri varchar(2000) NOT NULL,
                                         order_seq int4 NOT NULL,
                                         rec_deleted bool NULL,
                                         date_created timestamp DEFAULT  NOT NULL,
                                         last_updated timestamp DEFAULT  NOT NULL,
                                         company_id uuid NOT NULL,
                                         language_id uuid NULL,
                                         is_logo bool NULL,
                                         is_background bool DEFAULT false NULL,
                                         company_profile_id uuid NULL,
                                         is_embedded bool DEFAULT false NOT NULL,
                                         CONSTRAINT company_file_pkey PRIMARY KEY (id)
);

CREATE TABLE pep_schema.company_profession (
                                               id uuid DEFAULT NOT NULL,
                                               chamber_id int4 NOT NULL,
                                               chamber_company_profession_id int4 NULL,
                                               company_id uuid NOT NULL,
                                               profession_id uuid NOT NULL,
                                               profession_kind_id uuid NULL,
                                               date_created timestamp NULL,
                                               from_date timestamp NULL,
                                               last_updated timestamp NULL,
                                               rec_deleted bool DEFAULT FALSE NOT NULL,
                                               to_date timestamp NULL,
                                               profile_id uuid NULL,
                                               gemi_id numeric NULL,
                                               gemi_date_created timestamp NULL,
                                               gemi_last_updated timestamp NULL,
                                               CONSTRAINT company_profession_pkey PRIMARY KEY (id),
                                               CONSTRAINT uk_chamber_company_profession UNIQUE (chamber_id, chamber_company_profession_id)
);

CREATE TABLE pep_schema.company_profile (
                                            id uuid NOT NULL,
                                            name varchar(1000) NOT NULL,
                                            address_city varchar(50) NULL,
                                            address_latitude varchar(255) NULL,
                                            address_longitude varchar(255) NULL,
                                            address_region varchar(50) NULL,
                                            address_street varchar(255) NULL,
                                            address_street_number varchar(255) NULL,
                                            address_zip_code varchar(12) NULL,
                                            contact_email varchar(100) NULL,
                                            contact_mobile varchar(50) NULL,
                                            contact_phone1 varchar(20) NULL,
                                            contact_phone2 varchar(20) NULL,
                                            contact_phone3 varchar(20) NULL,
                                            contact_url varchar(100) NULL,
                                            date_created timestamp NULL,
                                            last_updated timestamp NULL,
                                            business_location_id uuid NULL,
                                            company_id uuid NOT NULL,
                                            rec_deleted bool DEFAULT FALSE NULL,
                                            show_business_guide bool DEFAULT true NULL,
                                            CONSTRAINT company_profile_pkey PRIMARY KEY (id)
);

CREATE TABLE pep_schema.company_profile_i18n (
                                                 company_profile_id uuid NOT NULL,
                                                 language_id uuid NOT NULL,
                                                 rec_deleted bool NOT NULL,
                                                 name varchar(1000) NULL,
                                                 address_city varchar(50) NULL,
                                                 address_region varchar(50) NULL,
                                                 address_street varchar(100) NULL,
                                                 date_created timestamp DEFAULT CURRENT_TIMESTAMP NULL,
                                                 last_updated timestamp DEFAULT CURRENT_TIMESTAMP NULL,
                                                 objective text NULL,
                                                 CONSTRAINT company_profile_i18n_pkey PRIMARY KEY (company_profile_id, language_id)
);

CREATE TABLE pep_schema.company_title (
                                          id uuid DEFAULT  NOT NULL,
                                          chamber_id int4 NOT NULL,
                                          title varchar(1000) NULL,
                                          chamber_title_id numeric NULL,
                                          company_id uuid NOT NULL,
                                          date_created timestamp NOT NULL,
                                          last_updated timestamp NOT NULL,
                                          rec_deleted bool DEFAULT FALSE NOT NULL,
                                          CONSTRAINT pk_company_title PRIMARY KEY (id),
                                          CONSTRAINT uk_chamber_company_title UNIQUE (chamber_id, chamber_title_id)
);

CREATE TABLE pep_schema.company_titlei18n (
                                              company_title_id uuid NOT NULL,
                                              language_id uuid NOT NULL,
                                              title varchar(1000) NULL,
                                              date_created timestamp NULL,
                                              last_updated timestamp NULL,
                                              chamber_i18n_id int4 NOT NULL,
                                              rec_deleted bool DEFAULT FALSE NOT NULL,
                                              CONSTRAINT pk_company_titlei18n PRIMARY KEY (company_title_id, language_id, chamber_i18n_id),
                                              CONSTRAINT uk_chamber_co_title_i18n UNIQUE (company_title_id, chamber_i18n_id)
);

CREATE TABLE pep_schema.company_yp_article (
                                               id uuid NOT NULL,
                                               chamber_id int4 NULL,
                                               company_id uuid NOT NULL,
                                               title text NULL,
                                               html text NULL,
                                               language_id uuid NULL,
                                               order_seq int4 DEFAULT 0 NOT NULL,
                                               date_created timestamp NOT NULL,
                                               last_updated timestamp NOT NULL,
                                               rec_deleted bool DEFAULT FALSE NOT NULL,
                                               is_published bool DEFAULT FALSE NOT NULL,
                                               company_profile_id uuid NOT NULL,
                                               CONSTRAINT company_yp_article_un UNIQUE (company_id, title),
                                               CONSTRAINT pk_company_article PRIMARY KEY (id)
);

CREATE TABLE pep_schema.company_yp_article_i18n (
                                                    company_article_id uuid NOT NULL,
                                                    title text NULL,
                                                    html text NULL,
                                                    language_id uuid NOT NULL,
                                                    date_created timestamp NOT NULL,
                                                    last_updated timestamp NOT NULL,
                                                    rec_deleted bool DEFAULT FALSE NOT NULL,
                                                    CONSTRAINT pk_company_article_i18n PRIMARY KEY (company_article_id, language_id)
);

CREATE TABLE pep_schema.company_yp_file (
                                            id uuid NOT NULL,
                                            chamber_id int4 NOT NULL,
                                            file_name varchar(100) NOT NULL,
                                            mime_type varchar(100) NOT NULL,
                                            file_size int4 NOT NULL,
                                            title varchar NULL,
                                            order_seq int4 NOT NULL,
                                            rec_deleted bool DEFAULT FALSE NULL,
                                            date_created timestamp NOT NULL,
                                            last_updated timestamp NOT NULL,
                                            company_id uuid NOT NULL,
                                            language_id uuid NOT NULL,
                                            blob_uri varchar(2000) NULL,
                                            CONSTRAINT company_yp_file_pkey PRIMARY KEY (id)
);

CREATE TABLE pep_schema.company_yp_photo (
                                             id uuid NOT NULL,
                                             chamber_id int4 NOT NULL,
                                             company_id uuid NOT NULL,
                                             file_name varchar(100) NOT NULL,
                                             mime_type varchar(100) NOT NULL,
                                             file_size int4 DEFAULT 0 NOT NULL,
                                             is_logo bool DEFAULT false NOT NULL,
                                             blob_uri varchar(2000) NOT NULL,
                                             order_seq int4 DEFAULT 0 NOT NULL,
                                             date_created timestamp NOT NULL,
                                             last_updated timestamp NOT NULL,
                                             rec_deleted bool DEFAULT FALSE NOT NULL,
                                             CONSTRAINT company_yp_photo_un UNIQUE (company_id, file_name),
                                             CONSTRAINT pk_company_photo PRIMARY KEY (id)
);

CREATE TABLE pep_schema.companyi18n (
                                        company_id uuid NOT NULL,
                                        language_id uuid NOT NULL,
                                        chamber_i18n_id int4 NOT NULL,
                                        city varchar(50) NULL,
                                        co_name varchar(1000) NULL,
                                        date_created timestamp NULL,
                                        last_updated timestamp NULL,
                                        objective text NULL,
                                        rec_deleted bool DEFAULT FALSE NOT NULL,
                                        street varchar(60) NULL,
                                        responsible_name varchar(255) NULL,
                                        CONSTRAINT pk_companyi18n PRIMARY KEY (company_id, language_id, chamber_i18n_id),
                                        CONSTRAINT uk_company_chamber_i18n UNIQUE (company_id, chamber_i18n_id)
);

CREATE TABLE pep_schema.export_comp_prod_country (
                                                     id uuid DEFAULT NOT NULL,
                                                     export_company_id uuid NOT NULL,
                                                     country_id uuid NOT NULL,
                                                     date_created timestamp NOT NULL,
                                                     last_updated timestamp NOT NULL,
                                                     rec_deleted bool DEFAULT FALSE NOT NULL,
                                                     exp_year int4 NOT NULL,
                                                     product_id uuid NULL,
                                                     CONSTRAINT export_comp_prod_country_pkey PRIMARY KEY (id),
                                                     CONSTRAINT export_comp_prod_country_year_check CHECK (((exp_year >= 1800) AND (exp_year <= 2100)))
);

CREATE TABLE pep_schema.export_company (
                                           id uuid DEFAULT NOT NULL,
                                           date_created timestamp NOT NULL,
                                           last_updated timestamp NOT NULL,
                                           company_id uuid NOT NULL,
                                           active bool DEFAULT FALSE NULL,
                                           eme_code int8 NOT NULL,
                                           CONSTRAINT export_company_pkey PRIMARY KEY (id)
);

CREATE TABLE pep_schema.status_history (
                                           id uuid DEFAULT NOT NULL,
                                           chamber_id int4 NOT NULL,
                                           chamber_status_history_id numeric NOT NULL,
                                           company_id uuid NOT NULL,
                                           company_status_id uuid NOT NULL,
                                           date_created timestamp NOT NULL,
                                           last_updated timestamp NOT NULL,
                                           notes varchar(255) NULL,
                                           reg_dt timestamp NOT NULL,
                                           start_dt timestamp NOT NULL,
                                           rec_deleted bool NOT NULL,
                                           gemi_id numeric NULL,
                                           gemi_date_created timestamp NULL,
                                           gemi_last_updated timestamp NULL,
                                           action_no varchar(50) NULL,
                                           CONSTRAINT pk_status_history PRIMARY KEY (id),
                                           CONSTRAINT uk_chamber_status_history UNIQUE (chamber_id, chamber_status_history_id)
);

CREATE TABLE pep_schema.user_contactinfo (
                                             id uuid DEFAULT NOT NULL,
                                             chamber_id int4 NULL,
                                             company_id uuid NOT NULL,
                                             username text NOT NULL,
                                             email varchar(100) NULL,
                                             phone varchar(20) NULL,
                                             mobile varchar(50) NULL,
                                             contact_url varchar(255) NULL,
                                             date_created timestamp NOT NULL,
                                             last_updated timestamp NOT NULL,
                                             CONSTRAINT user_contactinfo_pkey PRIMARY KEY (id)
);

CREATE TABLE pep_schema.user_geodata (
                                         id uuid DEFAULT NOT NULL,
                                         chamber_id int4 NULL,
                                         company_id uuid NOT NULL,
                                         username text NOT NULL,
                                         latitude varchar(255) NOT NULL,
                                         longitude varchar(255) NOT NULL,
                                         date_created timestamp NOT NULL,
                                         last_updated timestamp NOT NULL,
                                         CONSTRAINT user_geodata_pkey PRIMARY KEY (id)
);

CREATE TABLE pep_schema.working_hours (
                                          id int8  NOT NULL,
                                          company_id uuid NOT NULL,
                                          day_of_week varchar(20) NOT NULL,
                                          opening_time time NULL,
                                          closing_time time NULL,
                                          is_closed bool DEFAULT false NOT NULL,
                                          profile_id uuid NULL,
                                          CONSTRAINT working_hours_pkey PRIMARY KEY (id)
);
