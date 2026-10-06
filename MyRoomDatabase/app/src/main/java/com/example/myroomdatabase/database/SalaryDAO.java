package com.example.myroomdatabase.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.TypeConverters;
import androidx.room.Update;

import java.util.Date;
import java.util.List;

@Dao
@TypeConverters({DateConverter.class})
public interface SalaryDAO {
    @Insert
    void insertSalary(Salary... salary);
    @Update
    void updateSalary(Salary... salary);
    @Delete
    void deleteSalary(Salary... salary);
    @Query("Select * from Salary order by empId asc")
    LiveData<List<Salary>> getAllSalaries();
    @Query("Select * from Salary where empId=:empId order by date DESC")
    LiveData<List<Salary>> getEmployeeSalaries(long empId);
    @Query("Select * from Salary where empId=:empId And date>=:from And date<=:to order by date DESC")
    LiveData<List<Salary>> getEmployeeSalaries(long empId, Date from , Date to);
    @Query("Select * from Salary where date>=:from And date<=:to order by date DESC")
    LiveData<List<Salary>> getEmployeeSalaries(Date from , Date to);
    @Query("Select sum(amount) from Salary where empId=:empId")
    double getSalariesSum(long empId);
















}
