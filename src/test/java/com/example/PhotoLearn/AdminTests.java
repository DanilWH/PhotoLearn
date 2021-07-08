package com.example.PhotoLearn;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.xpath;

@SpringBootTest
@AutoConfigureMockMvc
@WithUserDetails("pl_admin")
@TestPropertySource("/application-test.properties")
@Sql(value = {"/create-user-before.sql", "/tutorials-list-before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/tutorials-list-after.sql", "/create-user-after.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class AdminTests {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void userAuthenticatedTest() throws Exception {
        this.mockMvc.perform(get("/tutorials"))
                .andDo(print())
                .andExpect(authenticated())
                .andExpect(xpath("//a[@id='greeting']").string("Здравствуйте, pl_admin!"));
    }

    @Test
    public void adminPrivilegesTest() throws Exception {
        this.mockMvc.perform(get("/tutorials"))
                .andDo(print())
                .andExpect(authenticated())
                .andExpect(xpath("//a[@id='adminDropdown']").exists());

        this.mockMvc.perform(get("/admin/users"))
                .andDo(print())
                .andExpect(authenticated())
                .andExpect(xpath("//table[@id='users-list-table']").exists());
    }
}
