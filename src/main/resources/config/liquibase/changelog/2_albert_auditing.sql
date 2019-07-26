ALTER TABLE "pms"."patients"
  ADD COLUMN "created_by" varchar(50)  NULL COLLATE "default",
ADD COLUMN	"created_date" timestamp(6)  NULL DEFAULT current_timestamp,
ADD COLUMN	"last_modified_by" varchar(50) COLLATE "default",
ADD COLUMN	"last_modified_date" timestamp(6) NULL DEFAULT current_timestamp;