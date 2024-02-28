package annaire.example.annuaire_client_lourd.dao;

import annaire.example.annuaire_client_lourd.models.SitesDTO;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.concurrent.Service;

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

public class SitesDAO {

    public static void deleteSites(int sitesId) {
        try {
            URL url = new URL("http://localhost:5678/api/v1/sites/"+sitesId);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("DELETE");

            int responseCode = connection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                System.out.println("Sites supprimé avec succès.");
            } else {
                System.out.println("Erreur de réponse de l'API. Code : " + responseCode);
            }

            connection.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
            // TODO Gérer les erreurs ici
        }

    }
    public List<SitesDTO> getAllSites() {
        StringBuilder response = new StringBuilder();

        try {
            URL url = new URL("http://localhost:5678/api/v1/sites");
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

    private List<SitesDTO> parseJsonArray(String jsonArray) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(jsonArray, new TypeReference<List<SitesDTO>>() {});
        } catch (IOException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    public static SitesDTO getSitesById(int sitesId) {
        StringBuilder response = new StringBuilder();

        try {
            URL url = new URL("http://localhost:5678/api/v1/sites/"+sitesId);
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
        SitesDTO apiSites = parseJsonString(response.toString());
        return apiSites;
    }

    public static SitesDTO parseJsonString(String jsonString) {
        try {
            // Utilisation de Jackson ObjectMapper pour mapper la chaîne JSON vers un objet Java
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(jsonString, SitesDTO.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String createSites(String table, String body){
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

    public static String updateSites(int id, String body){
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:5678/api/v1/sites/" + id))
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
