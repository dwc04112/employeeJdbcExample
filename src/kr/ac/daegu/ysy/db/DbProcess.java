package kr.ac.daegu.ysy.db;

import kr.ac.daegu.ysy.core.Menu;
import kr.ac.daegu.ysy.input.InputUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DbProcess {
    private static final String DB_URL = "jdbc:mariadb://localhost:3306/EMPLOYEES";
    private static final String DB_USER = "root";
    private static final String DB_PW = "0000";

    private final String selectedNumber;

    // 생성자를 명시적으로 지정하면 기본생성자 DbProcess()는 호출될 수 없다. (따로 명시 해야 한다.)
    public DbProcess(String selectedNumber) {
        this.selectedNumber = selectedNumber;
    }

    public void startProcessing() throws SQLException { //예외처리
        // Connection, PreparedStatement, ResultSet은 interface 객체이다.
        Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PW);
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        switch (this.selectedNumber) {
            case Menu.SELECT:
                showAllStudents(conn, pstmt, rs);
                break;
            case Menu.UPDATE:
                List<Employee> employees = showAllStudents(conn, pstmt, rs);
                System.out.println("수정할 사원번호 입력");

                int emIdToUpdate = InputUtil.getIdFromStudentList(employees);
                System.out.println("수정할 사원 번호:" + emIdToUpdate);

                Employee updateEmp = Employee.buildEmployee();
                //------------------------------------------0809-------

                //수정한 내용
                //1. Update Employees의 대소문자 문제 오류
                //employees 로 고쳐서 해결하고
                //2. where id 로 입력되어있는 부분을 where emp_no로 바꿔줌
                //3. hire_date 가 hire_data로 입력되어있어서 오류발생
                pstmt = conn.prepareStatement("UPDATE employees SET birth_date=?, first_name=?, last_name =?, gender=?, hire_date=? where emp_no=?");
                pstmt.setString(1, updateEmp.getBirth_date());
                pstmt.setString(2, updateEmp.getFirst_name());
                pstmt.setString(3, updateEmp.getLast_name());
                pstmt.setString(4, updateEmp.getGender());
                pstmt.setString(5, updateEmp.getHire_date());
                pstmt.setInt(6, emIdToUpdate);
                pstmt.executeUpdate();
                System.out.println("업데이트를 완료했습니다");
                break;
        }

    }

    private List<Employee> showAllStudents(Connection conn, PreparedStatement pstmt, ResultSet rs) throws SQLException{
        List<Employee> sList = new ArrayList<>();




        pstmt = conn.prepareStatement("select * from employees where emp_no between ? and ?");
        //?가 들어간 쿼리에 어떤값을 넣을건지  > 동적 쿼리
        int a,b;
        do{
            System.out.print("첫번째 입력 : ");
            Scanner num1 = new Scanner(System.in);
            a= num1.nextInt();
            System.out.print("두번째 입력 : ");
            Scanner num2 = new Scanner(System.in);
            b= num2.nextInt();
            System.out.println("첫번째 값이 두번째 값보다 클 수 없습니다");
            System.out.println("범위를 다시 지정해주세요.");
            //===========0810 오후 수정해야할부분=============//
        }while(a >= b);
        System.out.println("사원번호 "+ a +" 부터 " + b + " 까지 출력합니다.");

        pstmt.setInt(1,a);
        pstmt.setInt(2,b);
        rs = pstmt.executeQuery();
        /* 쿼리날린 결과를 가지고 콘솔에 출력한다. */
        while(rs.next()){
            System.out.println(
                    rs.getInt(1)    + " | "
                            + rs.getString(2) + " | "
                            + rs.getString(3)    + " | "
                            + rs.getString(4) + " | "
                            + rs.getString(5) + " | "
                            + rs.getString(6));
            sList.add(new Employee(rs.getInt(1), rs.getString(2),
                    rs.getString(3), rs.getString(4),
                    rs.getString(5), rs.getString(6)));
        }
        return sList;
    }
}
