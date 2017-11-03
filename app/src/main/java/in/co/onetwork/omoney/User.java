package in.co.onetwork.omoney;

/**
 * Created by abhi on 30/9/17.
 */

public class User {
    String uid,acc,name,age,address,email;
    double money;
    User(){

    }
    User(String uid,String acc, String name, String age, String address, String email,double money){
        this.uid=uid;
        this.name=name;
        this.age=age;
        this.address=address;
        this.email=email;
        this.acc=acc;
        this.money=money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public double getMoney() {
        return money;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getAddress() {
        return address;
    }

    public String getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }

    public String getUid() {
        return uid;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAcc() {
        return acc;
    }

    public void setAcc(String acc) {
        this.acc = acc;
    }
}
