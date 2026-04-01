package dev.KKFujita.cadastroBanco.ClientesBanco.Controller;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientDTO {

    private Long id;

    @NotBlank(message = "O nome não pode estar em branco")
    @Size(min = 3, max = 100, message = "O nome deve ter entre 3 e 100 caracteres")
    private String nome;

    @NotNull(message = "A idade é obrigatória")
    @Min(value = 1, message = "A idade mínima permitida é 1 ano")
    @Max(value = 120, message = "Idade inválida")
    private Integer idade;

    @NotBlank(message = "O telefone é obrigatório")
    @Pattern(regexp = "^\\(?\\d{2}\\)?\\s?\\d{4,5}-?\\d{4}$", message = "Formato de telefone inválido. Use (XX) XXXXX-XXXX")
    private String numeroTelefone;

    @NotBlank(message = "O e-mail é obrigatório")
    @Email(message = "Insira um endereço de e-mail válido")
    private String email;

    @NotBlank(message = "O CPF é obrigatório")
    // Opcional: Adicionar Regex de CPF se quiser validar o formato 000.000.000-00
    private String CPF;

    @NotBlank(message = "O RG é obrigatório")
    private String RG;
}
