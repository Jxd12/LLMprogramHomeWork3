/*
 Navicat Premium Dump SQL

 Source Server         : LLMHomework
 Source Server Type    : PostgreSQL
 Source Server Version : 180000 (180000)
 Source Host           : localhost:5432
 Source Catalog        : postgres
 Source Schema         : public

 Target Server Type    : PostgreSQL
 Target Server Version : 180000 (180000)
 File Encoding         : 65001

 Date: 04/11/2025 16:53:11
*/


-- ----------------------------
-- Table structure for activities
-- ----------------------------
DROP TABLE IF EXISTS "public"."activities";
CREATE TABLE "public"."activities" (
  "id" int8 NOT NULL DEFAULT nextval('activities_id_seq'::regclass),
  "daily_itinerary_id" int8 NOT NULL,
  "activity_type" varchar(20) COLLATE "pg_catalog"."default" NOT NULL,
  "place" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  "budget" varchar(100) COLLATE "pg_catalog"."default" NOT NULL,
  "content" text COLLATE "pg_catalog"."default",
  "transport" varchar(255) COLLATE "pg_catalog"."default",
  "created_at" timestamp(6) DEFAULT CURRENT_TIMESTAMP
)
;

-- ----------------------------
-- Primary Key structure for table activities
-- ----------------------------
ALTER TABLE "public"."activities" ADD CONSTRAINT "activities_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Foreign Keys structure for table activities
-- ----------------------------
ALTER TABLE "public"."activities" ADD CONSTRAINT "activities_daily_itinerary_id_fkey" FOREIGN KEY ("daily_itinerary_id") REFERENCES "public"."daily_itineraries" ("id") ON DELETE CASCADE ON UPDATE NO ACTION;
