ALTER TABLE "pms"."patients"
    DROP COLUMN	"firstname",
    DROP COLUMN	"lastname",
    DROP COLUMN	"middlename",
    DROP COLUMN "emegency_contact_no",
    ADD COLUMN "last_name" character varying,
    ADD COLUMN "first_name" character varying,
    ADD COLUMN "middle_name" character varying,
    ADD COLUMN "emergency_contact_no" character varying;
