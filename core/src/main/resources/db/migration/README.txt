Add flyway migration files in format V{identifier}__Name.sql

Identifier is in form major_minor_micro.

Run mvn compile before running any flyway commands.

Run mvn flyway:info for migration status.