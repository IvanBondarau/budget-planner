package by.malinovskaya.budgetplanner.backend.db;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;

@Component
@Scope("singleton")
public class SaltGeneratorImpl implements SaltGenerator {

    private final SecureRandom random = new SecureRandom();

    public byte[] generateSalt() {
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return salt;
    }
}
