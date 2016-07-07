import adt.implementations.AdtContainerFactory;
import adt.interfaces.AdtArray;

/**
 * Created by Seven on 07.07.2016.
 */
public class Starter {
    public static void main(String[] args) {
        System.out.println("HEY");
        AdtArray array = AdtContainerFactory.adtArray();
        array.set(0, 15);
        System.out.println(array.get(0));
    }
}
