package arimayuji.eletiva.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.boot.test.web.client.TestRestTemplate;
import com.fasterxml.jackson.core.JsonProcessingException;
import arimayuji.eletiva.domain.gateways.MusicRepository;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class MusicControllerTest {

  @Autowired
  private TestRestTemplate restTemplate;

  @Autowired
  private ObjectMapper objectMapper;

  @Autowired
  private MusicRepository musicRepository;

  @BeforeEach
  public void setup() {
  }

  @Test
  public void testRegisterMusicWithZeroReview() throws Exception {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    String body = "{\"musicName\": \"Nova Música\"}";
    HttpEntity<String> request = new HttpEntity<>(body, headers);

    ResponseEntity<String> response = restTemplate.postForEntity("/api/music", request, String.class);
    assertEquals(HttpStatus.CREATED, response.getStatusCode());

    Map<String, Object> music = objectMapper.readValue(response.getBody(), Map.class);
    assertEquals(0, music.get("review"));
  }

  @Test
  public void testMusicSortingByReview() throws Exception {
    assertMusicCreated("One");
    assertMusicCreated("Still");
    assertMusicCreated("Uncover");

    patchReview("One", 4);
    patchReview("Still", 3);
    patchReview("Uncover", 3);

    ResponseEntity<String> response = restTemplate.getForEntity("/api/music", String.class);
    assertEquals(HttpStatus.OK, response.getStatusCode());

    Map<String, Object> responseMap = objectMapper.readValue(response.getBody(), Map.class);
    List<Map<String, Object>> musicList = (List<Map<String, Object>>) responseMap.get("musics");
    assertNotNull(musicList);
    assertEquals(3, musicList.size());

    assertEquals("One", musicList.get(0).get("musicName"));
    assertEquals(4, musicList.get(0).get("review"));
    assertEquals("Still", musicList.get(1).get("musicName"));
    assertEquals(3, musicList.get(1).get("review"));
    assertEquals("Uncover", musicList.get(2).get("musicName"));
    assertEquals(3, musicList.get(2).get("review"));
  }

  private void assertMusicCreated(String musicName) throws JsonProcessingException {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    String body = String.format("{\"musicName\": \"%s\"}", musicName);
    HttpEntity<String> request = new HttpEntity<>(body, headers);
    ResponseEntity<String> response = restTemplate.postForEntity("/api/music", request, String.class);
    assertEquals(HttpStatus.CREATED, response.getStatusCode());

    ResponseEntity<String> getResponse = restTemplate.getForEntity("/api/music", String.class);
    Map<String, Object> responseMap = objectMapper.readValue(getResponse.getBody(), Map.class);
    List<Map<String, Object>> musicList = (List<Map<String, Object>>) responseMap.get("musics");
    assertTrue(musicList.stream().anyMatch(m -> musicName.equals(m.get("musicName"))));
  }

  private void patchReview(String musicName, int review) {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    String body = String.format("{\"review\": %d}", review);
    HttpEntity<String> request = new HttpEntity<>(body, headers);

    String encodedMusicName = URLEncoder.encode(musicName, StandardCharsets.UTF_8).replace("+", "%20");
    ResponseEntity<String> getResponse = restTemplate.getForEntity("/api/music", String.class);
    try {
      Map<String, Object> responseMap = objectMapper.readValue(getResponse.getBody(), Map.class);
      List<Map<String, Object>> musicList = (List<Map<String, Object>>) responseMap.get("musics");
    } catch (JsonProcessingException e) {
    }

    ResponseEntity<String> response = restTemplate.exchange(
        "/api/music/" + encodedMusicName + "/review",
        HttpMethod.PATCH,
        request,
        String.class);

    assertTrue(response.getStatusCode() == HttpStatus.OK || response.getStatusCode() == HttpStatus.NO_CONTENT,
        "Failed to patch review for music: " + musicName + ", status: " + response.getStatusCode());
  }
}
