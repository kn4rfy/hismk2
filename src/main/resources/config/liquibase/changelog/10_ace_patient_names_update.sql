ALTER TABLE "pms"."patients"
  RENAME COLUMN "firstname" TO "first_name";

ALTER TABLE "pms"."patients"
  RENAME COLUMN "lastname" TO "last_name";

ALTER TABLE "pms"."patients"
  RENAME COLUMN "middlename" TO "middle_name";

ALTER TABLE "pms"."patients"
  RENAME COLUMN "emegency_contact_no" TO "emergency_contact_no";
