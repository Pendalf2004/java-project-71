package hexlet.code;

import java.util.HashMap;
import java.util.Map;

public class DataCompare {
    //comparing received file Maps
    public static Map<String,Fields> getDiff(Map<String, Object> firstData, Map<String, Object> secondData){
    /*creating resulting map
     (key - keys from file,
     Fields - oldValue - value from first file
            - newValue - value from second file
            - keyStatus - what happened with value)*/
        HashMap<String, Fields> resultMap = new HashMap<String, Fields>();
    //gather all keys
        var keyMap = new HashMap<String, Object>(firstData);
        keyMap.putAll(secondData);
    //fill the map
        keyMap.keySet().forEach(key -> {
    //creating tmp value variable
            Fields tmpFields = new Fields();

            tmpFields.oldValue = firstData.get(key);
            tmpFields.newValue = secondData.get(key);
            if (tmpFields.newValue == null) {
                tmpFields.keyStatus = Fields.status.REMOVED;
            } else if (tmpFields.oldValue == null) {
                tmpFields.keyStatus = Fields.status.ADDED;
            } else if (tmpFields.oldValue.equals(tmpFields.newValue)) {
                tmpFields.keyStatus = Fields.status.UNCHANGED;
            } else tmpFields.keyStatus = Fields.status.CHANGED;
            resultMap.put(key, tmpFields);
        });
        return resultMap;
    }
}
