package kr.ac.daegu.ysy.input;

import kr.ac.daegu.ysy.core.Menu;
import kr.ac.daegu.ysy.db.Employee;
import org.apache.commons.lang.StringUtils;

import java.util.List;
import java.util.Scanner;

public class InputUtil {

    private static Scanner strSc = new Scanner(System.in);

    public static String getStringFromConsole(String defaultString) {
        String result = strSc.next().trim();
        if(result.equals("")){
            result = defaultString;
        }
        return result;
    }

    //===========================0810=====================
    public static int firstScanRange(int a) {
        System.out.print("첫번째 입력 : ");
        Scanner num1 = new Scanner(System.in);
        a= num1.nextInt();
        return a;
    }
    public static int lastScanRange(int b) {
        System.out.print("두번째 입력 : ");
        Scanner num2 = new Scanner(System.in);
        b= num2.nextInt();
        return b;
    }
    //값 비교하는 부분 0811에 수정해야함 -------------------
    /*
    public static int compareRange(int a,int b){
        do {
            System.out.println("첫번째 값이 두번째 값보다 클 수 없습니다");
            System.out.println("범위를 다시 지정해주세요.");
            System.out.print("첫번째 입력 : ");
            Scanner num1 = new Scanner(System.in);
            a= num1.nextInt();
            System.out.print("두번째 입력 : ");
            Scanner num2 = new Scanner(System.in);
            b= num2.nextInt();

            } while(a>=b);
        System.out.println("사원번호 " + a + " 부터 " + b + " 까지 출력합니다.");
        return a;
        //비교 > 메시지 출력 후
        //a값이 b값보다 클때 값을 다시 받고 dbProcess로 넘겨야하는데
        //넘겨야 할 값이 2개라서 return불가
    }

     */


    //=====================0810===================================




    public String validateUserInput() {
        String userInputString = null;           // 사용자가 입력한 데이터를 저장 하는 역할
        Scanner sc = new Scanner(System.in);     // 사용자의 입력을 받기 위한 Scanner 객체의 인스턴스를 불러온다. (인스턴스를 불러온다 -> 이 프로그램이 실행될 때 Scanner객체를 쓸 수 있도록 메모리에 적재 해라)
        do {
            printInfo();                         // 사용자의 안내를 출력하는 메소드
            userInputString = inputFromUserFromConsole(sc);
        } while (checkUserInputScope(userInputString)); // do - while 구문 : while 조건이 false 일때까지 do를 무한 반복한다.
        return userInputString;
    }

    private boolean checkUserInputScope(String inputString) {
        switch (inputString) {
            case Menu.SELECT: case Menu.INSERT: case Menu.UPDATE: case Menu.DELETE: return false;
            default :
                System.out.println("잘못 입력 하셨습니다. ");
                System.out.println("주어진 메뉴중 하나를 골라 주세요");
                return true;
        }
    }


    private String inputFromUserFromConsole(Scanner sc) {
            // private : 접근 제어자. 접근 : 해당 메소드가 선언된 클래스 이외의 다른 클래스에서 쓸수 있나 없나?
            return sc.next(); // 사용자의 입력을 받은 데이터를 String으로 반환한다.
        }





    // 콘솔에서 숫자 입력 받기!!!
    // 숫자 아닌 문자일 경우 경고 출력 하여 다시
    public static int getIntFromConsole() {
        String v = strSc.next();
        if (!isNumber(v)) {
            do {
                System.out.println("숫자를 입력해 주세요");
                v = strSc.next();
            } while (!isNumber(v));
        }
        return Integer.parseInt(v);
    }
    private static boolean isNumber(String v) {
        return StringUtils.isNumeric(v);
    }


    public static int getIdFromStudentList(List<Employee> employees) {
        int usrSelectedId; // 사용자로부터 입력받는 번호
        do {
            usrSelectedId = InputUtil.getIntFromConsole(); // 숫자만 입력받음.
            if(idIsExist(usrSelectedId, employees)){        // 존재하는 학생의 id인지?
                return usrSelectedId;
            } else {
                System.out.println("존재하지 않는 사원번호 입니다.  사원번호를 선택하시오.");
            }

            } while (!idIsExist(usrSelectedId, employees));
        // do - while문의 동작구조가 : while 조건절이 false가 뜰 때까지 do 구문을 반복한다.
            return usrSelectedId;
    }

    private static boolean idIsExist(int usrSelectedId, List<Employee> employeeIds) {
        for(Employee s: employeeIds){
            if(s.getEmp_no() == usrSelectedId){
                return true;
            }
        }
        return false;
    }












    private void printInfo() {
        System.out.println("아래 내용중 선택하세요.");
        System.out.println("1: 조회할 사원번호 범위를 지정해주세요");
        System.out.println("3: 사원번호 선택하여 변경");
    }
}
