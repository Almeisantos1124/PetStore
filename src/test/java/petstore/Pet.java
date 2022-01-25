// 1- Pacote
package petstore;
// 2- Bibliotecas

import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;


// 3 -Classe
public class Pet {
    // 3.1 Atributos

    String uri ="https://petstore.swagger.io/v2/pet"; // endereço da entidade Pet


    // 3.2 Metodos e Funções
    public String lerJson(String caminhoJson) throws IOException {
            return new String(Files.readAllBytes(Paths.get(caminhoJson)));
    }

    //incluir  - create - Post
    @Test  //Identifica o metodo de test para o TestNG
    public void incluirPet() throws IOException {
        String jsonBody = lerJson ("db/pet1.json");

    // Sintaxe Gherkin
    // Giv
    // When
   //  Then

    given() //Dado
            .contentType("application/json")//conteudo em API REST
            .log().all()
            .body(jsonBody)
    .when() //Quando
            .post(uri)
    .then() // Então
            .log().all()
            .statusCode(200)

    ;

    }
}
