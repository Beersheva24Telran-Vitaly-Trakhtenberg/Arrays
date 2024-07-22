/*
Lesson #8
ClassWork #8
HomeWork #8
 */

package telran.utils;

public class Person implements Comparable<Person>
{
    private long id;
    private String name;

    public Person()
    {

    }

    public Person(String name)
    {
        this.name = name;
    }

    public Person(long id, String name)
    {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public int compareTo(Person other_person) {
        return Long.compare(getId(), other_person.getId());
    }

/*
    public void setId(long id) {
        this.id = id;
    }

    public void getName(String name) {
        this.name = name;
    }
*/
}
