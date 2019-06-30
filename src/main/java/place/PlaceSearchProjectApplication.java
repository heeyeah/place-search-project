package place;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;

import org.springframework.web.client.RestTemplate;
import place.common.CommonUtil;
import place.entity.UserInfo;
import place.repository.DataRepository;

@SpringBootApplication
public class PlaceSearchProjectApplication {

	private Logger logger = LoggerFactory.getLogger(PlaceSearchProjectApplication.class);

	@Autowired
	private DataRepository dataRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(PlaceSearchProjectApplication.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	public void saveUserInfoAfterStartup() {

		List<UserInfo> list = new ArrayList<>();

		String word;
		for(int i = 0 ; i < 20 ; i++) {
			word = String.format("USER%02d", i);
			list.add(new UserInfo(word, CommonUtil.Encrypt.encrypt(word)));
		}
		
		dataRepository.saveAll(list);
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

}
