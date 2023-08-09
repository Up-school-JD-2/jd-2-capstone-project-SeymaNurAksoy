package io.upschool.dto.airline;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AirlineSaveRequest {

    @NotBlank
    @Size(min = 2, max = 300,message = "İsim alanı minimum 2 maksimum 300 karater olabilir.")
    private String name;

}
