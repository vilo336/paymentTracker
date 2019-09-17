package run;

import java.util.Timer;
import java.util.TimerTask;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import ui.ConsoleUi;

/*
 * Author 
 * Viliam Dlugos*/

@SpringBootApplication
public class SpringRun {

	public static void main(String[] args) {
		SpringApplication.run(SpringRun.class, args);
	}

	@Bean
	public ConsoleUi consoleUI() {
		return new ConsoleUi();
	}

	@Bean
	public CommandLineRunner runner(ConsoleUi consoleUi) {
		return (args) -> {
			System.out.println("Running");

			Timer t = new Timer();
			t.schedule(new TimerTask() {
				@Override
				public void run() {
					consoleUi.listAllPayments();
				}
			}, 0, 60000);

			consoleUi.inputData();

		};
	}
}
