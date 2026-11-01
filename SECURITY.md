# Security Policy

## Scope

Course Recovery System is an academic portfolio application. It demonstrates session authentication, role-based route protection, JDBC persistence, password-reset workflows, audit logging, and server-side rendering. It has not undergone a production security certification or penetration test.

## Reporting a Vulnerability

Please report suspected vulnerabilities privately to the repository owner through the contact options on the [ALMAFLEHI GitHub profile](https://github.com/ALMAFLEHI). Do not publish credentials, personal data, or exploit details in a public issue.

Include:

- the affected route or component
- steps required to reproduce the issue
- the expected and observed behaviour
- the likely impact
- any suggested mitigation

## Credential Handling

- Database credentials must be provided through `CRS_DB_URL`, `CRS_DB_USERNAME`, and `CRS_DB_PASSWORD` environment variables.
- Do not commit `.env`, `.properties`, server configuration, database exports, or IDE launch files containing private values.
- Demonstration accounts in SQL seed files are for local evaluation only and must be changed before shared deployment.
- Use a dedicated, least-privilege MySQL account rather than a database administrator account outside local development.

## Production Hardening

Before real institutional use:

1. Migrate password hashes from plain SHA-256 to Argon2id, BCrypt, or PBKDF2.
2. Add CSRF protection to all state-changing forms.
3. Enforce HTTPS and secure, HTTP-only, same-site session cookies.
4. Add security headers, request-size limits, rate limiting, and account lockout controls.
5. Use a managed secret store and rotate all deployment credentials.
6. Add automated security, authorization, integration, and dependency tests.
7. Review audit logs for sensitive-data exposure and establish retention rules.
8. Use connection pooling and a restricted production database account.

## Supported Version

Security improvements are applied to the latest commit on the `main` branch.
