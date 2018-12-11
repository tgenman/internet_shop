package com.dmitrybondarev.shop;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@RunWith(SpringRunner.class)
//@SpringBootTest
//@AutoConfigureMockMvc
public class LoginTest {

//    @Autowired
//    private MockMvc mockMvc;
//
//    @Test
//    public void contextLoad() throws Exception {
//        this.mockMvc.perform(get("/"))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(content().string(containsString("Home page")));
//    }

//    @Test
//    public void accessDeniedTest() throws Exception {
//        this.mockMvc.perform(get("/user"))
//                .andDo(print())
//                .andExpect(status().is3xxRedirection())
//                .andExpect(redirectedUrl("http://localhost/login"));
//    }

//    @Test
////    @Sql(value = {"/create-user-before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
//    public void correctLoginTest() throws Exception {
//        this.mockMvc.perform(formLogin().user("lupo@khtyler.com").password("qwe"))
//                .andExpect(status().is3xxRedirection())
//                .andExpect(redirectedUrl("/"));
//    }

//    @Test
//    public void badCredentials() throws Exception {
//        this.mockMvc.perform(formLogin().user("ivan@yandex.ru").password("1"))
//                .andExpect(status().isForbidden());
//    }

//    @Test
//    public void badCredentials() throws Exception {
//        this.mockMvc.perform(post("/login").param("username", "jonh"))
//                .andDo(print())
//                .andExpect(status().isForbidden());
//    }

}
