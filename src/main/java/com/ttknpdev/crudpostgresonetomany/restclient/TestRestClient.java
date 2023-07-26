package com.ttknpdev.crudpostgresonetomany.restclient;

import com.ttknpdev.crudpostgresonetomany.entities.Student;
import com.ttknpdev.crudpostgresonetomany.entities.Subject;
import com.ttknpdev.crudpostgresonetomany.log.Logging;
import org.apache.log4j.Level;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.*;

/*
  RestTemplate is a class within the Spring framework we will understand how to use RestTemplate for invoking REST APIs of different shapes.
  Once the above spring application is up and running,
  test all Spring rest APIs and logging results with the below Spring rest client
  (Making an HTTP GET Request to Obtain the JSON Response)
  to honestly we can use them(this class) instead Postman! ???
* */
public class TestRestClient extends Logging {
    private static final String GET_READS = "http://localhost:8080/api/reads";
    private static final String GET_READS2 = "http://localhost:8080/api/subject/reads/{code}";
    private static final String GET_READ = "http://localhost:8080/api/read/{code}";
    private static final String PUT_UPDATE = "http://localhost:8080/api/update/{code}";
    private static final String PUT_UPDATE2 = "http://localhost:8080/api/subject/update/{code}/{no}";
    private static final String DELETE_DELETE = "http://localhost:8080/api/delete/{code}";
    private static final String DELETE_DELETE2 = "http://localhost:8080/api/subject/delete/{code}/{no}";
    private static final String POST_CREATE = "http://localhost:8080/api/create";
    private static final String POST_CREATE2 = "http://localhost:8080/api/subject/create/{code}";
    private RestTemplate restTemplate = new RestTemplate();
    // RestTemplate is a synchronous client to perform HTTP requests.
    // RestTemplate class is used to create applications that consume REST api
    // RestTemplate adds the capability of transforming the request and response in JSON or XML to Java objects.
    // RestTemplate provides higher-level methods for each of the HTTP methods which make it easy to invoke RESTful services.
    // For example, the method getForObject() will perform a GET and return an object.
    public static void main(String[] args) {

        new TestRestClient().deleteSubject();

    }
    /* we use logic below because we set response like object it's not only entity
    *  we have to define headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON)); in header http */
    private void reads() {
        // Use HttpHeaders to set the Request Headers.
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        // Use HttpEntity to wrap the request object.
        HttpEntity< String > entity = new HttpEntity<>("parameters", headers);
        // exchange(): executes a specified HTTP method, such as GET, POST, PUT, etc, and returns a ResponseEntity containing both the HTTP status code and the resource as an object.
        ResponseEntity< String > students = restTemplate.exchange(GET_READS , HttpMethod.GET , entity , String.class);
        testRestClient.log(Level.INFO,"after request by exchange() method");
        testRestClient.log(Level.INFO,"students store "+students);
        // students store <200 OK OK,
        // {"status":202,"code":"accepted","info":
        // [{"code":10000,"fullname":"peter parker","weight":66.6,"height":169.9,"age":23,"status":true,
        //   "subjects":[{"fullname":"calculus 1","score":65,"grade":"C"}
        //   ,{"fullname":"english 1 B","score":82,"grade":"A+"}]}
        // ]},[Content-Type:"application/json", Transfer-Encoding:"chunked", Date:"Sat, 22 Jul 2023 16:09:52 GMT", Keep-Alive:"timeout=60", Connection:"keep-alive"]>
    }

    private void readsSubject() {
        Map<String,Long> params = new HashMap<>();
        params.put("code" , 10000L);// key id value 1 for taking on path reads/{id}
        // Use HttpHeaders to set the Request Headers.
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        // Use HttpEntity to wrap the request object.
        HttpEntity< String > request = new HttpEntity<>("parameters", headers);
        // exchange(): executes a specified HTTP method, such as GET, POST, PUT, etc, and returns a ResponseEntity containing both the HTTP status code and the resource as an object.
        ResponseEntity< String > response = restTemplate.exchange(GET_READS2 , HttpMethod.GET , request , String.class , params);
        testRestClient.log(Level.INFO,"after request by exchange() method");
        testRestClient.log(Level.INFO,"subject store "+response);
        // students store <200 OK OK,
        // {"status":202,"code":"accepted","info":
        // [{"code":10000,"fullname":"peter parker","weight":66.6,"height":169.9,"age":23,"status":true,
        //   "subjects":[{"fullname":"calculus 1","score":65,"grade":"C"}
        //   ,{"fullname":"english 1 B","score":82,"grade":"A+"}]}
        // ]},[Content-Type:"application/json", Transfer-Encoding:"chunked", Date:"Sat, 22 Jul 2023 16:09:52 GMT", Keep-Alive:"timeout=60", Connection:"keep-alive"]>
    }

    private void read() {
        Map<String,Long> params = new HashMap<>();
        params.put("code" , 10000L);// key id value 1 for taking on path read/{id}
        // Use HttpHeaders to set the Request Headers.
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        // Use HttpEntity to wrap the request object.
        HttpEntity< String > entity = new HttpEntity<>("parameters", headers);
        // exchange(): executes a specified HTTP method, such as GET, POST, PUT, etc, and returns a ResponseEntity containing both the HTTP status code and the resource as an object.
        ResponseEntity< String > student = restTemplate.exchange(GET_READ , HttpMethod.GET , entity ,String.class, params); // can add some parameter
        testRestClient.log(Level.INFO,"after request by exchange() ** have args in path http ** method");
        testRestClient.log(Level.INFO,"student store "+student);
        // student store <200 OK OK,{"status":202,"code":"accepted","info":{"code":10000,"fullname":"peter parker","weight":66.6,"height":169.9,"age":23,"status":true,"subjects":[{"fullname":"calculus 1","score":65,"grade":"C"},{"fullname":"english 1 B","score":82,"grade":"A+"}]}},[Content-Type:"application/json", Transfer-Encoding:"chunked", Date:"Sat, 22 Jul 2023 17:05:40 GMT", Keep-Alive:"timeout=60", Connection:"keep-alive"]>
    }

    private void create() {
        // Use HttpHeaders to set the Request Headers.
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON)); // importance for returning like json object

        Subject subject1 = new Subject("english 1 B",(short) 60,"D+");
        Subject subject2 = new Subject("calculus 1",(short) 79,"B+");
        Subject subject3 = new Subject("data structure and algorithm",(short) 82,"A");
        List<Subject> subjects = new ArrayList<>();
        subjects.add(subject1); subjects.add(subject2); subjects.add(subject3);
        Student student1 = new Student("stone ryder",62.60F,165.60F,(short)20,false, subjects);

        // Create the request body by wrapping
        // the object in HttpEntity
        HttpEntity< Student > request = new HttpEntity<>(student1);
        // exchange(): executes a specified HTTP method, such as GET, POST, PUT, etc, and returns a ResponseEntity containing both the HTTP status code and the resource as an object.
        ResponseEntity< String > response = restTemplate.exchange(POST_CREATE , HttpMethod.POST , request , String.class); // can add some entity
        testRestClient.log(Level.INFO,"after request by exchange() ** have args in body http ** method");
        testRestClient.log(Level.INFO,"student store "+response);
    }

    private void createSubject() {
        Map<String,Long> params = new HashMap<>();
        params.put("code" , 10000L);// key id value 1 for taking on path create/{code}

        // Use HttpHeaders to set the Request Headers.
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON)); // importance for returning like json object

        Subject subject = new Subject("english 1 A",(short) 85,"A");
        // Create the request body by wrapping
        // the object in HttpEntity
        HttpEntity< Subject > request = new HttpEntity<>(subject);
        // exchange(): executes a specified HTTP method, such as GET, POST, PUT, etc, and returns a ResponseEntity containing both the HTTP status code and the resource as an object.
        ResponseEntity< String > response = restTemplate.exchange(POST_CREATE2 , HttpMethod.POST , request , String.class , params); // can add some entity and path variable
        testRestClient.log(Level.INFO,"after request by exchange() ** have args in body http and args path ** method");
        testRestClient.log(Level.INFO,"response store "+response);
    }
    private void update() {
        Map<String,Long> params = new HashMap<>();
        params.put("code" , 10002L);// key id value 1 for taking on path update/{code}
        // Use HttpHeaders to set the Request Headers.
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON)); // importance for returning like json object
        Student student = new Student("alex sandler",70.7F,170.5F,(short)21,false);
        // Create the request body by wrapping
        // the object in HttpEntity
        HttpEntity< Student > request = new HttpEntity<>(student);
        // exchange(): executes a specified HTTP method, such as GET, POST, PUT, etc, and returns a ResponseEntity containing both the HTTP status code and the resource as an object.
        ResponseEntity< String > response = restTemplate.exchange(PUT_UPDATE , HttpMethod.PUT , request , String.class , params); // can use void.class for which returns an empty response body
        testRestClient.log(Level.INFO,"after request by exchange() ** have args in body http ** method");
        testRestClient.log(Level.INFO,"response store "+response);
    }
    private void updateSubject() {
        Map<String,Long> params = new HashMap<>();
        params.put("code" , 10000L);//
        params.put("no" , 12L);//
        // Use HttpHeaders to set the Request Headers.
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON)); // importance for returning like json object
        Subject subject = new Subject("english 1 A",(short)40 ,"F");
        // Create the request body by wrapping
        // the object in HttpEntity
        HttpEntity< Subject > request = new HttpEntity<>(subject);
        // exchange(): executes a specified HTTP method, such as GET, POST, PUT, etc, and returns a ResponseEntity containing both the HTTP status code and the resource as an object.
        ResponseEntity< String > response = restTemplate.exchange(PUT_UPDATE2 , HttpMethod.PUT , request , String.class , params); // it'll retrieve following the order in params have two args
        testRestClient.log(Level.INFO,"after request by exchange() ** have args in body http ** method");
        testRestClient.log(Level.INFO,"response store "+response);
    }
    private void delete() {
        Map<String,Long> params = new HashMap<>();
        params.put("code" , 10004L);// key id value 1 for taking on path update/{code}
        // Use HttpHeaders to set the Request Headers.
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON)); // importance for returning like json object
        // Use HttpEntity to wrap the request object.
        HttpEntity< String > request = new HttpEntity<>("parameters", headers);
        // exchange(): executes a specified HTTP method, such as GET, POST, PUT, etc, and returns a ResponseEntity containing both the HTTP status code and the resource as an object.
        ResponseEntity< String > response = restTemplate.exchange(DELETE_DELETE , HttpMethod.DELETE , request , String.class , params); // can use void.class for which returns an empty response body
        testRestClient.log(Level.INFO,"after request by exchange() ** have args in body http ** method");
        testRestClient.log(Level.INFO,"response store "+response);
    }

    private void deleteSubject() {
        Map<String,Long> params = new HashMap<>();
        params.put("code" , 10000L);
        params.put("no" , 12L);
        // Use HttpHeaders to set the Request Headers.
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON)); // importance for returning like json object
        // Use HttpEntity to wrap the request object.
        HttpEntity< String > request = new HttpEntity<>("parameters", headers);
        // exchange(): executes a specified HTTP method, such as GET, POST, PUT, etc, and returns a ResponseEntity containing both the HTTP status code and the resource as an object.
        ResponseEntity< String > response = restTemplate.exchange(DELETE_DELETE2 , HttpMethod.DELETE , request , String.class , params);
        testRestClient.log(Level.INFO,"after request by exchange() ** have args in body http ** method");
        testRestClient.log(Level.INFO,"response store "+response);
    }
}
