CREATE SCHEMA pms;


CREATE TABLE pms.patients
(
  id                             uuid,
  patient_no                     character varying,
  lastname                       character varying,
  firstname                      character varying,
  middlename                     character varying,
  name_suffix                    character varying,
  address                        character varying,
  country                        character varying,
  state_province                 character varying,
  city_municipality              character varying,
  barangay                       character varying,
  gender                         character varying,
  dob                            date,
  allergies                      character varying,
  father                         character varying,
  mother                         character varying,
  father_occupation              character varying,
  mother_occupation              character varying,
  emergency_contact_name         character varying,
  emergency_contact_address      character varying,
  emergency_contact_relationship character varying,
  emegency_contact_no            character varying,
  guarantor_name                 character varying,
  guarantor_address              character varying,
  guarantor_relationship         character varying,
  guarantor_contact_no           character varying,
  PRIMARY KEY (id)
);