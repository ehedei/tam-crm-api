# Demo Data

## Description
Here you can find some data to test the correct functioning of the API.

##
Due to API requirements, a user is provided for application startup:
- user: admin
- password: 123456


## How to Run

You will have to import the data using the tool of your choice, e.g. ```mongoimport```:

```sh
mongoimport --db crm --collection users --file users.json --jsonArray
```
```sh
mongoimport --db crm --collection customers --file customers.json --jsonArray
```
