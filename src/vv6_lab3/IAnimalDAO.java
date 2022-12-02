package bys6_lab3;

import java.util.List;
import javax.sql.DataSource;

public interface IAnimalDAO {
    void setDataSource(DataSource ds); // Установка связи с данныими
    void insert(Animal customer); // Вставка новой записи
    void append(String nameAnimal, String colorAnimal); // Добавление новой записи
    void append(String nameAnimal);
    void deleteByColorAnimal(String colorAnimal); // Удаление записи по фамилии
    void delete(String nameAnimal, String colorAnimal); // Удаление записи с указанными названием и цветом
    void delete(String nameAnimal);
    void deleteAll(); // Удаление всех запией
    void update(String oldColorAnimal, String newColorAnimal); // Изменение записей в таблице
    List<Animal> findByNameAnimal(String nameAnimal); // Получение записей с заданным названием 
    List<Animal> select(String nameAnimal, String colorAnimal); // Получение записей с заданными названием и цветом
    List<Animal> selectAll(); // Получение всех записей
}
