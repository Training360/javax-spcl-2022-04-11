package mentoringgateway.controller;

public class EmployeeDto {

    private long id;

    private String name;

    private long roleId;

    public EmployeeDto() {
    }

    public EmployeeDto(long id, String name, long roleId) {
        this.id = id;
        this.name = name;
        this.roleId = roleId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getRoleId() {
        return roleId;
    }

    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }
}
