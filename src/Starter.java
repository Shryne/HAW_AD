import adt.implementations.AdtContainerFactory;
import adt.interfaces.AdtArray;
import adt.interfaces.AdtList;

/**
 * Created by Seven on 07.07.2016.
 */
public class Starter {
    public static void main(String[] args) {
        AdtList list = AdtContainerFactory.adtList();

        for (int i = 1; i <= 2; i++) {
            list.insert(i, i);
            System.out.println(i);
        }

        System.out.println(list);
    }
}
