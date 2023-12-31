package com.smk627751.rolehierarchy.addsubrole.viewmodel;

import java.sql.SQLException;
import java.util.List;

import com.smk627751.rolehierarchy.addsubrole.view.View;
import com.smk627751.rolehierarchy.dto.Employee;
import com.smk627751.rolehierarchy.repository.Repository;

public class ViewModel {
	private Repository repo;
	private View view;
	public ViewModel(View view)
	{
		this.repo = Repository.getInstance();
		this.view = view;
	}
	public void getEmployees(String role) {
		try {
			List<Employee> employeeList = repo.getEmployee(role);
			view.onPrint(employeeList.toString());
		} catch (SQLException e) {
			view.onError(e);
		}
	}
	public void addSubRole(String role, String reportingManager) {
		try {
			boolean isAffected = repo.addSubRole(role,reportingManager);
			if(isAffected)
			{
				List<String> list = repo.displayRole(reportingManager);
				view.onPrint("Added successfully");
				view.onPrint(list.toString());
			}
		} catch (SQLException e) {
			view.onError(e);
		}
	}
	public void displayRoles()
	{
		try {
			List<String> list = repo.displayRole(null);
			view.onPrint(list.toString());
		} catch (SQLException e) {
			view.onError(e);
		}
	}
	public void deleteRole(String roleToDelete, String roleToTransfer){
		try {
			boolean isAffected = repo.deleteRole(roleToDelete,roleToTransfer);
			if(isAffected)
			{
				view.onPrint("Transfered successfully");
			}
		} catch (SQLException e) {
			view.onError(e);
		}
	}
	public void addUser(String name, String role)
	{
		try {
			boolean isAffected = repo.addUser(name, role);
			if(isAffected)
			{
				view.onPrint("Added successfully");
			}
		} catch (SQLException e) {
			view.onError(e);
		}
	}
}
