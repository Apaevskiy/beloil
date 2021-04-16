-- CREATE DATABASE beloil;
drop table if exists public.mobilecatalog;
drop table if exists public.departments;

CREATE TABLE IF NOT EXISTS public.departments
(
    departmentsId SERIAL PRIMARY KEY,
    name text NOT NULL
);
CREATE TABLE IF NOT EXISTS public.mobilecatalog
(
    id SERIAL PRIMARY KEY,
    fullName  text NOT NULL,
    position text NOT NULL,
    departmentId INT REFERENCES public.departments (departmentsId),
    serviceNumber text[] NOT NULL ,
    personalPhoneNumber text[] NOT NULL ,
    serviceMobilePhoneNumber text[] NOT NULL
);
