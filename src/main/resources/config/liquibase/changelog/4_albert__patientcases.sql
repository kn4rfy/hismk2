

CREATE TABLE pms.patient_cases
(
  id uuid,
  case_no character varying,
  service_type character varying,
  accomodation_type character varying,
  registry_type character varying,
  entry_datetime timestamp without time zone,
  admission_datetime timestamp without time zone,
  discharged_datetime timestamp without time zone,
  maygohome_datetime timestamp without time zone,
  admitting_diagnosis character varying,
  discharge_diagnosis character varying,
  history_present_illness character varying,
  pertinent_medical_history character varying,
  preop_diagnosis character varying,
  postop_diagnosis character varying,
  surgical_procedure character varying,
  case_informant character varying,
  case_informant_relation character varying,
  case_informant_address character varying,
  patient uuid,
  PRIMARY KEY (id),
  CONSTRAINT fk_patient_case_patient FOREIGN KEY (patient)
  REFERENCES pms.patients (id) MATCH SIMPLE
  ON UPDATE CASCADE
  ON DELETE RESTRICT
);


ALTER TABLE "pms"."patient_cases"
  ADD COLUMN "created_by" varchar(50)  NULL COLLATE "default",
ADD COLUMN	"created_date" timestamp(6)  NULL DEFAULT current_timestamp,
ADD COLUMN	"last_modified_by" varchar(50) COLLATE "default",
ADD COLUMN	"last_modified_date" timestamp(6) NULL DEFAULT current_timestamp;