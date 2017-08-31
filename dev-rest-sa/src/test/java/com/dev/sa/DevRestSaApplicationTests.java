package com.dev.sa;

import com.dev.sa.entity.Account;
import com.dev.sa.repository.AccountRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ComponentScan(basePackages = "com.dev.sa")
@ActiveProfiles("embeded")
@EnableJpaRepositories(basePackages="com.dev.sa.repository")
public class DevRestSaApplicationTests {

    @Autowired
    AccountRepository accountRepository;

    @Test
    public void Test() {

        Account account = new Account();
        account.setFirstName("sujit");
        account.setLastName("agarwal");
        account.setAccountNumber("12345678");
        accountRepository.save(account);
    }

}
