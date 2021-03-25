package service;

import domain.Architect;
import domain.Designer;
import domain.Employee;
import domain.Programmer;

/**
 * @Description 开发团队成员的管理：添加、删除
 */
public class TeamService {
    private static int counter = 1; // 给memberId赋值
    private final int MAX_MEMBER = 5; // 限制开发团队的人数
    private Programmer[] team = new Programmer[MAX_MEMBER]; // 保存开发团队成员
    private int total; // 记录开发团队中实际的人数

    public TeamService() {
        super();
    }

    /**
     * @return 现有开发团队中的所有成员
     * @Description 获取开发团队中的所有成员
     */
    public Programmer[] getTeam() {
        Programmer[] team = new Programmer[total];
        for (int i = 0; i < team.length; i++) {
            team[i] = this.team[i];
        }
        return team;
    }

    /**
     * @param employee
     * @Description 将指定的员工添加到开发团队中
     */
    public void addMember(Employee employee) throws TeamException {
        if (total >= MAX_MEMBER) {
            throw new TeamException("成员已满，无法添加");
        }
        if (!(employee instanceof Programmer)) {
            throw new TeamException("该成员不是开发成员，无法添加");
        }
        if (isExit(employee)) {
            throw new TeamException("该成员已在本开发团队中");
        }

        Programmer programmer = (Programmer) employee;
//        if ("BUSY".equals(programmer.getStatus().getSTATUS())) {
//            throw new TeamException("该员工已是某团队成员");
//        } else if ("VOCATION".equals(programmer.getStatus().getSTATUS())) {
//            throw new TeamException("该员工正在休假，无法添加");
//        }
        switch (programmer.getStatus()) {
            case BUSY:
                throw new TeamException("该员工已是某团队成员");
            case VOCATION:
                throw new TeamException("该员工正在休假，无法添加");
        }

        // 获取team已有成员中架构师，设计师，程序员的人数
        int numOfArchitect = 0, numOfDesigner = 0, numOfProgrammer = 0;
        for (int i = 0; i < total; i++) {
            if (team[i] instanceof Architect) {
                numOfArchitect++;
            } else if (team[i] instanceof Designer) {
                numOfDesigner++;
            } else if (team[i] instanceof Programmer) {
                numOfProgrammer++;
            }
        }

        if (programmer instanceof Architect) {
            if (numOfArchitect >= 1) {
                throw new TeamException("团队中至多只能有一名架构师");
            }
        } else if (programmer instanceof Designer) {
            if (numOfArchitect >= 2) {
                throw new TeamException("团队中至多只能有两名设计师");
            }
        } else if (programmer instanceof Programmer) {
            if (numOfArchitect >= 3) {
                throw new TeamException("团队中至多只能有三名程序员");
            }
        }

        team[total++] = programmer;
        programmer.setStatus(Status.BUSY);
        programmer.setMemberId(counter++);
    }

    /**
     * @param memberId
     * @Description 从团队中删除成员
     */
    public void removeMember(int memberId) throws TeamException {
        int i;
        for (i = 0; i < total; i++) {
            if (team[i].getMemberId() == memberId) {
                team[i].setStatus(Status.FREE);
                break;
            }
        }

        // 未找到指定memberId
        if (i == total) {
            throw new TeamException("找不到指定memberId的员工，删除失败");
        }

        for (int j = i; j < total - 1; j++) {
            team[j] = team[j + 1];
        }

        team[--total] = null;
    }

    /**
     * @param employee
     * @return 若在，则返回true；否则返回false
     * @Description 判断该员工是否已在团队中
     */
    public boolean isExit(Employee employee) {
        for (int i = 0; i < total; i++) {
            if (team[i].getId() == employee.getId()) {
                return true;
            }
        }
        return false;
    }
}
