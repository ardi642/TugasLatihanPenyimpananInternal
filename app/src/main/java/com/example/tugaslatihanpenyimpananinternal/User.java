package com.example.tugaslatihanpenyimpananinternal;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "user")
public class User {

    @PrimaryKey
    @NonNull
    public String nim;

    public String nama;

    public String password;
}
