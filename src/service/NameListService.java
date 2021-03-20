package service;

import domain.*;

import static service.Data.*;

/**
 * @Description 负责将Data中的数据封装到Employee[]数组中，同时提供相关操作Employee[]的方法
 */
public class NameListService {
    private Employee[] employees;

    /**
     * @Description 给employees及数组元素进行初始化
     */
    public NameListService() {
        employees = new Employee[EMPLOYEES.length];
        for (int i = 0; i < employees.length; i++) {
            // 获取类型
            int type = Integer.parseInt(EMPLOYEES[i][0]);

            // 获取基本信息
            int id = Integer.parseInt(EMPLOYEES[i][1]);
            String name = EMPLOYEES[i][2];
            int age = Integer.parseInt(EMPLOYEES[i][3]);
            double salary = Double.parseDouble(EMPLOYEES[i][4]);

            Equipment equipment;
            double bonus;
            int stock;

            switch (type) {
                case EMPLOYEE:
                    employees[i] = new Employee(id, name, age, salary);
                    break;
                case PROGRAMMER:
                    equipment = creatEquipment(i);
                    employees[i] = new Programmer(id, name, age, salary, equipment);
                    break;
                case DESIGNER:
                    equipment = creatEquipment(i);
                    bonus = Double.parseDouble(EMPLOYEES[i][5]);
                    employees[i] = new Designer(id, name, age, salary, equipment, bonus);
                    break;
                case ARCHITECT:
                    equipment = creatEquipment(i);
                    bonus = Double.parseDouble(EMPLOYEES[i][5]);
                    stock = Integer.parseInt(EMPLOYEES[i][6]);
                    employees[i] = new Architect(id, name, age, salary, equipment, bonus, stock);
                    break;
            }
        }
    }

    /**
     * @param index
     * @return 设备
     * @Description 获取指定index位置上的员工设备
     */
    private Equipment creatEquipment(int index) {
        int key = Integer.parseInt(EQUIPMENTS[index][0]);
        String modelOrName = EQUIPMENTS[index][1];

        switch (key) {
            case PC:
                String display = EQUIPMENTS[index][2];
                return new PC(modelOrName, display);
            case NOTEBOOK:
                double price = Double.parseDouble(EQUIPMENTS[index][2]);
                return new NoteBook(modelOrName, price);
            case PRINTER:
                String type = EQUIPMENTS[index][2];
                return new Printer(modelOrName, type);
        }
        return null;
    }

    /**
     * @return 员工数组
     * @Description 获取当前所有员工
     */
    public Employee[] getAllEmployees() {
        return employees;
    }

    /**
     * @param id
     * @return 该员工对象
     * @throws TeamException
     * @Description 获取指定ID的员工对象
     */
    public Employee getEmployee(int id) throws TeamException {
        for (Employee employee : employees) {
            if (employee.getId() == id) {
                return employee;
            }
        }
        throw new TeamException("找不到指定的员工");
    }
}
