package hexlet.code;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class DataCompare {
    //comparing received file Maps
    public static Map<String, Fields> getDiff(
            Map<String, Object> firstDataSet, Map<String, Object> secondDataSet) {
    /*creating resulting map
     (key - keys from files,
     Fields - oldValue - value from first file
            - newValue - value from second file
            - keyStatus - what happened with value)*/
        HashMap<String, Fields> resultMap = new HashMap<String, Fields>();
    //gather all keys
        var keyMap = new HashMap<String, Object>(firstDataSet);
        keyMap.putAll(secondDataSet);
    //fill the map
        keyMap.keySet().forEach(key -> {
            //creating tmp value variable
            Fields tmpFields = new Fields(firstDataSet.get(key), secondDataSet.get(key));
            if ((firstDataSet.containsKey(key)) && !(secondDataSet.containsKey(key))) {
                tmpFields.keyStatus = Fields.STATUS.REMOVED;
            }
            if (!(firstDataSet.containsKey(key)) && (secondDataSet.containsKey(key))) {
                tmpFields.keyStatus = Fields.STATUS.ADDED;
            }
            if (tmpFields.keyStatus == Fields.STATUS.CHANGED) {
                if (Objects.equals(tmpFields.oldValue, tmpFields.newValue)) {
                    tmpFields.keyStatus = Fields.STATUS.UNCHANGED;
                }
            }
            resultMap.put(key, tmpFields);
        });
        return resultMap;
    }
}
