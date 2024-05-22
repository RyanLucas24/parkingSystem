package com.example.parkingsystem.domain.usecases.client;

import com.example.parkingsystem.domain.model.client.Client;
import com.example.parkingsystem.domain.usecases.utils.Notification;
import com.example.parkingsystem.domain.usecases.utils.Validator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ClientBasicInformationInputRequestValidatorUnitTest {

    private final Validator<Client> validator = new ClientBasicInformationInputRequestValidator();

    @Test
    public void validate_ValidClient_SuccessfulValidation(){
        Client client = new Client("123-456-789", "ABC1D23");

        Notification validation = validator.validate(client);

        Assertions.assertTrue(validation.isCorrect());
    }
    @Test
    public void validate_BlankCPFClient_UnsuccessfulValidation(){
        Client client = new Client("", "ABC1D23");

        Notification validation = validator.validate(client);

        Assertions.assertTrue(validation.hasErrors());
    }

    @Test
    public void validate_NullCPFClient_UnsuccessfulValidation(){
        Client client = new Client(null, "ABC1D23");

        Notification validation = validator.validate(client);

        Assertions.assertTrue(validation.hasErrors());
    }

    @Test
    public void validate_BlankPlatesClient_UnsuccessfulValidation(){
        Client client = new Client("123-456-789", "");

        Notification validation = validator.validate(client);

        Assertions.assertTrue(validation.hasErrors());
    }
    @Test
    public void validate_NullPlatesClient_UnsuccessfulValidation(){
        Client client = new Client("123-456-789", null);

        Notification validation = validator.validate(client);

        Assertions.assertTrue(validation.hasErrors());
    }
}
