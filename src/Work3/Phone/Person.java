package Work3.Phone;

public class Person {
    String phone;
    String email;

    public Person(String phone, String email) {
        this.phone = phone;
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        //return super.toString();
        return String.format("тел.:%s, e-mail:%s",phone,email);
    }
}
