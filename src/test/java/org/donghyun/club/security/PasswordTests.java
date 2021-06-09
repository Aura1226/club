package org.donghyun.club.security;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
@Log4j2
public class PasswordTests {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Test
    public void testEncode(){

        String password = "1111";

        //$2a$10$sR1yoBi7FQtTP1ppbp08N.FcYX4LDZygagVMLSG08i7zsATobDs0S
        //$2a$10$xEsvTe0lkXoVM8s38ziYquxacz7bfTYP7HCIzu0XfT9U4.cbsz9HK
        String enPw = passwordEncoder.encode(password);

        log.info(enPw);

    }
}
