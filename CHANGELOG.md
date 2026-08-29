# Changelog

## [1.0.0] - 2026-08-29

### Features
- add GET /all, /by-ids, /stats and admin-delete endpoints
- add JavaDoc, health aliases, and component tests

### Bug Fixes
- resolve ambiguous mapping on GET /api/store and POST /api/store/upload
- add missing AiConfig/MailTemplate DTOs, entities, services and migration
- add GET /api/store, domain-mapping list, and upload endpoints
- add missing UpdateDomainRequest and UpdatePublishRequest DTOs
- scope page list to merchant store and inject storeId on create
- change Store.id type from String to UUID to match uuid DB column
- use @JdbcTypeCode(JSON) on branding field to handle jsonb column type
- remove liquibase default-schema to allow fresh DB bootstrap
- update controller @RequestMapping paths to match gateway routes
- run create-schema always so it recreates if missing
- accept any checksum for idempotent create-schema changeset
- limit HikariCP pool to 2 connections (db-f1-micro max 25 total)
- disable Hibernate validation (Liquibase owns schema, uuid vs String mismatch)
- set liquibase-schema=public so schema is created before tracking tables
- add Cloud SQL postgres-socket-factory for Cloud Run connectivity

### Documentation
- add complete project documentation

### CI/Build
- retrigger prod deploy
- retrigger after db-g1-small upgrade
- trigger first dev build
- use separate GCP project IDs for dev (digi-carts-dev) and prod (digi-carts)