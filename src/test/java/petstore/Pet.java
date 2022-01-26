// 1- Pacote
package petstore;
// 2- Bibliotecas

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.contains;


// 3 -Classe
public class Pet {
    // 3.1 Atributos
    String uri = "https://petstore.swagger.io/v2/pet"; // endere�o da entidade Pet

    // 3.2 Metodos e Fun��es
    public String lerJson(String caminhoJson) throws IOException {
        return new String(Files.readAllBytes(Paths.get(caminhoJson)));
    }

    //Incluir  - Create - Post
    @Test(priority = 1)  //Identifica o metodo ou fun��o de test para o TestNG
    public void incluirPet() throws IOException {
        String jsonBody = lerJson("db/pet1.json");

        // Sintaxe Gherkin
        // Giv (Dado)
        // When (Quando)
        //  Then (Ent�o)

        given() //Dado
                .contentType("application/json")//conteudo em API REST
                .log().all()
                .body(jsonBody)
                .when() //Quando
                .post(uri)
                .then() // Ent�o
                .log().all()
                .statusCode(200)
                .body("name", is("Nutella"))
                .body("status", is("available"))
                .body("category.name", is("KsljdljSJJD46444DD1"))
                .body("tags.name", contains("data"))
        ;

    }

    @Test(priority = 2)
    public void consultarPet() {
        String petId = "261120181";
        String token =

                given()
                        .contentType("application/json")
                        .log().all()
                        .when()
                        .get(uri + "/" + petId)
                        .then()
                        .log().all()
                        .statusCode(200)
                        .body("name", is("Nutella"))
                        .body("category.name", is("KsljdljSJJD46444DD1"))
                        .body("status", is("vendida"))
                        .extract()
                        .path("category.name");
        System.out.println("o token � " + token);


    }

    @Test(priority = 3)
    public void alterarPet() throws IOException {
        String jsonBody = lerJson("db/pet2.json");

        given()
                .contentType("application/json")
                .log().all()
                .body(jsonBody)
                .when()
                .put(uri)
                .then()
                .log().all()
                .statusCode(200)
                .body("name", is("Nutella"))
                .body("status", is("vendida"))
        ;
    }

    @Test(priority = 4)
    public void excluirPet() {
       String petId ="261120181";

        given()
                .contentType("application/json")
                .log().all()
        .when()
                .delete(uri +"/"+ petId)
        .then()
                .log().all()
                .statusCode(200)
                .body("code", is (200))
                .body("type", is("unknown"))
                .body("message", is(petId))
        ;
    }

}