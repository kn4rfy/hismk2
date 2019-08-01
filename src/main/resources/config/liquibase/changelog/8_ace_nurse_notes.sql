CREATE TABLE pms.patient_nurse_notes
(
  id                 uuid,
  entry_datetime     timestamp    NULL DEFAULT current_timestamp,

  focus              character varying,
  data               character varying,
  action             character varying,
  response           character varying,
  note               character varying,
  patient_case       uuid,

  created_by         varchar(50)  NULL COLLATE "default",
  created_date       timestamp(6) NULL DEFAULT current_timestamp,
  last_modified_by   varchar(50) COLLATE "default",
  last_modified_date timestamp(6) NULL DEFAULT current_timestamp,


  PRIMARY KEY (id),
  CONSTRAINT fk_patient_vitalsigns_patient_cases FOREIGN KEY (patient_case)
    REFERENCES pms.patient_cases (id) MATCH SIMPLE
    ON UPDATE CASCADE
    ON DELETE RESTRICT
);