package controllers;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

import com.fasterxml.jackson.databind.JsonNode;
import com.mize.dto.EmployeeDTO;
import com.mize.service.Service;

@Component
public class Application extends Controller {

	@Autowired
	private Service service;

	public Result getAllEmployees() {

		List<EmployeeDTO> list = null;

		list = service.getAllEmployees();

		if (list == null) {
			play.Logger.info("list is empty");
			return notFound("list is empty");
		} else {
			play.Logger.error("list has displayed ");

			return ok(Json.toJson(list));
		}

	}

	public Result getEmployeeByID() {

		JsonNode jsonNode = request().body().asJson();

		if (jsonNode != null) {

			EmployeeDTO employeeDTO = Json
					.fromJson(jsonNode, EmployeeDTO.class);
			if (employeeDTO.getId() == 0) {
				return badRequest("id is manadatory");
			}
			try{
			employeeDTO = service.getEmployeeById(employeeDTO);
			
			}catch(EmptyResultDataAccessException e){
				play.Logger.error(e.getMessage(),e);
				return notFound("record not found");
			}
			

			if (employeeDTO != null) {

				return ok(Json.toJson(employeeDTO));

			} else {

				return notFound("RecordNot Found");
			}
		} else {
			return badRequest("Invalid request");
		}

	}

	public Result addEmployee() {

		JsonNode jsonNode = request().body().asJson();

		if (jsonNode != null) {
			EmployeeDTO employeeDTO = Json
					.fromJson(jsonNode, EmployeeDTO.class);

			int result = 0;
			try{
			result = service.addEmployee(employeeDTO);
			
			}catch(DuplicateKeyException e){
				play.Logger.info("employee already exist with this id"+employeeDTO.getId());
				return ok("employee already exist with this id");
			
			}
			
			if (result == 1) {

				play.Logger.info("new employee added with id:"
						+ employeeDTO.getId());

				return ok(Json.toJson(employeeDTO));

			}

			else {
				play.Logger.info("insertion failed");

				return ok("insertion failed");
			}
		} else {
			return badRequest("Invalid request");
		}
	}

	public Result deleteEmployee() {

		JsonNode jsonNode = request().body().asJson();

		if (jsonNode != null) {

			EmployeeDTO employeeDTO = Json
					.fromJson(jsonNode, EmployeeDTO.class);
			if (employeeDTO.getId() == 0) {
				return badRequest("Bad Request");
			}

			int result = 0;

			result = service.deleteEmployee(employeeDTO);

			if (result > 0) {

				play.Logger.info("employee deleted with id:"
						+ employeeDTO.getId());

				return ok(Json.toJson(employeeDTO));

			} else {

				play.Logger.info("deletion failed with id"
						+ employeeDTO.getId());

				return notFound("employee not found");
			}
		} else {

			return badRequest("invalid request");
		}

	}

	public Result updateEmployee() {

		JsonNode jsonNode = request().body().asJson();

		if (jsonNode != null) {
			EmployeeDTO employeeDTO = Json
					.fromJson(jsonNode, EmployeeDTO.class);

			if (employeeDTO.getId() == 0) {
				return badRequest("Bad Request");
			}

			int result = 0;

			result = service.updateEmployee(employeeDTO);
			if (result > 0) {

				play.Logger.info(Json.toJson(employeeDTO) + "updated");

				return ok(Json.toJson(employeeDTO));

			} else {
				return notFound(Json.toJson("employee not found"));
			}

		} else {
			return badRequest("invalid request");
		}
	}

}
