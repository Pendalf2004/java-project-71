package hexlet.code;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class DataCompare {
    public static Map<String, Fields> getDiff(
        Map<String, Object> firstDataSet, Map<String, Object> secondDataSet) {
        HashMap<String, Fields> resultMap = new HashMap<String, Fields>();
        var keyMap = new HashMap<String, Object>(firstDataSet);
        keyMap.putAll(secondDataSet);
        keyMap.keySet().forEach(key -> {
            Fields tmpFields = new Fields(firstDataSet.get(key), secondDataSet.get(key));
            if ((firstDataSet.containsKey(key)) && !(secondDataSet.containsKey(key))) {
                tmpFields.setKeyStatus(Fields.STATUS.REMOVED);
            }
            if (!(firstDataSet.containsKey(key)) && (secondDataSet.containsKey(key))) {
                tmpFields.setKeyStatus(Fields.STATUS.ADDED);
            }
            if (tmpFields.getKeyStatus() == Fields.STATUS.CHANGED) {
                if (Objects.equals(tmpFields.getOldValue(), tmpFields.getNewValue())) {
                    tmpFields.setKeyStatus(Fields.STATUS.UNCHANGED);
                }
            }
            resultMap.put(key, tmpFields);
        });
        return resultMap;
    }
}
