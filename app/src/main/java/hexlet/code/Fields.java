package hexlet.code;
public class Fields {
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

    public Fields(Object value1, Object value2) {
        this.oldValue = value1;
        this.newValue = value2;
        this.keyStatus = STATUS.CHANGED;
    }
}
