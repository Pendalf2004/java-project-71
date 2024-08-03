package hexlet.code;
//data class to store files key values and status change
public class Fields {
//field for the first file value(null if key is absent in the first file)
    //что делать если значение в файле было null, а не ключ отсутствовал в файле
    public Object oldValue;
//field for the second file value(null if key is absent in the second file)
    public Object newValue;
//value change status
    public enum STATUS {
        UNCHANGED,
        REMOVED,
        ADDED,
        CHANGED
    }
    public STATUS keyStatus;

    public Fields(Object value1, Object value2) {
        this.oldValue = value1;
        this.newValue = value2;
        this.keyStatus = STATUS.CHANGED;
    }

    //    public Fields() {
    //}
}
