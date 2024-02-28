package annaire.example.annuaire_client_lourd.dao;

import annaire.example.annuaire_client_lourd.models.SalariesDTO;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;

public class SalariesDAO {
    public List<SalariesDTO> getAllSalaries() {
        StringBuilder response = new StringBuilder();

        try {
            URL url = new URL("http://localhost:5678/api/v1/salaries");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8)); // Spécifiez UTF-8 comme encodage
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
            } else {
                response.append("Erreur de réponse de l'API. Code : ").append(responseCode);
            }

        } catch (IOException e) {
            e.printStackTrace();
            response.append("Erreur lors de l'appel de l'API : ").append(e.getMessage());
        }

        return parseJsonArray(response.toString());
    }

    private List<SalariesDTO> parseJsonArray(String jsonArray) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(jsonArray, new TypeReference<List<SalariesDTO>>() {});
        } catch (IOException e) {
            e.printStackTrace();
            // Gérer les erreurs de parsing ici
            return Collections.emptyList();
        }
    }

    public static SalariesDTO getSalariesById(int salariesId) {
        StringBuilder response = new StringBuilder();

        try {
            URL url = new URL("http://localhost:5678/api/v1/salaries/"+salariesId);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                // On spécifie l'encodage UTF-8 pour que les accents soient gérés et s'affichent bien dans le client lourd
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8));
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
            } else {
                response.append("Erreur de réponse de l'API. Code : ").append(responseCode);
            }

        } catch (IOException e) {
            e.printStackTrace();
            response.append("Erreur lors de l'appel de l'API : ").append(e.getMessage());
        }
        SalariesDTO apiSalaries = parseJsonString(response.toString());
        return apiSalaries;
    }

    public static SalariesDTO parseJsonString(String jsonString) {
        try {
            // Utilisation de Jackson ObjectMapper pour mapper la chaîne JSON vers un objet Java
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(jsonString, SalariesDTO.class);
        } catch (Exception e) {
            e.printStackTrace();
            // TODO Gérer les erreurs de parsing ici
            return null;
        }
    }

    //méthode pour supprimer un salarié via son id
    public static void deleteSalaries(int id) {
        try {
            URL url = new URL("http://localhost:5678/api/v1/salaries/"+id);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("DELETE");

            int responseCode = connection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                System.out.println("Client supprimé avec succès.");
            } else {
                System.out.println("Erreur de réponse de l'API. Code : " + responseCode);
            }

            // Ferme la connexion
            connection.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
            // TODO Gérer les erreurs ici
        }

    }

    public static String createSalaries(String table, String body){
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:5678/api/v1/" + table))
                .setHeader("Content-Type","application/json")
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .build();
        HttpResponse<String> response = null;
        try {
            response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return response.body();
    }

    public static String updateSalaries(int id, String body){
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:5678/api/v1/salaries/" + id))
                .setHeader("Content-Type","application/json")
                .PUT(HttpRequest.BodyPublishers.ofString(body))
                .build();
        HttpResponse<String> response = null;
        try {
            response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return response.body();
    }
}
