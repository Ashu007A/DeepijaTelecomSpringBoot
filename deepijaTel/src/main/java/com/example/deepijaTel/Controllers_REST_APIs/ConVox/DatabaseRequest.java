package com.example.deepijaTel.Controllers_REST_APIs.ConVox;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DatabaseRequest {

    private String action;

    private String serverIp;

    private String dbName;

    private String tableName;
}