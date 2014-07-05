package org.yonko.mist.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "EMPLOYEE")
public class Employee implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID")
	@GeneratedValue
	private Integer employeeID;
	
	@Column(name = "FIO")
	@Size(min = 1, max = 255, message = "Имя сотрудника должно иметь диапазон символов от 1 до 255")
	private String employeeFIO;
	
	@Column(name = "AGE")
	@NotNull
	@Min(value = 1, message = "Диапазон 0 - 100")
	@Max(value = 100, message = "Диапазон 0 - 100")
	private int employeeAge;
	
	@Column(name = "BIRTHDATE")
	private Date birthDate;
	
	@Size(min = 1, max = 255) 			//параметр essage можно прямо в jsp теге объявлять между дескрипторами 
	@Column(name = "PROFESSION")		//<form:errors path="profession" cssClass="error"> Что-то сообщить </form:errors>
	private String profession;
	
	@Column(name = "DESCRIPTION")
	private String description;

	public Integer getEmployeeID() {
		return employeeID;
	}
	
	public void setEmployeeID(Integer employeeID) {
		this.employeeID = employeeID;
	}

	public String getEmployeeFIO() {
		return employeeFIO;
	}

	public void setEmployeeFIO(String employeeFIO) {
		this.employeeFIO = employeeFIO;
	}

	public int getEmployeeAge() {
		return employeeAge;
	}

	public void setEmployeeAge(int employeeAge) {
		this.employeeAge = employeeAge;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getProfession() {
		return profession;
	}

	public void setProfession(String profession) {
		this.profession = profession;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * @Override
	public String toString() {
		return "Табельный номер - " + employeeID + ", ФИО сотрудника - " + employeeFIO + ", Возраст - " 
				+ employeeAge + ", Дата рождения - " + birthDate
				+ ", Профессия " + profession; 
	}
	 * */

}
