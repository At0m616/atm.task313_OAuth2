//package com.example.atm.task313.service;
//
//import com.example.atm.task313.dao.RoleDao;
//import com.example.atm.task313.dao.UserDao;
//import com.example.atm.task313.model.User;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.util.Assert;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.BDDMockito.given;
//
//@SpringBootTest
//class UserServiceImplTest {
//
//
//
//    @MockBean
//    private UserDao userDao;
//    @MockBean
//    private RoleDao roleDao;
//
//    @Autowired
//    private UserService userService;
//    @Autowired
//    private RoleService roleService;
//
//    private User user;
//
//    @BeforeEach
//    void setUp() {
//        user = new User();
//        String[] role = {"ROLE_ADMIN"};
//
//        user.setId(42L);
//        user.setFirstname("Test1");
//        user.setLastname("Test1");
//        user.setUsername("username1");
//        user.setPassword("Password");
//        user.setAge(15);
////        user.setRoles(roleService.findRolesSetByName(role));
//
//    }
//
//    @AfterEach
//    void tearDown() {
//    }
//
//    @Test
//    void saveUser() {
//
//    }
//
//    @Test
//    void updateUser() {
//    }
//
//    @Test
//    void findUserById() {
//
//        given(this.userDao.getById(any()))
//                .willReturn(user);
//        User u = userService.findUserById(42L);
//        assertThat(u.getId()).isEqualTo(42);
//        // и какие-нибудь другие проверки
//    }
//
//
//    @Test
//    void findUserByEmail() {
//    }
//
//    @Test
//    void removeUser() {
//    }
//
//    @Test
//    void findAllUsers() {
//    }
//}