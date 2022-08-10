package com.example.backend.entities.models;

import java.sql.Timestamp;
import java.util.Date;

public class ResponseMessage {
    public static String success = "Operation is OK.";
    public static String fail = "Operation Failed.";
    public static Timestamp timestamp = new Timestamp(new Date().getTime());
}
