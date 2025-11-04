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

 Date: 04/11/2025 16:52:55
*/


-- ----------------------------
-- Table structure for travel_plans
-- ----------------------------
DROP TABLE IF EXISTS "public"."travel_plans";
CREATE TABLE "public"."travel_plans" (
  "id" int8 NOT NULL DEFAULT nextval('travel_plans_id_seq'::regclass),
  "user_id" int8 NOT NULL,
  "destination" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  "duration" varchar(100) COLLATE "pg_catalog"."default" NOT NULL,
  "budget" varchar(100) COLLATE "pg_catalog"."default" NOT NULL,
  "companions" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  "preferences" text COLLATE "pg_catalog"."default",
  "created_at" timestamp(6) DEFAULT CURRENT_TIMESTAMP,
  "updated_at" timestamp(6) DEFAULT CURRENT_TIMESTAMP
)
;

-- ----------------------------
-- Primary Key structure for table travel_plans
-- ----------------------------
ALTER TABLE "public"."travel_plans" ADD CONSTRAINT "travel_plans_pkey" PRIMARY KEY ("id");
