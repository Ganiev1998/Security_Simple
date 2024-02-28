package example.oddiysecurity.enums;

import java.util.List;

import static example.oddiysecurity.enums.Permission.*;

public enum Role {
    ADMIN(List.of(SELECT,CREATE,UPDATE,DELETE)),
    USER(List.of(SELECT));
    private List<Permission> permissions;
    Role(List<Permission> permissions){
        this.permissions = permissions;
    }
    public List<Permission> getPermissions(){
        return permissions;
    }
}
