Universal Blogger
=================

This is a 3-layer application that demos a 3-tier structure.

The individual components are:

- Presentation
  - iOS App
- Application
  - Java application exposed through a **Spring Boot** REST service
- Data
  - A docker container with:
    - **postgres** database
    - **pgAdmin** administration utility

It comprises the following parts:
* A Docker container for a persistence provider:
	* PostGres relational Database
	* pgAdmin application to manage the database
* A Spring Boot REST service to as the main interface for data operations on the datasbase.
* An iOS native App as one front end for blogging