ALTER TABLE "pms"."patient_vitalsigns"
    DROP COLUMN "entry_datetime",
    ADD COLUMN	"entry_datetime" timestamp NULL DEFAULT current_timestamp;