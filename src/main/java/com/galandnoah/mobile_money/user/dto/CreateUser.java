package com.galandnoah.mobile_money.user.dto;

import com.galandnoah.mobile_money.user.UserRole;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.hibernate.validator.constraints.Length;

public record CreateUser(
        @NotBlank(message = "Veuillez entrer votre nom")
        String name,

        @NotBlank(message = "Veuillez entre votre numéro de telephone")
        @Length(max = 9, min = 9, message = "Le numéro de telephone doit être composé de 9 chiffres")
        @Positive(message = "Le numéro de téléphone ne doit pas etre negatif")
        String phone,

        @NotBlank(message = "Veuillez créer un code pin de 5 chiffres")
        @Length(max = 5, min = 5, message = "Le code pin doit être compose de 5 chiffres")
        String pin,

        UserRole role
) {
}
