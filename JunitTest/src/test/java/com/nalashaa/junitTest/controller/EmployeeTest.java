package com.nalashaa.junitTest.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.json.JSONArray;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.CollectionUtils;

import com.nalashaa.junitTest.AbstractControllerTest;
import com.nalashaa.junitTest.entity.Employee;
import com.nalashaa.junitTest.entity.Skill;

/**
 * 
 * @author Prathap To Test all API calls in Employee Controller
 */
/**
 * 
 * @FixMethodOrder with Name Ascending will run methods one after the other in
 *                 Alphabetical order, Hence name all function with prefix
 *                 test1, test2... etc
 *
 */

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EmployeeTest extends AbstractControllerTest {

	@Before
	public void setUp() {
		super.setUp();
	}

	@Test
	public void test1_saveEmployee() throws Exception {
		String uri = "/employee/save";

		/* Can use map with Key, value pair instead of actual objects */
		List<Skill> skillList = new ArrayList<Skill>();

		Employee employee = new Employee();
		employee.setName("PRATHAP");
		employee.setCompany("Nalashaa");

		Skill skill1 = new Skill();
		skill1.setSkill("Skill 1");

		Skill skill2 = new Skill();
		skill2.setSkill("Skill 2");

		skillList.add(skill1);
		skillList.add(skill2);

		employee.setSkillList(skillList);

		logger.info(super.mapToJson(employee));

		/* Hit controller with Mockito */
		MvcResult result = this.mockMvc.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(super.mapToJson(employee))).andReturn();

		/**
		 * Can test with N Scenarios like below
		 */
		int status = result.getResponse().getStatus();
		Assert.assertEquals("failure - expected HTTP status 200", 200, status);

		String responseString = result.getResponse().getContentAsString();
		if (StringUtils.isEmpty(responseString)) {
			Assert.assertFalse("Respponse found empty", true);
		} else {
			Employee responseEmployee = super.mapFromJson(responseString, Employee.class);
			if (responseEmployee != null) {
				if (CollectionUtils.isEmpty(responseEmployee.getSkillList())) {
					Assert.assertFalse("Employee Skills found Empty", true);
				}
			} else {
				Assert.assertFalse("Employee object found Empty", true);
			}
		}
	}

	@Test
	public void test2_fetchEmployee() throws Exception {
		String uri = "/employee/getByName/PRATHAP";

		logger.info("URI : " + uri);

		/* Hit controller with Mockito */
		MvcResult result = this.mockMvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON))
				.andReturn();

		/**
		 * Can test with N Scenarios like below
		 */
		int status = result.getResponse().getStatus();
		Assert.assertEquals("failure - expected HTTP status 200", 200, status);

		String responseString = result.getResponse().getContentAsString();

		if (StringUtils.isEmpty(responseString)) {
			Assert.assertFalse("Respponse found empty", true);
		} else {
			JSONArray jArray = new JSONArray(responseString);
			if (!(jArray != null && jArray.length() > 0)) {
				Assert.assertFalse("Employee object found Empty", true);
			}
		}
	}

}
