import adt.interfaces.AdtList;

import static adt.implementations.AdtContainerFactory.*;

/**
 * Created by Seven on 07.07.2016.
 */
public class Starter {
    private static final int[] VALUES = {100, 1_000, 10_000, 100_000};
    private static final String FILE_NAME = "results";
    private static final String FILE_FORMAT = "csv";
    private static final char DELIMETER = ';';

    public static void main(String[] args) {
        AdtList list = adtList();
    }
}
