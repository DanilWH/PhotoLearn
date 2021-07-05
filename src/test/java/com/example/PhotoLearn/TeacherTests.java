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
@WithUserDetails("pl_teacher")
@TestPropertySource("/application-test.properties")
@Sql(value = {"/create-user-before.sql", "/tutorials-list-before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/tutorials-list-after.sql", "/create-user-after.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class TeacherTests {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void teacherAuthenticatedTest() throws Exception {
        this.mockMvc.perform(get("/tutorials"))
                .andDo(print())
                .andExpect(authenticated())
                .andExpect(xpath("//a[@id='greeting']").string("Здравствуйте, pl_teacher!"));
    }

    @Test
    public void teacherDoesNotHaveAdminsPrivilegesTest() throws Exception {
        this.mockMvc.perform(get("/admin/users"))
                .andDo(print())
                .andExpect(status().isForbidden());
    }

    @Test
    public void addTutorialTest() throws Exception {
        MockHttpServletRequestBuilder multipart = multipart("/tutorial/new")
                .file("multipartFile", "Content of the file".getBytes())
                .param("title", "The title of the new tutorial.")
                .param("content", "This is the content of the new tutorial.")
                .with(csrf());

        this.mockMvc.perform(multipart)
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));

        this.mockMvc.perform(get("/tutorials"))
                .andDo(print())
                .andExpect(xpath("//div[@id='tutorials-list']/div").nodeCount(5))
                .andExpect(xpath("//div[@id='tutorials-list']/div[@data-id=10]/div/h5")
                        .string("The title of the new tutorial."));
    }

    @Test
    public void teacherTutorialPageAppearanceTest() throws Exception {
        this.mockMvc.perform(get("/tutorial/1"))
                .andDo(print())
                .andExpect(xpath("//a[@id='watch-students-results-button']").exists())
                .andExpect(xpath("//div[@id='edit-delete-tutorial-buttons']").exists())
                .andExpect(xpath("//form[@id='photo-result-uploading-form']").doesNotExist());
    }

    @Test
    public void editTutorialTest() throws Exception {
        // tutorial before editing.
        this.mockMvc.perform(get("/tutorials"))
                .andDo(print())
                .andExpect(xpath("//div[@id='tutorials-list']/div").nodeCount(4))
                .andExpect(xpath("//div[@id='tutorials-list']/div[@data-id=1]/div/h5")
                        .string("Learn to edit photos"));

        // editing the tutorial.
        MockHttpServletRequestBuilder multipart = multipart("/tutorial/1/edit")
                .file("multipartFile", "Edited content of the file".getBytes())
                .param("title", "Edited title.")
                .param("content", "Edited content.")
                .with(csrf());

        this.mockMvc.perform(multipart)
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/tutorial/1"));

        // make sure the changes applied after editing.
        this.mockMvc.perform(get("/tutorials"))
                .andDo(print())
                .andExpect(xpath("//div[@id='tutorials-list']/div").nodeCount(4))
                .andExpect(xpath("//div[@id='tutorials-list']/div[@data-id=1]/div/h5")
                        .string("Edited title."));
    }

    @Test
    public void deleteTutorialTest() throws Exception {
        // go to the delete confirmation page.
        this.mockMvc.perform(get("/tutorial/1/delete"))
                .andDo(print())
                .andExpect(authenticated())
                .andExpect(status().isOk());

        // make the post request on deletion.
        this.mockMvc.perform(post("/tutorial/1/delete").with(csrf()))
                .andDo(print())
                .andExpect(authenticated())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));

        // check if the tutorial has really been deleted.
        this.mockMvc.perform(get("/tutorials"))
                .andDo(print())
                .andExpect(xpath("//div[@id='tutorials-list']/div").nodeCount(3))
                .andExpect(xpath("//div[@id='tutorials-list']/div[@data-id=1]").doesNotExist())
                .andExpect(xpath("//div[@id='tutorials-list']/div[@data-id=2]").exists())
                .andExpect(xpath("//div[@id='tutorials-list']/div[@data-id=3]").exists())
                .andExpect(xpath("//div[@id='tutorials-list']/div[@data-id=4]").exists());
    }
}
