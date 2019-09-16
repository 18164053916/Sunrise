/*
Navicat PGSQL Data Transfer

Source Server         : ticket-center
Source Server Version : 90506
Source Host           : localhost:5432
Source Database       : postgres
Source Schema         : public

Target Server Type    : PGSQL
Target Server Version : 90506
File Encoding         : 65001

Date: 2018-03-04 20:46:13
*/


-- ----------------------------
-- Table structure for t_student
-- ----------------------------
DROP TABLE IF EXISTS "public"."t_student";
CREATE TABLE "public"."t_student" (
"id" varchar(40) NOT NULL,
"student_name" varchar(1000) COLLATE "default" NOT NULL,
"student_level" int2 DEFAULT 0 NOT NULL,
"student_age" int8 NOT NULL,
"student_gender" int2 DEFAULT 0 NOT NULL,
"student_info" varchar(1000),
"create_date" timestamp(0) ,
"update_date" timestamp(0)
)
WITH (OIDS=FALSE)

;
COMMENT ON COLUMN "public"."t_student"."id" IS '主键ID';
COMMENT ON COLUMN "public"."t_student"."student_name" IS '学员名称';
COMMENT ON COLUMN "public"."t_student"."student_level" IS '学员类型(0:小学；1：幼儿)';
COMMENT ON COLUMN "public"."t_student"."student_age" IS '学员年龄';
COMMENT ON COLUMN "public"."t_student"."student_gender" IS '学员性别(0:男孩子；1：女孩子)';
COMMENT ON COLUMN "public"."t_student"."student_info" IS '学员备注';
COMMENT ON COLUMN "public"."t_student"."create_date" IS '新增数据日期';
COMMENT ON COLUMN "public"."t_student"."update_date" IS '修改数据日期';

ALTER TABLE "public"."t_student" ADD UNIQUE ("student_name");

/*
Navicat PGSQL Data Transfer

Source Server         : localhost
Source Server Version : 90506
Source Host           : localhost:5432
Source Database       : postgres
Source Schema         : public

Target Server Type    : PGSQL
Target Server Version : 90506
File Encoding         : 65001

Date: 2018-03-05 14:14:21
*/


-- ----------------------------
-- Table structure for t_class
-- ----------------------------
DROP TABLE IF EXISTS "public"."t_class";
CREATE TABLE "public"."t_class" (
"id" varchar(40) NOT NULL,
"class_level" int2 NOT NULL,
"class_name" varchar(64) NOT NULL,
"class_info" varchar(1000) COLLATE "default",
"create_date" timestamp(0),
"update_date" timestamp(0)
)
WITH (OIDS=FALSE)

;
COMMENT ON COLUMN "public"."t_class"."id" IS '主键';
COMMENT ON COLUMN "public"."t_class"."class_level" IS '班级类型(0:小学；1：幼儿)';
COMMENT ON COLUMN "public"."t_class"."class_name" IS '班级名称';
COMMENT ON COLUMN "public"."t_class"."class_info" IS '班级备注';
COMMENT ON COLUMN "public"."t_class"."create_date" IS '新增数据日期';
COMMENT ON COLUMN "public"."t_class"."update_date" IS '修改数据日期';

ALTER TABLE "public"."t_class" ADD UNIQUE ("class_name");
-- ----------------------------
-- Table structure for t_teacher
-- ----------------------------
DROP TABLE IF EXISTS "public"."t_teacher";
CREATE TABLE "public"."t_teacher" (
"id" varchar(40) NOT NULL,
"teacher_type" int2 NOT NULL,
"teacher_name" varchar(64) NOT NULL,
"teacher_mobile" varchar(32),
"teacher_info" varchar(1000),
"create_date" timestamp(0),
"update_date" timestamp(0)
)
WITH (OIDS=FALSE)

;
COMMENT ON COLUMN "public"."t_teacher"."id" IS '主键';
COMMENT ON COLUMN "public"."t_teacher"."teacher_type" IS '教师种类(0:外教；1：中教)';
COMMENT ON COLUMN "public"."t_teacher"."teacher_name" IS '教师名称';
COMMENT ON COLUMN "public"."t_teacher"."teacher_info" IS '教师备注';
COMMENT ON COLUMN "public"."t_teacher"."teacher_mobile" IS '教师手机';
COMMENT ON COLUMN "public"."t_teacher"."create_date" IS '新增数据日期';
COMMENT ON COLUMN "public"."t_teacher"."update_date" IS '修改数据日期';

ALTER TABLE "public"."t_teacher" ADD UNIQUE ("teacher_name");
-- ----------------------------
-- Table structure for t_attendance
-- ----------------------------
DROP TABLE IF EXISTS "public"."t_attendance";
CREATE TABLE "public"."t_attendance" (
"id" varchar(40) NOT NULL,
"teacher_id" varchar(40),
"class_id" varchar(40),
"start_time" timestamp(0),
"end_time" timestamp(0),
"create_date" timestamp(0),
"attendance_info" varchar(1000)
)
WITH (OIDS=FALSE)

;
COMMENT ON COLUMN "public"."t_attendance"."id" IS '主键';
COMMENT ON COLUMN "public"."t_attendance"."teacher_id" IS '教师ID';
COMMENT ON COLUMN "public"."t_attendance"."class_id" IS '班级ID';
COMMENT ON COLUMN "public"."t_attendance"."start_time" IS '出勤开始时间';
COMMENT ON COLUMN "public"."t_attendance"."end_time" IS '出勤结束时间';
COMMENT ON COLUMN "public"."t_attendance"."create_date" IS '新增数据日期';
COMMENT ON COLUMN "public"."t_attendance"."attendance_info" IS '出勤备注';

DROP TABLE IF EXISTS "public"."t_attendance_relation";
CREATE TABLE "public"."t_attendance_relation" (
"id" varchar(40) NOT NULL,
"student_id" varchar(40) NOT NULL,
"attendance_id" varchar(40)
)
WITH (OIDS=FALSE)

;
COMMENT ON COLUMN "public"."t_attendance_relation"."id" IS '主键';
COMMENT ON COLUMN "public"."t_attendance_relation"."student_id" IS '学员ID';
COMMENT ON COLUMN "public"."t_attendance_relation"."attendance_id" IS '出勤ID';

