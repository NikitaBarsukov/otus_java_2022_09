package homework;


import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.google.errorprone.annotations.Immutable;

import java.util.AbstractMap;
import java.util.Map;
import java.util.TreeMap;

public class CustomerService {

    //todo: 3. надо реализовать методы этого класса
    //важно подобрать подходящую Map-у, посмотрите на редко используемые методы, они тут полезны

    TreeMap<Customer, String> map = new TreeMap<>();

    public Map.Entry<Customer, String> getSmallest() {
        //Возможно, чтобы реализовать этот метод, потребуется посмотреть как Map.Entry сделан в jdk
        Map.Entry<Customer, String> entry = map.firstEntry();
        if (entry == null) return null;
        return Map.entry(new Customer(entry.getKey().getId(), entry.getKey().getName(), entry.getKey().getScores()), entry.getValue());
    }

    public Map.Entry<Customer, String> getNext(Customer customer) {
        Map.Entry<Customer, String> entry = map.higherEntry(customer);
        if (entry == null) return null;
        return Map.entry(new Customer(entry.getKey().getId(), entry.getKey().getName(), entry.getKey().getScores()), entry.getValue());
    }

    public void add(Customer customer, String data) {
        map.put(customer, data);
    }
}
