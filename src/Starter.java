import adt.implementations.AdtContainerFactory;
import adt.interfaces.AdtArray;
import adt.interfaces.AdtList;

import java.util.Random;

/**
 * Created by Seven on 07.07.2016.
 */
public class Starter {
    public static void main(String[] args) {
        AdtList list = AdtContainerFactory.adtSingleConcatList();

        for (int i = 1; i <= 10; i++) {
            list.insert(i, i);
        }

        System.out.println(list);
    }
}
