package br.com.basis.sgt;

import br.com.basis.sgt.domain.Comentario;
import br.com.basis.sgt.domain.Tarefa;
import br.com.basis.sgt.repository.ComentarioRepository;
import br.com.basis.sgt.repository.TarefaRepository;
import br.com.basis.sgt.service.dto.ComentarioDTO;
import br.com.basis.sgt.service.dto.TarefaDTO;
import br.com.basis.sgt.service.mapper.ComentarioMapper;
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
public class ComentarioResourceIT {

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
    private ComentarioRepository comentarioRepository;

    @Autowired
    private ComentarioMapper comentarioMapper;

    @BeforeEach

    @Test
    private void limparBanco() {
        comentarioRepository.deleteAll();
    }
@Test
   private Long salvarComentario(ComentarioDTO comentarioDTO) {
     Comentario save = comentarioRepository.save(comentarioMapper.toEntity(comentarioDTO));
     return save.getId();
    }



    @Test
    public void criarComentario() throws Exception {
        ComentarioDTO ComentarioDTO = getComentarioDTO();

        getMockMvc().perform(
                post("/api/comentario")
                        .contentType(APPLICATION_JSON_UTF8)
                        .content(convertObjectToJsonBytes(ComentarioDTO))
        ).andExpect(status().isOk());
    }

    @Test
    public void obterPorIdComSucesso() throws Exception {
        Long idComentario = salvarComentario(getComentarioDTO());

        getMockMvc().perform(
                get("/api/comentario/" + idComentario)
                        .contentType(APPLICATION_JSON_UTF8)
        ).andExpect(status().isOk());
    }


    @Test
    public void obterPorIdComError() throws Exception {
        salvarComentario(getComentarioDTO());
        getMockMvc().perform(
                get("/api/comentario/" + Long.MAX_VALUE)
                        .contentType(APPLICATION_JSON_UTF8)
        ).andExpect(status().isNotFound());
    }
@Test
    private ComentarioDTO getComentarioDTO() {
        ComentarioDTO comentarioDTO = new ComentarioDTO();
        comentarioDTO.setId(1L);

        comentarioDTO.setDescricao("descr");


        comentarioDTO.setComentarios(new ArrayList<>());
        return comentarioDTO;
    }


    @Test
    public void deletetarComentario() throws Exception{
        Long idComentario = salvarComentario(getComentarioDTO());
        getMockMvc().perform(
                delete("/api/comentario/"+ idComentario)
                        .contentType(APPLICATION_JSON_UTF8)
        ).andExpect(status().isOk());
    }
    @Test
    public void obterTodosSem() throws Exception {
        salvarComentario(getComentarioDTO());
        getMockMvc().perform(

                get("/api/comentarios/" + idInexistente)
                        .contentType(APPLICATION_JSON_UTF8)
        ).andExpect(status().isNotFound());
    }

    @Test
    public void obterTodoss() throws Exception {
        salvarComentario(getComentarioDTO());
        getMockMvc().perform(
                get("/api/comentarios/?descricacao=sdad")
                        .contentType(APPLICATION_JSON_UTF8)
        );
    }




}