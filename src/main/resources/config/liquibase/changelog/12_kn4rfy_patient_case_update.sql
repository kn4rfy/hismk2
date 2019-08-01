ALTER TABLE "pms"."patient_cases"
  RENAME COLUMN "accomodation_type" TO "accommodation_type";

ALTER TABLE "pms"."patient_cases"
  RENAME COLUMN "maygohome_datetime" TO "may_go_home_datetime";

ALTER TABLE "pms"."patient_cases"
  RENAME COLUMN "preop_diagnosis" TO "pre_op_diagnosis";

ALTER TABLE "pms"."patient_cases"
  RENAME COLUMN "postop_diagnosis" TO "post_op_diagnosis";
