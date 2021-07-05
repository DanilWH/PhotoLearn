package com.example.PhotoLearn;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@WithUserDetails("pl_student")
@TestPropertySource("/application-test.properties")
@Sql(value = {"/create-user-before.sql", "/tutorials-list-before.sql", "/student-result-before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/tutorials-list-after.sql", "/create-user-after.sql", "/student-result-after.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class StudentTests {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void studentIsAuthenticatedTest() throws Exception {
        this.mockMvc.perform(get("/tutorials"))
                .andDo(print())
                .andExpect(authenticated())
                .andExpect(xpath("//a[@id='greeting']").string("Здравствуйте, pl_student!"));
    }

    @Test
    public void studentMainPageAppearanceTest() throws Exception {
        this.mockMvc.perform(get("/tutorials"))
                .andDo(print())
                .andExpect(authenticated())
                .andExpect(xpath("//a[@id='adminDropdown']").doesNotExist())
                .andExpect(xpath("//a[@id='create-new-tutorial-button']").doesNotExist());
    }

    @Test
    public void studentTutorialPageAppearanceTest() throws Exception {
        this.mockMvc.perform(get("/tutorial/1"))
                .andDo(print())
                .andExpect(authenticated())
                // edit-delete tutorial buttons.
                .andExpect(xpath("//div[@id='edit-delete-tutorial-buttons']").doesNotExist())
                //  "watch students results" button.
                .andExpect(xpath("//a[@id='watch-students-results-button']").doesNotExist())
                // photo result uploading form.
                .andExpect(xpath("//form[@id='photo-result-uploading-form']").exists());
    }

    @Test
    public void adminPrivilegesDeniedTest() throws Exception {
        this.mockMvc.perform(get("/admin/users"))
                .andDo(print())
                .andExpect(authenticated())
                .andExpect(status().isForbidden());

        this.mockMvc.perform(get("/admin/user/3/edit"))
                .andDo(print())
                .andExpect(authenticated())
                .andExpect(status().isForbidden());
    }

    @Test
    public void teacherPrivilegesDeniedTest() throws Exception {
        // tutorial editing prohibition.
        this.mockMvc.perform(get("/tutorial/1/edit"))
                .andDo(print())
                .andExpect(authenticated())
                .andExpect(status().isForbidden());
        this.mockMvc.perform(post("/tutorial/1/edit"))
                .andDo(print())
                .andExpect(authenticated())
                .andExpect(status().isForbidden());

        // tutorial deletion prohibition.
        this.mockMvc.perform(get("/tutorial/1/delete"))
                .andDo(print())
                .andExpect(authenticated())
                .andExpect(status().isForbidden());
        this.mockMvc.perform(post("/tutorial/1/delete"))
                .andDo(print())
                .andExpect(authenticated())
                .andExpect(status().isForbidden());

        // students' results prohibition.
        this.mockMvc.perform(get("/tutorial/1/photo-results"))
                .andDo(print())
                .andExpect(authenticated())
                .andExpect(status().isForbidden());
    }

    @Test
    public void addStudentResultTest() throws Exception {
        MockHttpServletRequestBuilder multipart = multipart("/tutorial/1/photo-result/add")
                .file("multipartFile", "Content of the file.".getBytes())
                .param("description", "The description of the new result.")
                .with(csrf());

        this.mockMvc.perform(multipart)
                .andDo(print())
                .andExpect(authenticated())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/tutorial/1"));

        this.mockMvc.perform(get("/tutorial/1"))
                .andDo(print())
                .andExpect(authenticated())
                .andExpect(xpath("//div[@id='student-result']").exists())
                .andExpect(xpath("//p[@id='description']")
                        .string("The description of the new result."));
    }

    @Test
    public void deleteStudentResultTest() throws Exception {
        this.mockMvc.perform(get("/tutorial/2/photo-result/1/delete"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/tutorial/2"));

        this.mockMvc.perform(get("/tutorial/2"))
                .andDo(print())
                .andExpect(xpath("//div[@id='student-result']").doesNotExist())
                .andExpect(xpath("//form[@id='photo-result-uploading-form']").exists());
    }
}
