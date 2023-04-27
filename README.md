# Technical Test for Ninjatalent By Samir Ben Bouker

This application is made for the ninjatalent technical test where we can Obtain, Create, Update and Delete users from our database.

## Structure Project
    .
    ├── ...
    ├── main                                    # Main folder
    │   ├── java                                
    │   │   └── com.samirbenbouker.ninjatalent 
    │   │       ├── config                      # Config folder
    │   │       │   ├── SwaggerInfo             # (C) Contain Swagger information 
    │   │       │   └── UserConfig              # (C) Create user information
    │   │       ├── model 
    │   │       │   ├── Address                 # (C) Address class 
    │   │       │   └── User                    # (C) User class
    │   │       ├── repository 
    │   │       │   ├── AddressRepository       # (I) Address class JPA 
    │   │       │   └── UserRepository          # (I) User class JPA
    │   │       ├── resources
    │   │       │   ├── impl                    # Resource Impl folder 
    │   │       │   │   └── UserResourceImpl    # (C) Impl User Resource
    │   │       │   └── UserResource            # (I) Contains all paths for user
    │   │       ├── service
    │   │       │   ├── impl                    # Service Impl folder
    │   │       │   │   └── UserServiceImpl     # (C) Contains all logic about user paths
    │   │       │   ├── UserErrorEnum           # (E) Contains error text for user paths
    │   │       │   └── UserService             # (I) Contains service for user
    │   │       └── NinjatalentApplication      # Main Application
    │   └── ...
    ├── test                                    # Test folder
    │   └── java                                
    │       └── com.samirbenbouker.ninjatalent  
    │           └── UserServiceImpl.test        # User Service Impl Unit Test
    └── ...

## Endpoints

### Get All Users
In this endpoint we can get all the users of our database

<table>
  <tr>
    <th colspan="4" >Get /api/v1/users/getusers</th>
  </tr>
  <tr>
    <th>Response</th>
    <th>Status</th>
    <th>Description</th>
  </tr>
  <tr>
    <td>JSON { List of Users }</td>
    <td>200 OK</td>
    <td>OK</td>
  </tr>
</table>

### Get User By Id
In this endpoint we can get one user by id of our database

<table>
  <tr>
    <th colspan="4" >Get /api/v1/users/getusersById/{userId}</th>
  </tr>
  <tr>
    <th>Response</th>
    <th>Status</th>
    <th>Description</th>
  </tr>
  <tr>
    <td>JSON { User }</td>
    <td>200 OK</td>
    <td>OK</td>
  </tr>
  <tr>
    <td>ResponseStatusException</td>
    <td>400 BAD REQUEST</td>
    <td>Invalid user id</td>
  </tr>
  <tr>
    <td>ResponseStatusException</td>
    <td>404 NOT FOUND</td>
    <td>User not found</td>
  </tr>
  <tr>
    <th>In</th>
    <th>Param</th>
    <th>Values</th>
    <th>Description</th>
  </tr>
  <tr>
    <td>Path</td>
    <td>userId</td>
    <td>Integer</td>
    <td>User id</td>
  </tr>
</table>

### Create User By Id
In this endpoint we can create one user by id of our database

<table>
  <tr>
    <th colspan="4">POST /api/v1/users/createUsers</th>
  </tr>
  <tr>
    <th>Response</th>
    <th>Status</th>
    <th>Description</th>
  </tr>
  <tr>
    <td>JSON { User }</td>
    <td>201 CREATED</td>
    <td>Created</td>
  </tr>
  <tr>
    <td>ResponseStatusException</td>
    <td>404 NOT FOUND</td>
    <td>User not found</td>
  </tr>
  <tr>
    <td>ResponseStatusException</td>
    <td>409 CONFLICT</td>
    <td>User already exist</td>
  </tr>
  <tr>
    <th>In</th>
    <th>Param</th>
    <th>Values</th>
    <th>Description</th>
  </tr>
  <tr>
    <td>body</td>
    <td>JSON { User }</td>
    <td>User</td>
    <td>Information User</td>
  </tr>
</table>

### Update User By Id
In this endpoint we can update one user by id of our database

<table>
  <tr>
    <th colspan="4">PUT /api/v1/users/updateUsersById/{userId}</th>
  </tr>
  <tr>
    <th>Response</th>
    <th>Status</th>
    <th>Description</th>
  </tr>
  <tr>
    <td>JSON { User }</td>
    <td>200</td>
    <td>OK</td>
  </tr>
  <tr>
    <td>ResponseStatusException</td>
    <td>400 BAD REQUEST</td>
    <td>Invalid user id</td>
  </tr>
  <tr>
    <td>ResponseStatusException</td>
    <td>400 BAD REQUEST</td>
    <td>Identifier mismatch</td>
  </tr>
  <tr>
    <td>ResponseStatusException</td>
    <td>404 NOT FOUND</td>
    <td>User not found</td>
  </tr>
  <tr>
    <td>ResponseStatusException</td>
    <td>409 CONFLICT</td>
    <td>Email taken</td>
  </tr>
  <tr>
    <th>In</th>
    <th>Param</th>
    <th>Values</th>
    <th>Description</th>
  </tr>
  <tr>
    <td>body</td>
    <td>JSON { User }</td>
    <td>User</td>
    <td>Information User</td>
  </tr>
  <tr>
    <td>path</td>
    <td>userId</td>
    <td>Integer</td>
    <td>User id</td>
  </tr>
</table>

### Delete User By Id
In this endpoint we can delete one user by id of our database

<table>
  <tr>
    <th colspan="4">DELETE /api/v1/users/deleteUserById/{userId}</th>
  </tr>
  <tr>
    <th>Response</th>
    <th>Status</th>
    <th>Description</th>
  </tr>
  <tr>
    <td>ResponseEntity</td>
    <td>200 OK</td>
    <td>User deleted correctly</td>
  </tr>
  <tr>
    <td>ResponseStatusException</td>
    <td>400 BAD REQUEST</td>
    <td>Invalid user id</td>
  </tr>
  <tr>
    <td>ResponseStatusException</td>
    <td>404 NOT FOUND</td>
    <td>User not found</td>
  </tr>
  <tr>
    <th>In</th>
    <th>Param</th>
    <th>Values</th>
    <th>Description</th>
  </tr>
  <tr>
    <td>path</td>
    <td>userId</td>
    <td>Integer</td>
    <td>User id</td>
  </tr>
</table>
