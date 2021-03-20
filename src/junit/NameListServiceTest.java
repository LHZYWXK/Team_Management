package junit;

import domain.Employee;
import service.NameListService;
import service.TeamException;

/**
 * @Description 对NameListService类的测试
 */
public class NameListServiceTest {
//    public static void main(String[] args) {
//        NameListServiceTest nameListServiceTest = new NameListServiceTest();
//        nameListServiceTest.testGetAllEmployees();
//        nameListServiceTest.testGetEmployee();
//    }

    public void testGetAllEmployees() {
        NameListService nameListService = new NameListService();
        Employee[] employees = nameListService.getAllEmployees();
        for (Employee employee : employees) {
            System.out.println(employee);
        }
    }

    public void testGetEmployee() {
        NameListService nameListService = new NameListService();
        int id = 1;
        try {
            Employee employee = nameListService.getEmployee(id);
            System.out.println(employee);
        } catch (TeamException e) {
            System.out.println(e.getMessage());
        }
    }
}
