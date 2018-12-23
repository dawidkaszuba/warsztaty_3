package pl.dawidkaszuba.model;


public class UserGroup {

    private Integer id;
    private String name;

    public UserGroup(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public UserGroup(String name) {
        this.name = name;
    }

    public UserGroup() {
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "UserGroup{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

}