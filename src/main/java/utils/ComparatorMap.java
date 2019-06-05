package utils;

import java.util.*;

/**
 * Created by Dmytro Torlop
 * on 05.06.19
 */
public class ComparatorMap {

    public static HashMap sortByValuesDesc(HashMap map1) {
        List list1 = new LinkedList(map1.entrySet());

        list1.sort((obj1, obj2) -> ((Comparable) ((Map.Entry) (obj2)).getValue())
                .compareTo(((Map.Entry) (obj1)).getValue()));

        HashMap sortedHashMap1 = new LinkedHashMap();

        for (Iterator iter1 = list1.iterator(); iter1.hasNext(); ) {
            Map.Entry entry = (Map.Entry) iter1.next();
            sortedHashMap1.put(entry.getKey(), entry.getValue());
        }
        return sortedHashMap1;
    }
}
