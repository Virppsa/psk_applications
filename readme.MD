To run frontend:
```
cd frontend
http-server
```

To Push changes to github main:
```
git add .
git commit -m "Commit name"
git push origin main
```
To push to branch:
```
git checkout -b name_of_branch
git add .
git commit -m "message that goes with new commit"
git push origin name_of_branch
```

To switch to main branch and download latest version:
```
git checkout main
git pull origin main
```


-----
`Data Mapper:`

In Java, DataMapper typically refers to a design pattern rather than a specific library or function. The DataMapper pattern is used to separate the responsibilities of mapping data between different representations, such as between objects in an application and a database.

In this pattern, you have two main components:

1. Data Access Object (DAO): This component is responsible for interacting with the database or any other data source. It provides methods for CRUD operations (Create, Read, Update, Delete) without exposing the underlying database details.

2. Domain Object or Entity: This represents the business object or model in your application. It contains data and methods to manipulate that data.
The DataMapper acts as a bridge between these two components, facilitating the transfer of data between them. It typically involves mapping data from the database (or other data source) into domain objects and vice versa.

In our case our DataMapper is implemented in the following way:
Hibernate is used as a framework to enable interactions with the database. 
The Mappings are in our Entities package (where classes are mapped to database tables and columns)
