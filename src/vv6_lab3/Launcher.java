package bys6_lab3;

import java.util.List;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Launcher {

    public static void main(String[] args) {
        try {
            ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml"); // Загрузка файла с биновами

            AnimalDAO animalDAO = (AnimalDAO) context.getBean("customerDAO"); // Загрузка бина доступа к таблице клиентов 

            animalDAO.deleteAll(); // Удаление

            Animal animal = new Animal("Собака", "Черная"); // Создание
            animalDAO.insert(animal);

            animalDAO.insert(new Animal("Собака", "Серая")); // Добавление
            animalDAO.insert(new Animal("Кот", "Белый"));
            
            /////////////////////
            animalDAO.append("Змея");
            /////////////////////
            
            animalDAO.deleteByColorAnimal("Бел"); // Удаление
            animalDAO.delete("Собака", "Серая");
            
            //////////////////////
            animalDAO.delete("Змея");
            /////////////////////
            
            List<Animal> animals = animalDAO.findByNameAnimal("ак");
            System.out.println(animals != null ? animals : "Нет данных");

            animalDAO.append("Лошадь", "Коричневая");
            animalDAO.append("Медведь", "Белый");
            animalDAO.append("Медведь", "Бурый");
            animalDAO.append("Паук", "Черный");

            animalDAO.update("Черный", "Красный");

            System.out.println("Данные в таблице БД:");

            List<Animal> list = animalDAO.selectAll();
            for (Animal myAnimal : list) {
                System.out.println(myAnimal.getNameAnimal() + " " + myAnimal.getColorAnimal());
            }

            System.out.println();
            System.out.println("Вывод записей с Лошадь и Коричневая:");

            list = animalDAO.select("лошадь", "коричневая");
            for (Animal myAnimal : list) {
                System.out.println(myAnimal.getNameAnimal() + " " + myAnimal.getColorAnimal());
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error!");
        }
    }

}
