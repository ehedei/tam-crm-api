# CRM Customer API
[![MIT License](https://img.shields.io/apm/l/atomic-design-ui.svg?)](https://github.com/ehedei/tam-crm-api/blob/main/LICENSE)


## Description
The purpose of this REST API is to manage the user data of a small store.

## Main Technologies
- Spring Boot
- Spring Data
- Spring Security
- MongoDB

## How to Run

This application is packaged as a jar which has Tomcat server embedded. No Tomcat or JBoss installation is necessary. You can run it using the ```mvn spring-boot:run``` command.

1. Clone this repository

2. Make sure you are using OpenJDK 11 and Maven 3.x

3. Don't forget to configure the application to give it access to a MongoDB database (via ```application.properties``` file, with ```SPRING_DATA_MONGODB_URI``` environment variable, etc. There are many approaches).

4. Start the app
```sh
mvn spring-boot:run
```

## Deployment
There are many options for [deployment](https://docs.spring.io/spring-boot/docs/current/reference/html/deployment.html), in any case do not forget to change the credentials for the database and the JWT secret.


## Data Model

### Users
| Key          | Type       | Reference      | Required | Validation               |
| ------------ | ---------- | -------------- | -------- | ------------------------ |
| id           | String     |                | False    |                          |
| username     | String     |                | True     | Unique                   |
| email        | String     |                | True     | Regex, Unique            |
| password     | String     |                | True     |                          |
| roles        | Enum       |                | False    | enum: [ROLE_USER, ROLE_ADMIN] |
| enabled      | Boolean    |                | False    |                          |
| accountNonExpired         | Boolean    |                | False    |                          |
| accountNonLocked          | Boolean    |                | False    |                          |
| credentialsNonExpired     | Boolean    |                | False    |                          |


##

### Customers
| Key          | Type       | Reference      | Required | Validation               |
| ------------ | ---------- | -------------- | -------- | ------------------------ |
| id           | String     |                | False    |                          |
| name         | String     |                | True     |                          |
| surname      | String     |                | True     |                          |
| photoSrc     | String     |                | False    |                          |
| createdBy    | Object     | User Object    | False    |                          |
| updatedBy    | Object     | User Object    | False    |                          |

##

## API Endpoints
All the endpoints are preceeded by `/api`.

- ### Auth
All these endpoints are preceeded by `/auth`.

|Verb|Route|Description|Auth.|Body Params|Returns|
|-|-|-|-|-|-|
|POST|**/login** |Log in with username and password|-|**username**, **password**|token, user|
|GET|**/profile** |User get own profile|Admin, User|-|User|

##
- ### User
All these endpoints are preceeded by `/user`.

|Verb|Route|Description|Auth.|Body Params|Query Params|Returns|
|-|-|-|-|-|-|-|
|GET|**/** |Get a list of all Users|Admin|-|**page** _[0]_, <br>**pageSize** _[10]_,<br>**sortBy** _[username]_|List with all Users|
|GET|**/:id** |Get an User by id|Admin|-|-|User|
|POST|**/**|Register a new User in the App|Admin|**username**, **email**, **password**, roles, enabled, accountNonExpired, accountNonLocked, credentialsNonExpired |-|User created||
|PUT|**/:id**|Update an User by id|Admin|**username**, **email**, password, **roles**, enabled, accountNonExpired, accountNonLocked, credentialsNonExpired |-|User updated|
|DELETE|**/:id** |Delete an User by id|Admin|-|-|-|


##
- ### Customer
All these endpoints are preceeded by `/customer`.

|Verb|Route|Description|Auth.|Body Params|Query Params|Returns|
|-|-|-|-|-|-|-|
|GET|**/** |Get a list of all Customers|Admin, User|-|**page** _[0]_, <br>**pageSize** _[10]_,<br>**sortBy** _[id]_|List with all Customers|
|GET|**/:id** |Get a Customer by Id|Admin, User|-|-|Customer|
|POST|**/** |Create a Customer|Admin, User|**name**, **surname**, photoSrc |-|Created Customer|
|PUT|**/id** |Update a Customer|Admin, User|**name**, **surname**, photoSrc|-|Updated Customer|
|DELETE|**/id** |Delete a Customer by Id|Admin, User|-|-|-|
##

## Contact
**Ehedei Hernández García** - _Full-stack developer_ - [GitHub](https://github.com/ehedei) - [LinkedIn](https://linkedin.com/in/ehedeihg/)

## License
[MIT License](https://github.com/ehedei/tam-crm-api/blob/main/LICENSE)
