package collection;

/**
 * Created by qiuw on 2017/10/27.
 */

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author qiuwei
 * @create 2017-10-27 17:00
 **/
public class AnalyseHashMap {
    public static void main(String[] args) {
        Map<Object, String> map = new HashMap<>();
        map.put("a", "aaa");
        map.put("b", "bbb");
        map.put("c", "ddd");
        map.put("e", null);
        map.put(new Country("china", "111111"),"eee");
        map.put(new Country("english", "222222"),"fff");
        Set<Map.Entry<Object, String>> entrySet = map.entrySet();
        for (Map.Entry<Object, String> entry : entrySet) {
            System.out.println("key: " + entry.getKey() + " value: " + entry.getValue());
        }
    }

    private static class Country{
        private String name;
        private String location;

        public Country(String name, String location) {
            this.name = name;
            this.location = location;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Country country = (Country) o;

            if (!name.equals(country.name)) return false;
            return location.equals(country.location);
        }

        @Override
        public int hashCode() {
            int result = name.hashCode();
            result = 31 * result + location.hashCode();
            return result;
        }
    }
}
