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

 Date: 04/11/2025 16:53:02
*/


-- ----------------------------
-- Table structure for daily_itineraries
-- ----------------------------
DROP TABLE IF EXISTS "public"."daily_itineraries";
CREATE TABLE "public"."daily_itineraries" (
  "id" int8 NOT NULL DEFAULT nextval('daily_itineraries_id_seq'::regclass),
  "travel_plan_id" int8 NOT NULL,
  "day_number" int4 NOT NULL,
  "accommodation_place" varchar(255) COLLATE "pg_catalog"."default",
  "accommodation_budget" varchar(100) COLLATE "pg_catalog"."default",
  "accommodation_content" text COLLATE "pg_catalog"."default",
  "created_at" timestamp(6) DEFAULT CURRENT_TIMESTAMP
)
;

-- ----------------------------
-- Uniques structure for table daily_itineraries
-- ----------------------------
ALTER TABLE "public"."daily_itineraries" ADD CONSTRAINT "daily_itineraries_travel_plan_id_day_number_key" UNIQUE ("travel_plan_id", "day_number");

-- ----------------------------
-- Primary Key structure for table daily_itineraries
-- ----------------------------
ALTER TABLE "public"."daily_itineraries" ADD CONSTRAINT "daily_itineraries_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Foreign Keys structure for table daily_itineraries
-- ----------------------------
ALTER TABLE "public"."daily_itineraries" ADD CONSTRAINT "daily_itineraries_travel_plan_id_fkey" FOREIGN KEY ("travel_plan_id") REFERENCES "public"."travel_plans" ("id") ON DELETE CASCADE ON UPDATE NO ACTION;
