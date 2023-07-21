package org.testTask;

import lombok.Data;

@Data
public class ADDRObj {
    private String objectID, name, typeName;

    public ADDRObj(String objectID, String name, String typeName) {
        this.objectID = objectID;
        this.name = name;
        this.typeName = typeName;
    }

    public ADDRObj(){}

    @Override
    public String toString() {
        return String.format("%s: %s %s",objectID, typeName, name);
    }
}
