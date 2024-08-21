package com.example.demo;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
//import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

//import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
@Service
public class demoservice {
    @Autowired
    JdbcTemplate jdbct;
    public String addemp(demo e)
    {
        String aname=e.getUname();
        String apwd=e.getPwd();
        String msg="";
        try{
            String q1="select role_name from roles where roleid= (select roleid from emp where uname=\""+aname+"\" and pwd=\""+apwd+"\")";
            System.out.println(q1);
            String rname=  jdbct.queryForObject(q1, String.class);
            if(!(rname.equals("admin")))
            {
                return "You are not allowed.";
            }
            else
            {
                String fname=e.getFname();
                String lname=e.getLname();
                String uname=fname.charAt(0)+lname;
                String wmail=fname.charAt(0)+lname+"@miraclesoft.com";
                //LocalDateTime currentDateTime = LocalDateTime.now();
                //int hour = currentDateTime.getHour();
                //int min = currentDateTime.getMinute();
                String pwd=fname.substring(fname.length()-2)+"@"+lname.substring(lname.length()-2)+"@"+LocalTime.now();
                System.out.println(pwd);
                int roleid=0;
                try{
                    String q2="select roleid from roles where role_name=\""+e.getRname()+"\"";
                    System.out.println(q2);
                    roleid=jdbct.queryForObject(q2,Integer.class);
                    System.out.println(roleid); 
                }
                catch(Exception ex1)
                {
                    return "Invalid Employee Position";
                }
                String q3="select empid from emp where uname=\""+aname+"\"";
                System.out.println(q3);
                int aid=jdbct.queryForObject(q3,Integer.class);
                // String q4="insert into emp(fname, lname, dept, phoneno, joining_date,gmail, workdaymail, pwd, workloc, pincode, salary, roleid,createdby,uname) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                String q4="insert into emp(fname, lname, dept, phoneno, gmail, workdaymail, pwd, workloc, pincode, salary, roleid,createdby,uname) values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
                System.out.println(q4);
                int i=jdbct.update(q4,e.getFname(),e.getLname(),e.getDept(),e.getPhoneno(),e.getGmail(),wmail,pwd,e.getWorkloc(),e.getPincode(),e.getSalary(),roleid,aid,uname); 
                System.out.println(i);
                if(i>0)
                {
                    msg="Details are Inserted";
                }
                else
                {
                    msg="Details are not Inserted";
                }
            }
        }
        catch(Exception ex)
        {
            return "Invalid Admin credentials";
        }
        return msg;
    }

    public String addproject(demo d){
        String aname=d.getUname();
        String apwd=d.getPwd();
        String msg="";
        try{
            String q1="select role_name from roles where roleid= (select roleid from emp where uname=\""+aname+"\" and pwd=\""+apwd+"\")";
            System.out.println(q1);
            String rname=  jdbct.queryForObject(q1, String.class);
            System.out.println(rname);
            String q2="select roleid from roles where role_name=\""+rname+"\"";
            System.out.println(q2);
            String rname1=  jdbct.queryForObject(q2, String.class);
             System.out.println(rname1);
            if(!(rname.equals("admin")) &&  !(rname.equals("Hr")) )
            {
                return "You are not allowed.";
            }
            else
            {
                System.out.print(rname1);
                LocalDate myObj = LocalDate.now(); 
                LocalDate end_date=myObj.plusDays(435);
                long daysDifference = ChronoUnit.DAYS.between(myObj, end_date);
                String q4="insert into project(pname,pid,client,assignedby,start_date,end_date,duration,hrmanager,project_manager) values(?,?,?,?,?,?,?,?,?)";
                System.out.println(q4);
                int i=jdbct.update(q4,d.getPname(),d.getPid(),d.getClient(),rname1,myObj,end_date,daysDifference,d.getHr_manager(),d.getProject_manager()); 
                System.out.println(i);
                if(i>0)
                {
                    msg="Details are Inserted";
                }
                else
                {
                    msg="Details are not Inserted";
                }
            }
        }
        catch(Exception ex)
        {
            return "Invalid Admin credentials";
        }
        return msg;


    }


    public String addproj_emp(demo d){
        String aname=d.getUname();
        String apwd=d.getPwd();
        String msg="";
        try{
            String q1="select role_name from roles where roleid= (select roleid from emp where uname=\""+aname+"\" and pwd=\""+apwd+"\")";
            String rname=  jdbct.queryForObject(q1, String.class);
            if(!(rname.equals("project Manager")) &&  !(rname.equals("Hr")) )
            {
                return "You are not allowed.";
            }
            else
            {
                String q2="select fname from emp where roleid=4";
                List<String> r1 = jdbct.queryForList(q2, String.class);
                System.out.println(q2);
                System.out.println(r1);               
                String q4="insert into project_emp(empid,pid,assign_date,emp_lead,assignedby) values(?,?,?,?,?)";
                System.out.println(d.getAssigneddate());
                int i=jdbct.update(q4,d.getEid(),d.getPid(),d.getAssigneddate(),r1.get(0),rname); 
                if(i>0)
                {
                    msg="Details are Inserted";
                }
                else
                {
                    msg="Details are not Inserted";
                }
            }
        }
        catch(Exception ex)
        {
            return "Invalid Admin credentials";
        }
        return msg;
    }

    public Map<Integer, List<String>> details() {
    Map<Integer, List<String>> projectEmployeeMap = new HashMap<>();
    try {
        String q1 = "select * from project";
        List<Map<String, Object>> projects = jdbct.queryForList(q1);
        for (Map<String, Object> project : projects) {
            int pid = (Integer) project.get("pid");
            String q2 = "select emp.fname from emp, project_emp where emp.empid = project_emp.empid and project_emp.pid = ?";
            List<String> employees = jdbct.queryForList(q2, String.class, pid);
            projectEmployeeMap.put(pid, employees);
        }
    } catch (Exception ex) {
        ex.printStackTrace();
    }
    return projectEmployeeMap;
}

public String tasklist(demo d){
    String msg=" ";
    LocalDate date=LocalDate.now();
    LocalDate s=date.plusDays(80/8);
    /*create table tasklist(tid int,tname varchar(25),createdby date,tassignedby varchar(25),assigned_date varchar(25),start_date varchar(25),duration long,status varchar(5),tdesc varchar(150));
 */
    String q1="insert into tasklist(tid,tname,createdby,tassignedby,assigned_date,start_date,duration,status,tdesc)values(?,?,?,?,?,?,?,?,?)";
    int i=jdbct.update(q1,d.getTid(),d.getTname(),date,d.getAssignedto(),d.getAssigneddate(),d.getStart_date(),s,d.getStatus(),d.getDescription());
    if(i>0)
                {
                    msg="Details are Inserted";
                }
                else
                {
                    msg="Details are not Inserted";
                }
    return msg;
}
public List select_PDetails(demo e){
        List l = new ArrayList<>();
        List<Map<String,Object>> data = new ArrayList<Map<String,Object>>();
        String sql= "select * from project where pid=?";
        String sql2 = "select emp.fname from emp, project_emp where emp.empid = project_emp.empid and project_emp.pid = ?";
        data = jdbct.queryForList(sql,e.getPid());
        List<Map<String, Object>> data1 = jdbct.queryForList(sql2,e.getPid());
        List<String> employeeNames = new ArrayList<>();
            for (Map<String, Object> emp : data1) {
                employeeNames.add((String) emp.get("fname"));
            }
        for (Map m : data) {
            Map<String, Object> ma = new LinkedHashMap<>();
            ma.put("ProjectName", m.get("pname"));
            ma.put("ProjectId", m.get("pid"));
            ma.put("Client", m.get("clients"));
            ma.put("StartDate", m.get("start_date"));
            ma.put("EndDate", m.get("end_date"));
            ma.put("Duration", m.get("duration"));
            ma.put("Hrmanager", m.get("hrmanager"));
            ma.put("ProjectManager", m.get("project_manager"));
            ma.put("Employees",employeeNames);
            l.add(ma);
        }
        return l;
        
        }
        

}
