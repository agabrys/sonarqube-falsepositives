package biz.gabrys.agabrys.sonarqube.falsepositives.d20180809;

public class TrailingCommentCheck {

    private final String name;
    private final String surname;
    private final int age;

    public TrailingCommentCheck(final String name, final String surname, final int age) {
        this.name = name;
        this.surname = surname;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return new StringBuilder()//
                .append("name: ")//
                .append(name)//
                .append(", surname: ")//
                .append(surname)//
                .append(", age: ")//
                .append(age).toString();
    }
}
