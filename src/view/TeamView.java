package view;

import domain.Employee;
import domain.Programmer;
import service.NameListService;
import service.TeamException;
import service.TeamService;

public class TeamView {
    private NameListService listSvc = new NameListService();
    private TeamService teamSvc = new TeamService();

    public void enterMainMenu() {
        boolean flag = true;
        char menu = '0';

        while (flag) {
            if (menu != '1') {
                listAllEmployees();
            }

            System.out.print("1-团队列表\t2-添加团队成员\t3-删除团队成员\t4-退出\t\t请选择(1-4)：");
            menu = TSUtility.readMenuSelection();
            switch (menu) {
                case '1':
                    getTeam();
                    break;
                case '2':
                    addMember();
                    break;
                case '3':
                    deleteMember();
                    break;
                case '4':
                    System.out.print("确认是否退出(Y/N)：");
                    char confirm = TSUtility.readConfirmSelection();
                    if (confirm == 'Y')
                        flag = false;
                    break;
            }
        }
    }

    /**
     * @Description 显示所有的员工信息
     */
    private void listAllEmployees() {
        System.out.println("-------------------------------开发团队调度软件-------------------------------\n");

        Employee[] employees = listSvc.getAllEmployees();

        if (employees == null || employees.length == 0) {
            System.out.println("公司中没有任何员工信息！");
        } else {
            System.out.println("ID\t\t姓名\t\t年龄\t工资\t\t职位\t\t状态\t奖金\t\t股票\t领用设备");

            for (int i = 0; i < employees.length; i++) {
                System.out.println(employees[i]);
            }
        }

        System.out.println("----------------------------------------------------------------------------");
    }

    /**
     * @Description 查看开发团队情况
     */
    private void getTeam() {
        System.out.println("-------------------------------团队成员列表-------------------------------\n");

        Programmer[] team = teamSvc.getTeam();
        if (team == null || team.length == 0) {
            System.out.println("开发团队目前没有成员！");
        } else {
            System.out.println("TID/ID\t姓名\t年龄\t工资\t职位\t奖金\t股票\n");
            for (int i = 0; i < team.length; i++) {
                System.out.println(team[i].getDetailsForTeam());
            }
        }
        System.out.println("-------------------------------------------------------------------------");
    }

    /**
     * @Description 添加团队成员
     */
    private void addMember() {
        System.out.println("-------------------------------添加成员-------------------------------\n");
        System.out.println("请输入要添加的成员ID：");
        int id = TSUtility.readInt();

        try {
            Employee employee = listSvc.getEmployee(id);
            teamSvc.addMember(employee);
            System.out.println("添加成功");
        } catch (TeamException e) {
            System.out.println("添加失败，原因：" + e.getMessage());
        }
        TSUtility.readReturn();
    }

    /**
     * @Description 删除团队成员
     */
    private void deleteMember() {
        System.out.println("-------------------------------删除成员-------------------------------\n");
        System.out.println("请输入要删除的成员ID：");
        int memberId = TSUtility.readInt();

        System.out.println("确认是否删除(Y/N)：");
        char confirm = TSUtility.readConfirmSelection();
        if (confirm == 'N') {
            return;
        }
        try {
            teamSvc.removeMember(memberId);
            System.out.println("删除成功");
        } catch (TeamException e) {
            System.out.println("删除失败，原因：" + e.getMessage());
        }
        TSUtility.readReturn();
    }

    public static void main(String[] args) {
        TeamView teamView = new TeamView();
        teamView.enterMainMenu();
    }
}
