package xatu20191222;

import java.io.*;

/**
 * Description:了解
 *
 * @author: KangWuBin
 * @Date: 2019/12/22
 * @Time: 16:37
 */
public class SerializableDemo {
    /*Serializable作用:持久化（长期保存）；传输（经过网络）；
    * */
    private static class Person implements Serializable {
        String name;
        int age;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        {
            Person person1 = new Person("KWB", 20);
            Person person2 = new Person("SY", 20);
            OutputStream os = new FileOutputStream("Person对象.bin");
//            OutputStream os = new FileOutputStream("Person对象.txt");
            ObjectOutputStream oos = new ObjectOutputStream(os);
            oos.writeObject(person1);
            oos.writeObject(person2);
            oos.close();
        }

        {
            InputStream is = new FileInputStream("Person对象.bin");
//            InputStream is = new FileInputStream("Person对象.txt");
            ObjectInputStream ois = new ObjectInputStream(is);
            Person p;
            p = (Person) ois.readObject();
            System.out.println(p);
            p = (Person) ois.readObject();
            System.out.println(p);
            ois.close();
        }
    }
}
