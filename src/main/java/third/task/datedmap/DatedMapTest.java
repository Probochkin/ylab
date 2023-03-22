package third.task.datedmap;

public class DatedMapTest {
    public static void main(String[] args) {
        DatedMap datedMap = new DatedMapImpl();
        datedMap.put("test1", "test1key");
        datedMap.put("test2", "test2key");
        datedMap.put("test3", "test3key");
        datedMap.put("test4", "test4key");
        System.out.println(datedMap.get("test1"));
        System.out.println(datedMap.get("test2"));
        System.out.println(datedMap.getKeyLastInsertionDate("test1"));
        System.out.println(datedMap.getKeyLastInsertionDate("test2"));
        datedMap.put("test2", "test22key");
        System.out.println(datedMap.getKeyLastInsertionDate("test2"));
        datedMap.remove("test2");
        System.out.println(datedMap.getKeyLastInsertionDate("test2"));
    }
}
