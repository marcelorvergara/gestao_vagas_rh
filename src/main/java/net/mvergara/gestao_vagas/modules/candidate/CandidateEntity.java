package net.mvergara.gestao_vagas.modules.candidate;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;
import java.util.UUID;
@Data
@Entity(name = "candidate")
public class CandidateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Schema(hidden = true)
    private UUID id;
    @Schema(example = "Marcelo Vergara", requiredMode = Schema.RequiredMode.REQUIRED, description = "Nome do candidato")
    private String name;
    @NotBlank()
    @Pattern(regexp = "\\S+", message="O campo [username] não deve conter espaço")
    @Schema(example = "mrvergara", requiredMode = Schema.RequiredMode.REQUIRED, description = "Username do candidato")
    private String username;
    @Email(message = "O campo [email] deve conter um e-mail válido")
    @Schema(example = "mrvergara@globo.com", requiredMode = Schema.RequiredMode.REQUIRED, description = "Email do candidato")
    private String email;
    @Length(min = 10, max = 100, message = "O campo [password] deve ter entre 10 e 100 caracteres")
    @Schema(example = "Admin@123456", minLength = 10, maxLength = 100, requiredMode = Schema.RequiredMode.REQUIRED, description = "Senha do candidato")
    private String password;
    @Schema(example = "Desenvolvedor Java", description = "Breve descrição do candidato")
    private String description;
    private String curriculum;

    @CreationTimestamp
    @Schema(hidden = true)
    private LocalDateTime createdAt;
}
