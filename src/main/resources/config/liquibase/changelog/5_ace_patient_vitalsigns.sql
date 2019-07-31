CREATE TABLE pms.patient_vitalsigns
(
  id uuid,
  systolic character varying,
  diastolic character varying,
  temperature character varying,
  pulse_rate character varying,
  respiratory_rate character varying,
  oxygen_saturation character varying,
  patient_case uuid,

  created_by varchar(50) NULL COLLATE "default",
  created_date timestamp(6) NULL DEFAULT current_timestamp,
  last_modified_by varchar(50) COLLATE "default",
  last_modified_date timestamp(6) NULL DEFAULT current_timestamp,


  PRIMARY KEY (id),
  CONSTRAINT fk_patient_vitalsigns_patient_cases FOREIGN KEY (patient_case)
  REFERENCES pms.patient_cases (id) MATCH SIMPLE
  ON UPDATE CASCADE
  ON DELETE RESTRICT
);