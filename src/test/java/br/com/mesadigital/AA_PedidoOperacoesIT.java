package br.com.mesadigital;

import br.com.mesadigital.config.TestContainerConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class AA_PedidoOperacoesIT extends TestContainerConfig {

    @Test
    @DisplayName("Altera o status do pedido para Pronto")
    void AA_AlterarStatusPedidoParaProntoDeveRetornar200() throws Exception {
        String idPedido = "32";
        mockMvc.perform(
                        patch("/operacoespedido/pronto/" + idPedido)
                                .contentType(MediaType.APPLICATION_JSON)
                ).andExpect(status().is2xxSuccessful());

    }


}
