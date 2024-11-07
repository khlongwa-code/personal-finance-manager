# Finance Manager (in progess)

**Finance Manager** is a command-line based application developed in Java for managing personal finances efficiently. It uses the EoD SQL library for ORM, with data persisted in an SQLite database.
The project follows best practices in development with a CI/CD pipeline, unit tests using TDD, and acceptance tests using BDD. The application is packaged as a Docker image for easy deployment and 
includes both client and server components.

---

## Table of Contents
- [Features](#features)
- [Installation](#installation)
- [Usage](#usage)
  - [Authentication Commands](#authentication-commands)
  - [View Commands](#view-commands)
  - [Make Commands](#make-commands)
  - [Clear Commands](#clear-commands)
  - [Update Commands](#update-commands)
- [Testing](#testing)
  - [Unit Testing](#unit-testing)
  - [Acceptance Testing](#acceptance-testing)
- [Development and CI/CD Pipeline](#development-and-cicd-pipeline)
- [Docker](#docker)
- [Technologies Used](#technologies-used)
- [Contributing](#contributing)
- [License](#license)

---

## Features

- **User Authentication**: Secure login and registration commands.
- **Finance Management Commands**:
  - View and manage budgets, savings, expenses, transaction history, and income sources.
  - Create savings goals and manage income sources and transactions.
- **Data Persistence**: Stores data in an SQLite database using the EoD SQL library for seamless ORM integration.
- **Testing**: Ensures reliability through unit tests (TDD) and acceptance tests (BDD).
- **Dockerized**: Easy setup and deployment via Docker.

---

## Installation

1. **Clone the repository**:
   ```bash
   git clone https://github.com/khlongwa-code/personal-finance-manager.git
   cd personal-finance-manager

2. **Build and Run with Docker**:
   ```bash
   docker build -t finance-manager .
   docker run -it finance-manager

## Usage

The Finance Manager application runs via a set of commands that allow users to interact with and manage financial data. 
Commands follow a pattern based on the action required: ```login```, ```register```, ```view```, ```make```, ```clear```, or ```update```.

## Authentication Commands

- ```login```: Log in to an existing account.
- ```register```:  Register a new account.

## View Commands

Use these commands to view different aspects of your finances:

- ```view budget```: View your current budget.
- ```view expenses```: View all recorded expenses.
- ```view transactions```: View the history of all transactions.
- ```view income```: View all income sources.
- ```view savings```: View your savings information.

## Make Commands

Create new records for managing finances:

- ```make budget```: Set a new budget.
- ```make expenses```: Add an expense.
- ```make savings```: Set a new savings goal.
- ```make income```: Add a new source of income.
- ```make transaction```: Record a transaction.

## Clear Commands

Delete specific financial data:

- ```clear expense```: Clear all expense data.
- ```clear budget```: Clear your budget.
- ```clear savings```: Clear all savings records.
- ```clear transactions```: Clear transaction history.

## Update Commands

Modify existing financial data:

- ```update budget```: Update the current budget.
- ```update savings```: Update savings goals or records.
- ```update income```: Update the income source.


## Testing

### Unit Testing

Finance Manager follows a Test-Driven Development (TDD) approach. Unit tests are written using JUnit and focus on individual 
components of the application. 

### Acceptance Testing

Acceptance tests are written in a Behavior-Driven Development (BDD) style. These tests verify end-to-end functionality to ensure
that all commands work as expected.

- To run the tests:
   ```bash
   mvn test

## Development and CI/CD Pipeline

This project uses a CI/CD pipeline to automate testing and deployment processes. On each commit:

- **Linting** and **Static Analysis**: Check code quality and adherence to style guidelines.
- **Build and Test**: Run all unit and acceptance tests to verify functionality.
- **Deployment**: Build and deploy the Docker image.
- **Versioning**: Versions the project to mornitor its stages in progress

##Technologies Used

- **Java**: Main programming language.
- **EoD SQL**: For object-relational mapping with SQLite.
- **SQLite**: Database for persisting financial data.
- **JUnit**: Unit testing and BDD acceptance tests.
- **Docker**: Containerization for easy deployment.
- **Maven**: Build automation tool.
