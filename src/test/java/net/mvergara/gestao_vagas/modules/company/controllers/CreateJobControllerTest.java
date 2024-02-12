package net.mvergara.gestao_vagas.modules.company.controllers;

import net.mvergara.gestao_vagas.exceptions.CompanyNotFoundException;
import net.mvergara.gestao_vagas.modules.company.dto.CreateJobDTO;
import net.mvergara.gestao_vagas.modules.company.entities.CompanyEntity;
import net.mvergara.gestao_vagas.modules.company.respsitories.CompanyRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.UUID;

import static net.mvergara.gestao_vagas.utils.TestUtils.generateToken;
import static net.mvergara.gestao_vagas.utils.TestUtils.objectToJSON;
import static org.assertj.core.api.Java6Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class CreateJobControllerTest {

    private MockMvc mvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private CompanyRepository companyRepository;

    @Before
    public void setup() {
        // Cria a estrutura para rodar os testes de integração
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(SecurityMockMvcConfigurers.springSecurity())
                .build();
    }

    @Test
    public void should_create_a_new_job() throws Exception {

        // H2 database
        var company = CompanyEntity.builder()
                .description("COM_DESC")
                .email("email@com.com")
                .password("123412341234")
                .username("COMP_USERNAME")
                .name("COM_NAME")
                .build();
        company = companyRepository.saveAndFlush(company);

        var createJobDTO = CreateJobDTO.builder()
                .benefits("BENEFITS_TEST")
                .description("DESCRIPTION_TEST")
                .level("LEVEL_TEST")
                .build();

        var result = mvc.perform(
                        MockMvcRequestBuilders.post("/company/job/")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectToJSON(createJobDTO))
                                .header("Authorization", generateToken(company.getId(), "secretqueninguémsabeoqueé"))
                )
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void should_not_create_new_job_if_company_not_found() throws Exception {
        var createJobDTO = CreateJobDTO.builder()
                .benefits("BENEFITS_TEST")
                .description("DESCRIPTION_TEST")
                .level("LEVEL_TEST")
                .build();

        mvc.perform(MockMvcRequestBuilders.post("/company/job/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectToJSON(createJobDTO))
                        .header("Authorization", generateToken(UUID.randomUUID(), "secretqueninguémsabeoqueé"))
                )
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
}
