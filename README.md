# Aristotle Education Platform
![Staging Deploy](https://github.com/luscinia-enterprises/aristotle/workflows/Build%20and%20deploy%20JAR%20app%20to%20Azure%20Web%20App%20-%20aristotleapp(staging)/badge.svg)
![CodeQL](https://github.com/luscinia-enterprises/aristotle/workflows/CodeQL/badge.svg)

![Aristotle Uptime](https://img.shields.io/uptimerobot/ratio/m780026806-56b4aef568b9f266dd4439dd?style=plastic): [https://aristotle.luscinia.ca](https://aristotle.luscinia.ca)

![Docker Build](https://img.shields.io/docker/cloud/build/lusciniaservices/aristotleapp?style=plastic)

## Overview

The Aristotle Education platform is a project currently under development that aims to provide a interactive education experience using machine learning to help teachers access deeper and more relevant insights into their students' education.

### Timeline

We are currently working towards a 2021 Alpha release beginning with a set of resources designed for Grade 9 level math and science following the BC Curriculum.

## Technical Details

### Contributing

If you would like to help out with small code changes, feel free to submit a pull request, if you would like to become involved in a larger capacity, contact [development@luscinia.ca](mailto:development@luscinia.ca).

### Environment

The following environment variables can be configured

- `SPRING_REDIS_URL`
  - required
  - sets the connection string for a redis database
  - Aristotle uses Redis for session persistence in stateless mode and for persisting tokens across microservices
- `SPRING_MONGO_URL`
  - required
  - sets the connection string for a mongodb database
  - Aristotle uses MongoDB for persisting all application related data except certain specific files
  - hosted Aristotle utilises Azure Cosmos DB API for MongoDB with shard keys stored as the 'uuid' field
- `SPRING_SENDGRID_KEY`
  - required (to be optional in future updates)
  - allows Aristotle to send email
  - This will become optional in the future allowing users a choice between MailJet and SendGrid (potentially SMTP)
- `ARISTOTLE_DATAENTRY_URL`
  - required (defaults to entry.aristotle.luscinia.ca)
  - indicates classic data entry tool to be used by admins in the admin portal
  - will be deprecated when non-assessment resources tool is created
- `SPRING_SECURITY_TOKENKEY`
  - required
  - used as a secret key to sign remember me tokens
  - it is recommended that this is set to a randomly generated string
- `SPRING_SECURITY_ALLOWEDIP`
  - required
  - used to restrict admin account creation to specific ipv6 addresses
  - must be in CIDR notation, to allow all IPs (not recommended) set to `0/0`
- `SECURITY_USE_TWILIO`
  - required (defaults to false)
  - enable/disable Twilio Services
  - must be enabled to use sms/phone authentication
  - `SECURITY_TWILIO_SID`
    - required (if `SECURITY_USE_TWILIO` is true)
    - set to twilio account SID
  - `SECURITY_TWILIO_TOKEN`
    - required (if `SECURITY_USE_TWILIO` is true)
    - set to twilio api key
