package com.example.myroomdatabase.database;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.Date;
import java.util.List;

public class MyViewModel extends AndroidViewModel {
    Repository repository;
    public MyViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository(application);
    }

    public void insertEmployee(Employee... employee){
      repository.insertEmployee(employee);
    }
    public void updateEmployee(Employee... employee){
      repository.updateEmployee(employee);
    }
    public void deleteEmployee(Employee... employee){
      repository.deleteEmployee(employee);
    }
    public void deleteEmployee(String email){
      repository.deleteEmployee(email);
    }
    public LiveData<List<Employee>> getAllEmployees(){
        return repository.getAllEmployees();
    }
    public LiveData<List<Employee>> getEmployeesByEmail(String email){
        return repository.getEmployeesByEmail(email);
    }
    public LiveData<List<Employee>> getEmployeesByName(String name){
        return repository.getEmployeesByName(name);
    }

    // Salary DAO methods


    public void insertSalary(Salary... salary){
       repository.insertSalary(salary);
    }
    public void updateSalary(Salary... salary){
        repository.updateSalary(salary);
    }
    public void deleteSalary(Salary... salary){
       repository.deleteSalary(salary);
    }
    public LiveData<List<Salary>> getAllSalaries(){
        return repository.getAllSalaries();
    }
    public LiveData<List<Salary>> getEmployeeSalaries(long empId){
        return repository.getEmployeeSalaries(empId);
    }
    public LiveData<List<Salary>> getEmployeeSalaries(long empId, Date from , Date to){
        return repository.getEmployeeSalaries(empId,from,to);
    }
    public LiveData<List<Salary>> getEmployeeSalaries(Date from , Date to){
        return repository.getEmployeeSalaries(from,to);
    }
    public void getSalariesSum(long empId , DoubleValueListener listener){
       repository.getSalariesSum(empId, listener);
    }
}
