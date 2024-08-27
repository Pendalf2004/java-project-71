package hexlet.code;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class DataCompare {
    public enum STATUS {
        UNCHANGED,
        REMOVED,
        ADDED,
        CHANGED
    }

    public STATUS getKeyStatus() {
        return keyStatus;
    }

    public void setKeyStatus(STATUS keyStatus) {
        this.keyStatus = keyStatus;
    }

    private STATUS keyStatus;


    private Object oldValue;
    public Object getOldValue() {
        return oldValue;
    }
    public void setOldValue(Object oldValue) {
        this.oldValue = oldValue;
    }

    private Object newValue;
    public Object getNewValue() {
        return newValue;
    }
    public void setNewValue(Object newValue1) {
        this.newValue = newValue1;
    }

    public DataCompare(Object value1, Object value2) {
        this.oldValue = value1;
        this.newValue = value2;
        this.keyStatus = STATUS.CHANGED;
    }

    public static Map<String, DataCompare> getDiff(
        Map<String, Object> firstDataSet, Map<String, Object> secondDataSet) {
        HashMap<String, DataCompare> resultMap = new HashMap<String, DataCompare>();
        var keyMap = new HashMap<String, Object>(firstDataSet);
        keyMap.putAll(secondDataSet);
        keyMap.keySet().forEach(key -> {
            DataCompare tmpFields = new DataCompare(firstDataSet.get(key), secondDataSet.get(key));
            if ((firstDataSet.containsKey(key)) && !(secondDataSet.containsKey(key))) {
                tmpFields.setKeyStatus(DataCompare.STATUS.REMOVED);
            }
            if (!(firstDataSet.containsKey(key)) && (secondDataSet.containsKey(key))) {
                tmpFields.setKeyStatus(DataCompare.STATUS.ADDED);
            }
            if (tmpFields.getKeyStatus() == DataCompare.STATUS.CHANGED) {
                if (Objects.equals(tmpFields.getOldValue(), tmpFields.getNewValue())) {
                    tmpFields.setKeyStatus(DataCompare.STATUS.UNCHANGED);
                }
            }
            resultMap.put(key, tmpFields);
        });
        return resultMap;
    }
}
