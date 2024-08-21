package com.example.demo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import javax.sql.DataSource;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
public class testibg{ 
    DataSource dataSource = createDataSource();
    demoservice ec = new demoservice();
    JdbcTemplate jdbc = new JdbcTemplate(dataSource);   
    @Test
    public void test_insert_emp()
    {
        demo e=new demo();
        e.setFname("admin");
        e.setLname("ad");
        e.setDept("software");
        e.setGmail("admin@gmail.com");
        e.setPincode(522004);
        e.setPhoneno(625482769);
        e.setSalary(65000);
        e.setWorkloc("mcity");
        String result = ec.addemp(e);
        assertEquals("Invalid Admin credentials", result);

    }



    

    private DataSource createDataSource() {

        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");

        dataSource.setUrl("jdbc:mysql://localhost:3306/itgdb");

        dataSource.setUsername("root");

        dataSource.setPassword("M1racle@123");

        return dataSource;

    }


}
