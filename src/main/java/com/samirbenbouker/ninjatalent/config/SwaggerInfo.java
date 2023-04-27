package com.samirbenbouker.ninjatalent.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SwaggerInfo {

    private HashMap swagger;

    public SwaggerInfo() {
        this.swagger = getSwagger();
    }

    public HashMap getSwagger() {
        this.swagger = new HashMap<>();
        this.swagger.put("author", "Samir Ben Bouker");
        this.swagger.put("project", "Technical test of NinjaTalent");

        HashMap paths = new HashMap<>();
        paths.put("/api/v1/users/getusers",getAllUsersInfo());
        paths.put("/api/v1/users/getusersById/{userId}", getUserById());
        paths.put("/api/v1/users/createUsers", createUsers());
        paths.put("/api/v1/users/updateUsersById/{userId}", updateUsersById());
        paths.put("/api/v1/users/deleteUsersById/{userId}", deleteUserById());

        this.swagger.put("paths", paths);
        return this.swagger;
    }

    private HashMap getAllUsersInfo() {
        HashMap schema = new HashMap();
        schema.put("type", "array");
        schema.put("items", "List<User>");

        HashMap responseInfo = new HashMap();
        responseInfo.put("description", "OK");
        responseInfo.put("schema", schema);

        HashMap responseGetUsers = new HashMap();
        responseGetUsers.put("200", responseInfo);

        HashMap response = new HashMap();
        response.put("response", responseGetUsers);

        HashMap getUsers = new HashMap();
        getUsers.put("description", "Get all users");
        getUsers.put("GET", response);

        return getUsers;
    }

    private HashMap getUserById() {
        HashMap response = new HashMap();

        HashMap responseGetUserById = new HashMap();
        responseGetUserById.put("200", response200("OK", "User"));
        responseGetUserById.put("400", response400());
        responseGetUserById.put("404", response404());

        response.put("response", responseGetUserById);

        List params = new ArrayList();
        params.add(createParameter("path", "userId", "Integer", true));

        response.put("parameters", params);

        HashMap getUsersById = new HashMap();
        getUsersById.put("description", "Get one User");
        getUsersById.put("GET", response);

        return getUsersById;
    }

    private HashMap createUsers() {
        HashMap respose = new HashMap();

        HashMap responseCreateUser = new HashMap();
        responseCreateUser.put("201", response200("Created", "User"));
        responseCreateUser.put("405", response405());
        responseCreateUser.put("409", response409());

        respose.put("response", responseCreateUser);

        List params = new ArrayList();
        params.add(createParameter("body", "user", "User", true));

        respose.put("parameters", params);

        HashMap createUsers = new HashMap();
        createUsers.put("description", "Create one user");
        createUsers.put("POST", respose);

        return createUsers;
    }

    private HashMap updateUsersById() {
        HashMap respose = new HashMap();

        List params = new ArrayList();
        params.add(createParameter("path", "userId", "Integer", true));
        params.add(createParameter("body", "user", "User", true));

        respose.put("parameters", params);

        HashMap responseUpdateUserById = new HashMap();
        responseUpdateUserById.put("200", response200("OK", "User"));
        responseUpdateUserById.put("400", response400());
        responseUpdateUserById.put("404", response404());
        responseUpdateUserById.put("409", response409EmailTaken());

        respose.put("response", responseUpdateUserById);

        HashMap updateUserById = new HashMap();
        updateUserById.put("description", "Update one user");
        updateUserById.put("PUT", respose);

        return updateUserById;
    }

    private HashMap deleteUserById() {
        HashMap respose = new HashMap();

        List params = new ArrayList();
        params.add(createParameter("path", "userId", "Integer", true));

        respose.put("parameters", params);

        HashMap responseDeleteUserById = new HashMap();

        responseDeleteUserById.put("200", response200("OK", null));
        responseDeleteUserById.put("400", response400());
        responseDeleteUserById.put("404", response404());

        respose.put("response", responseDeleteUserById);

        HashMap deleteUserById = new HashMap();
        deleteUserById.put("description", "Delete one user");
        deleteUserById.put("DELETE", respose);

        return deleteUserById;
    }

    //response 200
    private HashMap response200(String description, String schema) {
        HashMap response = new HashMap();
        response.put("description", description);

        if(schema != null)
            response.put("schema", schema);

        return response;
    }

    //error response
    private HashMap response400() {
        HashMap response400 = new HashMap();
        response400.put("description", "Invalid User id");
        return response400;
    }

    private HashMap response404() {
        HashMap response404 = new HashMap();
        response404.put("description", "User not found");
        return response404;
    }

    private HashMap response405() {
        HashMap response405 = new HashMap();
        response405.put("description", "Invalid input");
        return response405;
    }

    private HashMap response409() {
        HashMap response409 = new HashMap();
        response409.put("description", "User already exist");
        return response409;
    }

    private HashMap response409EmailTaken() {
        HashMap response409 = new HashMap();
        response409.put("description", "Email taken");
        return response409;
    }

    // create parameters
    private HashMap createParameter(String in, String name, String type, Boolean required) {
        HashMap parameter = new HashMap();
        parameter.put("in", in);
        parameter.put("name", name);
        parameter.put("type", type);
        parameter.put("required", required);
        return parameter;
    }
}
