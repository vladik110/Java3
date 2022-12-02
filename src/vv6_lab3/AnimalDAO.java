package bys6_lab3;

import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

public class AnimalDAO implements IAnimalDAO {
    private DataSource dataSource;

    @Override
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void insert(Animal customer) { // Реализация вставки новой записи
        JdbcTemplate insert = new JdbcTemplate(dataSource);
        insert.update("INSERT INTO bys6_lab3 (NAME_ANIMAL, COLOR_ANIMAL) VALUES(?,?)",
                new Object[]{customer.getNameAnimal(), customer.getColorAnimal()});
    }

    @Override
    public void append(String nameAnimal, String colorAnimal) {  // Реализация добавления новой записи
        JdbcTemplate insert = new JdbcTemplate(dataSource);
        insert.update("INSERT INTO bys6_lab3 (NAME_ANIMAL, COLOR_ANIMAL) VALUES(?,?)", new Object[]{nameAnimal, colorAnimal});
    }

    @Override
    public void deleteByColorAnimal(String colorAnimal) {  // Реализация удаления записей по цвету
        JdbcTemplate insert = new JdbcTemplate(dataSource);
        insert.update("DELETE FROM bys6_lab3 WHERE COLOR_ANIMAL LIKE ?", new Object[]{'%' + colorAnimal + '%'});
    }

    @Override
    public void delete(final String nameAnimal, final String colorAnimal) {  // Реализация удаления записей с указанными названием и цветом
        TransactionTemplate transactionTemplate = new TransactionTemplate(new DataSourceTransactionManager(dataSource));

        transactionTemplate.execute(new TransactionCallback() {
            @Override
            public Object doInTransaction(TransactionStatus status) {

                try {
                    JdbcTemplate delete = new JdbcTemplate(dataSource);
                    delete.update("DELETE from bys6_lab3 where NAME_ANIMAL = ? AND COLOR_ANIMAL = ?", new Object[]{nameAnimal, colorAnimal});
                } catch (RuntimeException e) {
                    status.setRollbackOnly();
                    throw e;
                } catch (Exception e) {
                    status.setRollbackOnly();
                    throw new RuntimeException(e);
                }
                return null;
            }
        });
    }

    @Override

    public void deleteAll() {  // Реализация удаления всех запией
        JdbcTemplate delete = new JdbcTemplate(dataSource);
        delete.update("DELETE from bys6_lab3");
    }

    @Override
    public void update(String oldColorAnimal, String newColorAnimal) {  // Изменение записей в таблице
        JdbcTemplate update = new JdbcTemplate(dataSource);
        update.update("UPDATE bys6_lab3 SET COLOR_ANIMAL = ? WHERE COLOR_ANIMAL = ?", new Object[]{newColorAnimal, oldColorAnimal});
    }

    @Override
    public List<Animal> findByNameAnimal(String nameAnimal) {  // Реализация поиска записей по названию
        JdbcTemplate select = new JdbcTemplate(dataSource);
        String sql = "SELECT * FROM bys6_lab3 WHERE NAME_ANIMAL LIKE ?";
        List<Animal> animal = select.query(sql, new Object[]{'%' + nameAnimal + '%'}, new AnimalRowMapper());
        return animal;
    }

    @Override
    public List<Animal> select(String nameAnimal, String colorAnimal) {  // Реализация получения записей с заданными названием и цветом
        JdbcTemplate select = new JdbcTemplate(dataSource);
        return select.query("select  * from bys6_lab3 where NAME_ANIMAL = ? AND COLOR_ANIMAL= ?",
                new Object[]{nameAnimal, colorAnimal}, new AnimalRowMapper());
    }

    @Override
    public List<Animal> selectAll() {  // Реализация получения всех записей
        JdbcTemplate select = new JdbcTemplate(dataSource);
        return select.query("select * from bys6_lab3", new AnimalRowMapper());
    }

    @Override
    public void append (String nameAnimal) {  // Реализация добавления новой записи
        JdbcTemplate insert = new JdbcTemplate(dataSource);
        String colorAnimal = "Зеленая";
        insert.update("INSERT INTO bys6_lab3(NAME_ANIMAL, COLOR_ANIMAL) VALUES(?,?)", new Object[]{nameAnimal, colorAnimal});
    }
    
   @Override
    public void delete(final String nameAnimal) {  // Реализация удаления записей с указанными названием и цветом
        TransactionTemplate transactionTemplate = new TransactionTemplate(new DataSourceTransactionManager(dataSource));

        transactionTemplate.execute(new TransactionCallback() {
            @Override
            public Object doInTransaction(TransactionStatus status) {

                try {
                    JdbcTemplate delete = new JdbcTemplate(dataSource);
                    delete.update("DELETE from bys6_lab3 where NAME_ANIMAL = ?", new Object[]{nameAnimal});
                } catch (RuntimeException e) {
                    status.setRollbackOnly();
                    throw e;
                } catch (Exception e) {
                    status.setRollbackOnly();
                    throw new RuntimeException(e);
                }
                return null;
            }
        });
    }
}