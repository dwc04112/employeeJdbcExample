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

        }

    }

    private List<Employee> showAllStudents(Connection conn, PreparedStatement pstmt, ResultSet rs) throws SQLException{
        List<Employee> sList = new ArrayList<>();
        pstmt = conn.prepareStatement("select * from Employees where emp_no between 10001 and 10020");
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
