package hexlet.code;

import java.util.HashMap;
import java.util.Map;

public class DataCompare {
    //comparing received file Maps
    public static Map<String, Fields> getDiff(Map<String, Object> firstData, Map<String, Object> secondData) {
    /*creating resulting map
     (key - keys from files,
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
            Fields tmpFields = new Fields(firstData.get(key), secondData.get(key));
            if ((secondData.get(key) != null) && (
                    secondData.getOrDefault(key, "no such key").toString().equals("no such key"))) {
                tmpFields.keyStatus = Fields.STATUS.REMOVED;
            }
            if ((firstData.get(key) != null) && (
                    firstData.getOrDefault(key, "no such key").toString().equals("no such key"))) {
                tmpFields.keyStatus = Fields.STATUS.ADDED;
            }
            if (firstData.getOrDefault(key, "no such key in the first file").equals(
                    secondData.getOrDefault(key, "no such key in the second file"))) {
                tmpFields.keyStatus = Fields.STATUS.UNCHANGED;
            }
            resultMap.put(key, tmpFields);
        });
        return resultMap;
    }
}
