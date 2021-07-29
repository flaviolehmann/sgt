package br.com.basis.sgt;

import br.com.basis.sgt.domain.Responsavel;
import br.com.basis.sgt.domain.Tarefa;
import br.com.basis.sgt.repository.ResponsavelRepository;
import br.com.basis.sgt.repository.TarefaRepository;
import br.com.basis.sgt.service.dto.ResponsavelDTO;
import br.com.basis.sgt.service.dto.TarefaDTO;
import br.com.basis.sgt.service.mapper.ResponsavelMapper;
import br.com.basis.sgt.service.mapper.TarefaMapper;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.transaction.Transactional;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Transactional
public class ResponsavelResourceIT {

    public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(
            MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(), StandardCharsets.UTF_8);

    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;

    private Long idInexistente;

    @BeforeEach
    public void setUp() {
        idInexistente = 1000000000000000000L;
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                /*.apply(SecurityMockMvcConfigurers.springSecurity())*/.build();
    }

    /**
     * Atribuir o valor do <code>pWebApplicationContext</code> no atribuito <code>webApplicationContext</code> da Classe.
     *
     * @param pWebApplicationContext Parâmetro de Entrada
     */
    @Autowired
    public void setWebApplicationContext(WebApplicationContext pWebApplicationContext) {
        webApplicationContext = pWebApplicationContext;
    }

    /**
     * Retorna o valor do atributo <code>mockMvc</code> do Objeto.
     *
     * @return O atributo <code>mockMvc</code>
     */
    protected MockMvc getMockMvc() {
        return mockMvc;
    }

    public static byte[] convertObjectToJsonBytes(Object object)
            throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        JavaTimeModule module = new JavaTimeModule();
        mapper.registerModule(module);

        return mapper.writeValueAsBytes(object);
    }

    @Autowired
    private ResponsavelRepository responsavelRepository;

    @Autowired
    private ResponsavelMapper responsavelMapper;

    @BeforeEach

    private void limparBanco() {
        responsavelRepository.deleteAll();
    }
@Test
   private Long salvarResponsavel(ResponsavelDTO responsavelDTO) {
     Responsavel save = responsavelRepository.save(responsavelMapper.toEntity(responsavelDTO));
     return save.getId();
    }

    @Test
    public void criarResponsavel() throws Exception {
        ResponsavelDTO responsavelDTO = getResponsavelDTO();

        getMockMvc().perform(
                post("/api/responsavel")
                        .contentType(APPLICATION_JSON_UTF8)
                        .content(convertObjectToJsonBytes(responsavelDTO))
        ).andExpect(status().isOk());
    }

    @Test
    public void obterPorIdComSucesso() throws Exception {
        Long idResponsavel = salvarResponsavel(getResponsavelDTO());

        getMockMvc().perform(
                get("/api/responsavel/" + idResponsavel)
                        .contentType(APPLICATION_JSON_UTF8)
        ).andExpect(status().isOk());
    }

    @Test
    public void obterPorIdComError() throws Exception {
        salvarResponsavel(getResponsavelDTO());
        getMockMvc().perform(
                get("/api/responsavel/" + Long.MAX_VALUE)
                        .contentType(APPLICATION_JSON_UTF8)
        ).andExpect(status().isNotFound());
    }



    @Test
    public void deletetar() throws Exception{
        Long idResponsavel = salvarResponsavel(getResponsavelDTO());
        getMockMvc().perform(
                delete("/api/responsavel/"+ idResponsavel)
                        .contentType(APPLICATION_JSON_UTF8)
        ).andExpect(status().isOk());
    }
    @Test
    public void obterTodosSemResponsavel() throws Exception {
        salvarResponsavel(getResponsavelDTO());
        getMockMvc().perform(

                get("/api/responsavel/" )
                        .contentType(APPLICATION_JSON_UTF8)
        ).andExpect(status().isOk());
    }

    @Test
    public void obterTodossResponsaveis() throws Exception {
        salvarResponsavel(getResponsavelDTO());
        getMockMvc().perform(
                get("/api/responsavel/?setor=sdad")
                        .contentType(APPLICATION_JSON_UTF8)
        ).andExpect(status().isOk());
    }

    @Test
    public void obterTodossResponsaveisSemSetor() throws Exception {
        salvarResponsavel(getResponsavelDTO());
        getMockMvc().perform(
                get("/api/reponsavel/")
                        .contentType(APPLICATION_JSON_UTF8)
        );
    }






@Test
    private ResponsavelDTO getResponsavelDTO() {
        ResponsavelDTO responsavelDTO = new ResponsavelDTO();
        responsavelDTO.setId(1L);
        responsavelDTO.setSetor("descr");
        responsavelDTO.setResponsavel(new ArrayList<>());
        return responsavelDTO;
    }

    @Test
    public void deletetarComId() throws Exception{
        Long idResponsavel = salvarResponsavel(getResponsavelDTO());
        getMockMvc().perform(
                delete("/api/responsavel/"+ idResponsavel)
                        .contentType(APPLICATION_JSON_UTF8)
        ).andExpect(status().isOk());
    }

}