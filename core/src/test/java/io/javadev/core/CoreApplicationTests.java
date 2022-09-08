package io.javadev.core;

import io.javadev.core.domain.Task;
import io.javadev.core.domain.User;
import io.javadev.core.repository.TaskJpaRepository;
import io.javadev.core.repository.UserJpaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CoreApplicationTests {

	@LocalServerPort
	private int serverPort;
	@Autowired
	TaskJpaRepository taskRepository;
	@Autowired
	UserJpaRepository userRepository;

	private final User user1, user2, user3, user4, user5;
	private final Task task1, task2, task3, task4, task5;

	public CoreApplicationTests() {

		user1 = new User("User001", "John Taragarien","Show");
		user2 = new User("User002", "","Hodor");
		user3 = new User("User003", "John Dow","");
		user4 = new User("User004", null,"");
		user5 = new User("User005", "","Targarien");

		task1 = new Task("1001", "some data 1001", user1);
		task2 = new Task("1002", "", user2);
		task3 = new Task("1003", "some data 1003", user3);
		task4 = new Task("1004", null, user1);
		task5 = new Task("1005", "some data 1005", null);
	}

	@BeforeEach
	public void setUpTestData() {
		userRepository.saveAll(List.of(user1, user2, user3, user4, user5));
		taskRepository.saveAll(List.of(task1, task2, task3, task4, task5));
	}

	@Test
	void RetrieveTasksWithQueryParam() {

		// retrieve all tasks
		RestTemplate restTemplate = new RestTemplate();
		String baseUrl = "http://127.0.0.1:" + serverPort + "/api/tasks";
		ResponseEntity<Task[]> response = restTemplate.getForEntity(baseUrl, Task[].class);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertNotNull(response.getBody());
		List<Task> responseBody = Arrays.asList(response.getBody());
		assertEquals(4, responseBody.size());
		// asset task list doesn't contain task with a null acquiredBy value
		assertTrue(responseBody.stream().anyMatch(taskVO -> !Objects.equals(taskVO.getId(), task5.getId())));
	}

	@Test
	void RetrieveTasksWithTaskDataQueryParam() {
		RestTemplate restTemplate = new RestTemplate();
		String baseUrl = "http://127.0.0.1:" + serverPort + "/api/tasks?taskData=" + "some+data";
		ResponseEntity<Task[]> response = restTemplate.getForEntity(baseUrl, Task[].class);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertNotNull(response.getBody());
		List<Task> responseBody = Arrays.asList(response.getBody());
		assertEquals(2, responseBody.size());
		assertEquals(List.of(task1, task3), responseBody);
	}

	@Test
	void RetrieveTasksWithUserIdQueryParam() {
		RestTemplate restTemplate = new RestTemplate();
		String baseUrl = "http://127.0.0.1:" + serverPort + "/api/tasks?userId=" + user2.getId();
		ResponseEntity<Task[]> response = restTemplate.getForEntity(baseUrl, Task[].class);
		assertNotNull(response.getBody());
		List<Task> responseBody = Arrays.asList(response.getBody());
		assertEquals(1, responseBody.size());
		assertEquals(List.of(task2), responseBody);
	}

	@Test
	void RetrieveTasksWithTaskIdPathVariable() {
		RestTemplate restTemplate = new RestTemplate();
		String baseUrl = "http://127.0.0.1:" + serverPort + "/api/tasks/" + task1.getId();
		ResponseEntity<Task> response = restTemplate.getForEntity(baseUrl, Task.class);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertNotNull(response.getBody());
		assertEquals(task1, response.getBody());
	}

	@Test
	void ReturnsHttpNotFoundWhenProvidingInvalidTaskId() {
		RestTemplate restTemplate = new RestTemplate();
		String baseUrl = "http://127.0.0.1:" + serverPort + "/api/tasks/" + "1015";
		try {
			restTemplate.getForEntity(baseUrl, String.class);
		} catch (HttpClientErrorException e) {
			assertEquals(HttpStatus.NOT_FOUND, e.getStatusCode());
		}
	}

}
