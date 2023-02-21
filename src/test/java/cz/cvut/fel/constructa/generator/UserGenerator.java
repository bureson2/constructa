package cz.cvut.fel.constructa.generator;

import cz.cvut.fel.constructa.enums.Role;
import cz.cvut.fel.constructa.model.role.User;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class UserGenerator {

    private static final Random RAND = new Random();
    GregorianCalendar gc = new GregorianCalendar();



    public static int randomInt() {
        return RAND.nextInt();
    }

    public static int randBetween(int start, int end) {
        return start + (int)Math.round(Math.random() * (end - start));
    }
    public static Date getRandomDate() throws ParseException {
        GregorianCalendar gc = new GregorianCalendar();
        SimpleDateFormat formattedDate
                = new SimpleDateFormat("dd-MMM-yyyy");

        int year = randBetween(1980,2023);
        int dayOfYear = randBetween(1, gc.getActualMaximum(Calendar.DAY_OF_YEAR));
        gc.set(Calendar.YEAR, year);
        gc.set(GregorianCalendar.MONTH, 03);
        gc.set(Calendar.DAY_OF_YEAR, dayOfYear);

        String dateFormatted
                = formattedDate.format(
                gc.getTime());

        return new SimpleDateFormat("dd-MMM-yyyy").parse(dateFormatted);
    }

//    TODO better params
    public static User generateBasicUser() throws ParseException {
        String randomNumber = Integer.toString(randomInt());
        User user = new User();
        List<Role> roles = new ArrayList<>();
        roles.add(Role.EMPLOYEE);
        user.setRoles(roles);
        user.setEmail("test@gmail.com".concat(randomNumber));
        user.setUsername("username".concat(randomNumber));
        user.setFirstname("testname".concat(randomNumber));
        user.setLastname("testlastname".concat(randomNumber));
        user.setBirthId("0012127189");
        user.setDateOfBirth(getRandomDate());
        user.setBankAccount("0123456789");
        user.setDateOfAcceptance(getRandomDate());
        user.setHourRate(100);
        user.setPassword("password");
        return user;
    }


}
