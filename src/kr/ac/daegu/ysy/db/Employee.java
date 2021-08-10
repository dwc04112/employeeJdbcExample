package kr.ac.daegu.ysy.db;

import kr.ac.daegu.ysy.input.InputUtil;

public class Employee {
    private int emp_no;
    private String birth_date;
    private String first_name;
    private String last_name;
    private String gender;
    private String hire_date;



    public Employee() {}
    public Employee(int emp_no, String birth_date, String first_name, String last_name, String gender
        ,String hire_date) {
        this.emp_no = emp_no;
        this.birth_date = birth_date;
        this.first_name = first_name;
        this.last_name = last_name;
        this.gender = gender;
        this.hire_date = hire_date;
    }


    public int getEmp_no(){
        return this.emp_no;
    }

    public String getBirth_date(){
        return this.birth_date;
    }

    public String getFirst_name() {
        return this.first_name;
    }

    public String getLast_name() {
        return this.last_name;
    }

    public String getGender() {
        return this.gender;
    }

    public String getHire_date(){
        return this.hire_date;
    }

    //---------------0809 오류 수정해야함
    public static Employee buildEmployee() {
        Employee s = new Employee();
        System.out.println("생년월일을 입력해주세요");
        System.out.println("YYYY-MM-DD 형식으로 입력");
        s.birth_date = InputUtil.getStringFromConsole("no date");

        System.out.println("first name을 입력해주세요");
        s.first_name = InputUtil.getStringFromConsole("no first_name");

        System.out.println("last name을 입력해주세요");
        s.last_name = InputUtil.getStringFromConsole("no last_name");

        System.out.println("성별을 입력해주세요");
        System.out.println("M/F");
        s.gender = InputUtil.getStringFromConsole("not selected");

        System.out.println("입사 날짜를 입력해주세요");
        System.out.println("YYYY-MM-DD 형식으로 입력");
        s.hire_date = InputUtil.getStringFromConsole("no date");
        return s;
    }

}
