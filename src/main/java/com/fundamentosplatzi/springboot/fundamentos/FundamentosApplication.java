package com.fundamentosplatzi.springboot.fundamentos;

import com.fundamentosplatzi.springboot.fundamentos.bean.MyBean;
import com.fundamentosplatzi.springboot.fundamentos.bean.MyBeanWithDependency;
import com.fundamentosplatzi.springboot.fundamentos.bean.MyBeanWithProperties;
import com.fundamentosplatzi.springboot.fundamentos.component.ComponentDependency;
import com.fundamentosplatzi.springboot.fundamentos.entity.User;
import com.fundamentosplatzi.springboot.fundamentos.pojo.UserPojo;
import com.fundamentosplatzi.springboot.fundamentos.repository.UserRepository;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Sort;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class FundamentosApplication implements CommandLineRunner {

	private final Log LOGGER= LogFactory.getLog(FundamentosApplication.class);

	private UserPojo userPojo;
	private ComponentDependency componentDependency;
	private MyBean myBean;

	private MyBeanWithDependency myBeanWithDependency;

	private MyBeanWithProperties myBeanWithProperties;

	private UserRepository userRepository;


	@Autowired
	public FundamentosApplication(@Qualifier("componentTwoImplement") ComponentDependency componentDependency,
								  MyBean myBean, MyBeanWithDependency myBeanWithDependency, MyBeanWithProperties myBeanWithProperties,
								  UserRepository userRepository){
	this.componentDependency=componentDependency;
	this.myBean=myBean;
	this.myBeanWithDependency=myBeanWithDependency;
	this.myBeanWithProperties=myBeanWithProperties;
	this.userRepository=userRepository;

	}

	public static void main(String[] args) {
		SpringApplication.run(FundamentosApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		saveUsersInDatabase();
		getInformation();
	}

	public void getInformation(){
	LOGGER.info(userRepository.findByUserEmail("emmanuel9927@hotmail.com").
			orElseThrow(()->new RuntimeException("User not found")));

	userRepository.findAndSort("M", Sort.by("id").descending()).stream().
			forEach(user -> LOGGER.info("User with method sort: "+user));

		userRepository.findByName("Manolo").stream().
				forEach(user -> LOGGER.info("User find by name: "+user));

		userRepository.findByEmailAndName("Manolo@hotmail.com","Manolo").stream().
				forEach(user -> LOGGER.info("User with query methods name and email: "+user));

		userRepository.findByNameLike("%M%").stream().
				forEach(user -> LOGGER.info("User with findByNameLike : "+user));
		userRepository.findByNameOrEmail(null,"emmanuel9927@hotmail.com").stream().
				forEach(user -> LOGGER.info("User with findByNameOrEmail : "+user));

		userRepository.findByBirthDateBetween(LocalDate.of(2021, 3,1),
						LocalDate.of(2021, 4,2)).stream().
				forEach(user -> LOGGER.info("User with interval of dates : "+user));

		userRepository.findByNameLikeOrderByIdDesc("%M%").stream().
				forEach(user -> LOGGER.info("User with findByNameLikeOrderByIdDesc : "+user));

		userRepository.findByNameContainingOrderByIdDesc("M").stream().
				forEach(user -> LOGGER.info("User with findByNameContainingOrderByIdDesc : "+user));


		LOGGER.info("The user find with getAllByBirthDateAndEmail: "+userRepository.getAllByBirthDateAndEmail(LocalDate.of(2021,3,20),"Manolirris@hotmail.com").
				orElseThrow(()-> new RuntimeException("User not found with params")));


	}

	private  void saveUsersInDatabase(){
		User one= new User("Emmanuel","emmanuel9927@hotmail.com", LocalDate.of(2021,03,20));
		User two= new User("Manolo","Manolo@hotmail.com", LocalDate.of(2021,4,20));
		User three= new User("Manolirris","Manolirris@hotmail.com", LocalDate.of(2021,3,20));
		User four= new User("Manolin","Manolin@hotmail.com", LocalDate.of(2021,03,20));
		User five= new User("Manu","Manu@hotmail.com", LocalDate.of(2021,2,20));
		User six= new User("Manuel","Manuel@hotmail.com", LocalDate.of(2021,1,20));
		User seven= new User("Samuel","Samuel@hotmail.com", LocalDate.of(2021,6,20));
		User eight= new User("Juan manuel","Juan@hotmail.com", LocalDate.of(2021,7,20));
		User nine= new User("Emanuel","Emanuel@hotmail.com", LocalDate.of(2021,8,20));
		User ten= new User("Mamuel","Mamuesl@hotmail.com", LocalDate.of(2021,9,20));

		List<User> list= Arrays.asList(one,two,three,four,five,six,seven,eight,nine,ten);
		list.forEach(userRepository::save);
	}



	private void ejemplosAnteriores(){
		componentDependency.saludar();
		myBean.print();
		myBeanWithDependency.printWithDependency();
		System.out.println(myBeanWithProperties.function());
		System.out.println(userPojo.getEmail()+" - "+userPojo.getPassword());
		LOGGER.error("Esto es un error del aplicativo");

		try {
			//error
			int value=10/0;


		}catch (Exception e){
			LOGGER.error("Esto es un error al dividir por cero "+ e.getMessage());
		}
	}
}
