package com.samirbenbouker.ninjatalent.service;

public enum UserErrorEnum {

    USER_INVALID_USER_ID("Invalid user id"),
    USER_NOT_FOUND("User not found"),
    USER_ALREADY_EXIST("User already exist"),
    USER_EMAIL_TAKEN("Email taken"),
    USER_IDENTIFIER_MISMATCH("Identifier mismatch"),
    USER_INVALID_INPUT("Invalid input");

    private String errorMessage;

    private UserErrorEnum(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String gerErrorMessage()  {
        return errorMessage;
    }
}
