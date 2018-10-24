package com.stackroute;

import com.stackroute.domain.Movie;
import com.stackroute.repository.MovieRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;




@SpringBootApplication
@EnableEurekaClient
public class MovieCruiserAppApplication /*implements ApplicationListener<ContextRefreshedEvent> , CommandLineRunner */{
    private static Logger logger = LoggerFactory.getLogger(MovieCruiserAppApplication.class);

	/*@Autowired
	MovieRepository movieRepository;
*/
/*	@Override
	public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
		Movie movie=new Movie("tt5568","http://google.com","Butler","Nice",7.3,2015);
		movieRepository.save(movie);
	}
	@Override
	public void run(String... args) throws Exception {
		Movie movie=new Movie("tt5569","http://google.com","Ted","Nice",7.2,2014);
		movieRepository.save(movie);
	}*/
	public static void main(String[] args) {

		SpringApplication.run(MovieCruiserAppApplication.class, args);
	}
}
