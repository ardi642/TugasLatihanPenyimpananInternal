package com.example.tugaslatihanpenyimpananinternal;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;


@Dao
public interface UserDao {
    @Query("SELECT * FROM user")
    List<User> getUsers();

//    @Query("SELECT * FROM user WHERE uid IN (:userIds)")
//    List<User> loadAllByIds(int[] userIds);

    @Query("SELECT * FROM user WHERE nim == :nim LIMIT 1")
    User findByNim(String nim);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertUser(User user);

    @Update
    void updateUser(User user);

    @Delete
    void delete(User user);
}