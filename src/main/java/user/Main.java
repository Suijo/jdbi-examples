package user;

import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;

import java.time.LocalDate;
import java.util.Optional;

public class Main {

    public static void main(String[] args) {
        Jdbi jdbi = Jdbi.create("jdbc:h2:mem:test");
        jdbi.installPlugin(new SqlObjectPlugin());
        long id;
        User bori;
        User Andele = new User();
        Optional user;


        try (Handle handle = jdbi.open()) {
            UserDao dao = handle.attach(UserDao.class);
            dao.createTable();
            id = dao.insert(User.builder()
                    .username("bori")
                    .password("1234")
                    .name("Gergő")
                    .email("bori@gmail.com")
                    .gender(User.Gender.MALE)
                    .dob(LocalDate.of(1999,10,11))
                    .enabled(true).build());
            dao.insert(User.builder()
                    .username("Andele")
                    .password("12345")
                    .name("Adél")
                    .email("Andele@gmail.com")
                    .gender(User.Gender.FEMALE)
                    .dob(LocalDate.of(1999,3,31))
                    .enabled(true).build());







            System.out.println(dao.findById(1).get());

            System.out.println(dao.findByUsername("Andele").get());
            dao.delete("Andele");
            dao.list().stream().forEach(System.out::println);







        }
    }
}