package org.testTask;

import lombok.Data;

@Data
public class ADDRObj {
    private String objectID, name, address, typeName, activeParent;
//    private int isActual;
//    private int isActive;
    public ADDRObj(String objectID, String name, String typeName) {
        this.objectID = objectID;
        this.name = name;
        this.typeName = typeName;
    }

    public ADDRObj(){}

    @Override
    public String toString() {
        return String.format("%s %s", typeName, name);
    }
//    public String toString() {
//        return address;
//    }

}
