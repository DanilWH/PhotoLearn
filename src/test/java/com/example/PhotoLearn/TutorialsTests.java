package com.example.PhotoLearn;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("/application-test.properties")
class TutorialsTests {
	@Autowired
	private MockMvc mockMvc;

	@Test
	@Sql(value = {"/create-user-before.sql", "/tutorials-list-before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(value = {"/tutorials-list-after.sql", "/create-user-after.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
	public void mainPageContentTest() throws Exception {
		this.mockMvc.perform(get("/tutorials"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("Войти")))
				.andExpect(content().string(containsString("Зарегистрироваться")))
				.andExpect(xpath("//div[@id='tutorials-list']/div").nodeCount(4));
	}

	@Test
	public void redirectionTest() throws Exception {
		this.mockMvc.perform(get("/"))
				.andDo(print())
				.andExpect(status().is3xxRedirection())
				.andExpect(redirectedUrl("/tutorials"));
	}

	@Test
	@Sql(value = {"/create-user-before.sql", "/tutorials-list-before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(value = {"/tutorials-list-after.sql", "/create-user-after.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
	public void tutorialsSearchTest() throws Exception {
		this.mockMvc.perform(get("/tutorials").param("tutorial-search", "photo"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(xpath("//*[@id='tutorials-list']/div").nodeCount(2))
				.andExpect(xpath("//*[@id='tutorials-list']/div[@data-id=1]").exists())
				.andExpect(xpath("//*[@id='tutorials-list']/div[@data-id=2]").doesNotExist())
				.andExpect(xpath("//*[@id='tutorials-list']/div[@data-id=3]").exists())
				.andExpect(xpath("//*[@id='tutorials-list']/div[@data-id=4]").doesNotExist());
	}
}
