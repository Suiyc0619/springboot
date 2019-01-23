package com.sui.study;

import com.sui.study.mapper.CourseMapper;
import com.sui.study.model.Course;
import com.sui.study.repository.CourseRepository;
import io.searchbox.client.JestClient;
import io.searchbox.core.Index;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.ConfigFileApplicationContextInitializer;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(initializers = ConfigFileApplicationContextInitializer.class)
public class StudyApplicationTests {

	@Autowired
	DataSource dataSource;

	@Autowired
	RedisTemplate<Object,Object> redisTemplate;

	@Autowired
	CourseMapper courseMapper;

	@Autowired
	RabbitTemplate rabbitTemplate;

	@Autowired
	AmqpAdmin amqpAdmin;

	@Autowired
	JestClient jestClient;

	@Autowired
	CourseRepository courseRepository;


	@Test
	public void contextLoads() {
		System.out.println(dataSource.getClass());
	}

	@Test
	public void redisTest(){
		Course course = courseMapper.selectCourse(1L);
		redisTemplate.opsForValue().set("course-1",course);
	}

	@Test
	public void rabbitTest(){
		Course course = courseMapper.selectCourse(2L);
		rabbitTemplate.convertAndSend("exchange.direct","atguigu.emps",course);
		System.out.println(System.currentTimeMillis());
	}

	@Test
	public void reciveRabbit(){
		Course o = (Course)rabbitTemplate.receiveAndConvert();
		System.out.println(o);
	}

	@Test
	public void createExchange(){
		amqpAdmin.declareExchange(new DirectExchange("sui"));
		amqpAdmin.declareQueue(new Queue("suiQueue",true));
		amqpAdmin.declareBinding(new Binding("suiQueue", Binding.DestinationType.QUEUE,"sui","suiQueue",null));
	}

	@Test
	public void jest(){
		Course course = courseMapper.selectCourse(2L);
		Index index = new Index.Builder(course).index("sui").type("course").build();
		try {
			jestClient.execute(index);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void jestSearch(){
		String json = "{\n" +
				"    \"query\" : {\n" +
				"        \"match\" : {\n" +
				"            \"name\" : \"1\"\n" +
				"        }\n" +
				"        \n" +
				"    }\n" +
				"}";
		Search search = new Search.Builder(json).addIndex("sui").addType("course").build();
		try {
			SearchResult result = jestClient.execute(search);
			System.out.println(result.getJsonString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void elasticTest(){
		Course course = courseMapper.selectCourse(1L);
		courseRepository.index(course);
	}
}


