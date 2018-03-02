package com.nalashaa.junitTest;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebAppConfiguration
public abstract class AbstractControllerTest extends AbstractTest {

	protected MockMvc mockMvc;

	@Autowired
	protected WebApplicationContext webApplicationContext;

	protected void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	protected String mapToJson(Object obj) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(obj);
	}

	/**
	 * Maps a String of JSON into an instance of a Class of type T. Uses a
	 * Jackson ObjectMapper.
	 * 
	 * @param json
	 *            A String of JSON.
	 * @param clazz
	 *            A Class of type T. The mapper will attempt to convert the JSON
	 *            into an Object of this Class type.
	 * @return An Object of type T.
	 * @throws JsonParseException
	 *             Thrown if an error occurs while mapping.
	 * @throws JsonMappingException
	 *             Thrown if an error occurs while mapping.
	 * @throws IOException
	 *             Thrown if an error occurs while mapping.
	 */
	protected <T> T mapFromJson(String json, Class<T> clazz)
			throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.readValue(json, clazz);
	}

}
