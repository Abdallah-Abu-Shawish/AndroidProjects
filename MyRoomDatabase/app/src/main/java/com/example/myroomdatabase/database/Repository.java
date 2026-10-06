package com.example.myroomdatabase.database;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.Date;
import java.util.List;

public class Repository {

    EmployeeDAO employeeDAO;
    SalaryDAO salaryDAO;

    public Repository(Application application) {
        MyRoomDatedase db = MyRoomDatedase.getDatabase(application);
        employeeDAO = db.employeeDao();
        salaryDAO = db.salaryDao();
    }


    public void insertEmployee(Employee... employee){
        MyRoomDatedase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                employeeDAO.insertEmployee(employee);
            }
        });
    }
    public void updateEmployee(Employee... employee){
        MyRoomDatedase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                employeeDAO.updateEmployee(employee);
            }
        });
    }
    public void deleteEmployee(Employee... employee){
        MyRoomDatedase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                employeeDAO.deleteEmployee(employee);
            }
        });
    }
    public void deleteEmployee(String email){
        MyRoomDatedase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                employeeDAO.deleteEmployee(email);
            }
        });
    }
    public LiveData<List<Employee>> getAllEmployees(){
        return employeeDAO.getAllEmployees();
    }
    public LiveData<List<Employee>> getEmployeesByEmail(String email){
        return employeeDAO.getEmployeesByEmail(email);
    }
    public LiveData<List<Employee>> getEmployeesByName(String name){
        return employeeDAO.getEmployeesByName(name);
    }

    // Salary DAO methods


    public void insertSalary(Salary... salary){
        MyRoomDatedase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                salaryDAO.insertSalary(salary);
            }
        });
    }
    public void updateSalary(Salary... salary){
        MyRoomDatedase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                salaryDAO.updateSalary(salary);
            }
        });
    }
    public void deleteSalary(Salary... salary){
        MyRoomDatedase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                salaryDAO.deleteSalary(salary);
            }
        });
    }
    public LiveData<List<Salary>> getAllSalaries(){
        return salaryDAO.getAllSalaries();
    }
    public LiveData<List<Salary>> getEmployeeSalaries(long empId){
        return salaryDAO.getEmployeeSalaries(empId);
    }
    public LiveData<List<Salary>> getEmployeeSalaries(long empId, Date from , Date to){
        return salaryDAO.getEmployeeSalaries(empId,from,to);
    }
    public LiveData<List<Salary>> getEmployeeSalaries(Date from , Date to){
        return salaryDAO.getEmployeeSalaries(from,to);
    }
    public void getSalariesSum(long empId , DoubleValueListener listener){
        MyRoomDatedase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
               double value = salaryDAO.getSalariesSum(empId);
               listener.onValueSubmit(value);
            }
        });
    }
}
