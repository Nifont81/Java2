package Work3.Phone;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

public class PhoneBook {
    HashMap<String, LinkedList<Person>> book;

    public PhoneBook() {
        book = new HashMap<>();
    }

    public void add(String fio, String phone, String email) {
        LinkedList<Person> personLst = book.get(fio);
        if (personLst == null) {
            personLst = new LinkedList<>();
            personLst.add(new Person(phone, email));
            book.put(fio, personLst);
        } else personLst.add(new Person(phone, email));
    }

    public void print(){
        System.out.println("[Телефонная книга]");
        Iterator<Map.Entry<String, LinkedList<Person>>> iter = book.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry<String,LinkedList<Person>> entry = iter.next();
            System.out.println(entry.getKey()+" - "+entry.getValue());
        }
    }

    public LinkedList<String> findPhone(String fio){
        LinkedList<String> phoneLst = new LinkedList<>();
        LinkedList<Person> personLst = book.get(fio);
        if (personLst==null) return null;

        Iterator<Person> iter = personLst.iterator();
        while (iter.hasNext()) {
            phoneLst.add(iter.next().getPhone());
        }
        return  phoneLst;
    }

    public LinkedList<String> findMail(String fio){
        LinkedList<String> mailLst = new LinkedList<>();
        LinkedList<Person> personLst = book.get(fio);
        if (personLst==null) return null;

//        Iterator<Person> iter = personLst.iterator();
//        while (iter.hasNext()) {
//            mailLst.add(iter.next().getEmail());
//        }
        for (Person person: personLst) {
            mailLst.add(person.getEmail());
        }
        return  mailLst;
    }
}
