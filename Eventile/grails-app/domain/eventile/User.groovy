package eventile

class User {

    static constraints = {
        email(unique:true)
        password(password:true)

    }

    int age;
    String name;
    String email;
    String password;



}
