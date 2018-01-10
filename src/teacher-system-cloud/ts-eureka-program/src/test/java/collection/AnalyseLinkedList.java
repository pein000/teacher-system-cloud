package collection;

/**
 * Created by qiuw on 2017/10/27.
 */

import java.util.LinkedList;
import java.util.List;

/**
 * @author qiuwei
 * @create 2017-10-27 16:04
 **/
public class AnalyseLinkedList {

    public static void main(String[] args) {
        List<String> list = new LinkedList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");
        for (int i = 0; i < list.size() ; i++) {
            System.out.println(list.get(i));
        }
    }

}
