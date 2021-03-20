package domain;

public class Designer extends Programmer {
    private double bonus;

    public Designer() {
        super();
    }

    public Designer(int id, String name, int age, double salary, Equipment equipment, double bonus) {
        super(id, name, age, salary, equipment);
        this.bonus = bonus;
    }

    public double getBonus() {
        return bonus;
    }

    public void setBonus(double bonus) {
        this.bonus = bonus;
    }

    @Override
    public String toString() {
        return super.getDetails() + "\t\t设计师\t\t" + getStatus() + "\t" + bonus + "\t\t\t\t" + getEquipment().getDescription();
    }

    public String getDetailsForTeam() {
        return getMemberId() + "/" + getId() + "\t\t" + getName() + "\t" + getAge() + "\t\t" + getSalary() + "\t设计师\t" + bonus;
    }
}
