package com.example.demo;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.jdbc.core.JdbcTemplate;
@RunWith(MockitoJUnitRunner.class)
public class demoservicetest {

    @Mock
    private JdbcTemplate jdbcTemplateMock;

    @InjectMocks
    private demoservice demoservice;

    @Test
    public void testAddemp() {
        demo e = new demo();
        e.setUname("aad");
        e.setPwd("ad@ad@1212");
        e.setFname("ram");
        e.setLname("kk");
        e.setGmail("ram@gmail;.com");
        e.setWorkloc("bhogapuram");
        e.setSalary(30000);
        when(jdbcTemplateMock.queryForObject(anyString(), eq(String.class))).thenReturn("admin");
        when(jdbcTemplateMock.queryForObject(anyString(), eq(Integer.class))).thenReturn(1);
        when(jdbcTemplateMock.update(anyString(), any(Object[].class))).thenReturn(1);
        String result = demoservice.addemp(e);
        assertEquals("Details are Inserted", result);
    }

    
    @Test
    public void addproject(){
        demo e=new demo();
        e.setPname("java Developer");
        e.setPid(2);
        e.setClient("tejaswi");
        e.setHr_manager("santosh kolla");
        e.setProject_manager("mounika");
        when(jdbcTemplateMock.queryForObject(anyString(), eq(String.class))).thenReturn("admin");
        when(jdbcTemplateMock.queryForObject(anyString(), eq(Integer.class))).thenReturn(1);
        when(jdbcTemplateMock.queryForObject(anyString(), eq(String.class))).thenReturn("Hr");
        when(jdbcTemplateMock.update(anyString(), any(Object[].class))).thenReturn(3);

        String result = demoservice.addproject(e);
        assertEquals("Details are Inserted", result);
    }

    @Test
    public void addproject_emp(){
        demo e=new demo();
        e.setUname("smma");
        e.setPwd("la@ma@15:23:46.016343341");
        e.setEid(6);
        e.setPid(1);
        e.setAssigneddate("2024-07-01");

        when(jdbcTemplateMock.queryForObject(anyString(), eq(String.class))).thenReturn("project Manager");
        when(jdbcTemplateMock.queryForObject(anyString(), eq(Integer.class))).thenReturn(2);
        when(jdbcTemplateMock.queryForObject(anyString(), eq(String.class))).thenReturn("Hr");
        when(jdbcTemplateMock.queryForObject(anyString(), eq(Integer.class))).thenReturn(3);
        when(jdbcTemplateMock.queryForObject(anyString(), eq(Integer.class))).thenReturn(1);
        String result = demoservice.addproj_emp(e);
        assertEquals("Details are Inserted", result);
}
}

