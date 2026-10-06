package com.example.myroomdatabase.database;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.TypeConverters;
import androidx.room.Update;
import java.util.List;

@Dao
@TypeConverters({DateConverter.class})
public interface EmployeeDAO {
    @Insert
    void insertEmployee(Employee... employee);
    @Update
    void updateEmployee(Employee... employee);
    @Delete
    void deleteEmployee(Employee... employee);
    @Query("delete from Employee_table where email=:email" )
    void deleteEmployee(String email);
    @Query("Select * from Employee_table order by emp_name ASC")
    LiveData<List<Employee>> getAllEmployees();
    @Query("Select * from Employee_table where email=:email")
    LiveData<List<Employee>> getEmployeesByEmail(String email);
    @Query("Select * from Employee_table where emp_name like '%'||:name||'%' ")
    LiveData<List<Employee>> getEmployeesByName(String name);
}
