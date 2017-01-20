package io.srqsoftware;

import org.springframework.stereotype.Component;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

@Component
public class GpioPinService implements PinService {

	private final GpioController gpio;
	private final GpioPinDigitalOutput pin;
	
	public GpioPinService() {
		gpio = GpioFactory.getInstance();		
		pin = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_01, "Door", PinState.HIGH);
	}
	
	@Override
	public void toggleDoor() {
		try {
			pin.setShutdownOptions(true, PinState.LOW);
			System.out.println("--> GPIO state should be: ON");


			// turn on gpio pin #01 for 1 second and then off
			System.out.println("--> GPIO state should be: ON for only 1 second");
			pin.pulse(500, true); // set second argument to 'true' use a
									// blocking call
	        // stop all GPIO activity/threads by shutting down the GPIO controller
	        // (this method will forcefully shutdown all GPIO monitoring threads and scheduled tasks)
	        gpio.shutdown();

	        System.out.println("Exiting ControlGpioExample");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
