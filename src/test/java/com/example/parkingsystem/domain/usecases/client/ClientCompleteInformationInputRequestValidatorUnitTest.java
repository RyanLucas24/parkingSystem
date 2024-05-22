package com.example.parkingsystem.domain.usecases.client;

import com.example.parkingsystem.domain.model.client.Client;
import com.example.parkingsystem.domain.usecases.utils.Notification;
import com.example.parkingsystem.domain.usecases.utils.Validator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ClientCompleteInformationInputRequestValidatorUnitTest {
    private final Validator<Client> validator = new ClientCompleteInformationInputRequestValidator();
    private Client client;

    @BeforeEach
    public void createANewClient(){
        client = new Client("123-456-789", "ABC1D23");
        client = new Client(client,
                "João Da Silva",
                "(16)91234-5678",
                "joaozinho@email.com",
                "Rua aleatoria, 123");
    }
    @Test
    public void validate_ValidClient_SuccessfulValidation(){
        Notification validation = validator.validate(client);

        Assertions.assertTrue(validation.isCorrect());
    }
    @Test
    public void validate_NullClient_UnsuccessfulValidation(){
        client = null;

        Notification validation = validator.validate(client);

        Assertions.assertTrue(validation.hasErrors());
    }
    @Test
    public void validate_BlankNameClient_UnsuccessfulValidation(){
        client.setName("");

        Notification validation = validator.validate(client);

        Assertions.assertTrue(validation.hasErrors());
    }
    @Test
    public void validate_NullNameClient_UnsuccessfulValidation(){
        client.setName(null);

        Notification validation = validator.validate(client);

        Assertions.assertTrue(validation.hasErrors());
    }

    @Test
    public void validate_BlankPhoneClient_UnsuccessfulValidation(){
        client.setPhone("");

        Notification validation = validator.validate(client);

        Assertions.assertTrue(validation.hasErrors());
    }

    @Test
    public void validate_NullPhoneClient_UnsuccessfulValidation(){
        client.setPhone(null);

        Notification validation = validator.validate(client);

        Assertions.assertTrue(validation.hasErrors());
    }

    @Test
    public void validate_BlankEmailClient_UnsuccessfulValidation(){
        client.setEmail("");

        Notification validation = validator.validate(client);

        Assertions.assertTrue(validation.hasErrors());
    }
    @Test
    public void validate_NullEmailClient_UnsuccessfulValidation(){
        client.setEmail(null);

        Notification validation = validator.validate(client);

        Assertions.assertTrue(validation.hasErrors());
    }
    @Test
    public void validate_BlankAddressClient_UnsuccessfulValidation(){
        client.setAddress("");

        Notification validation = validator.validate(client);

        Assertions.assertTrue(validation.hasErrors());
    }
    @Test
    public void validate_NullAddressClient_UnsuccessfulValidation(){
        client.setAddress(null);

        Notification validation = validator.validate(client);

        Assertions.assertTrue(validation.hasErrors());
    }
}
