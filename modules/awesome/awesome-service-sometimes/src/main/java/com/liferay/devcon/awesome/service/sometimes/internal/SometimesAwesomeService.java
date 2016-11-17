package com.liferay.devcon.awesome.service.sometimes.internal;

import com.liferay.devcon.awesome.service.AwesomeService;

import java.util.concurrent.atomic.AtomicInteger;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
@Component(
	immediate = true,
	property = {
		"com.liferay.devcon.awesome.behavior=sometimes",
		"service.ranking:Integer=1"
	},
	service = AwesomeService.class
)
/**
 * @author Igor Arouca
 */
public class SometimesAwesomeService implements AwesomeService {

	@Activate
	public void activate() {
		_awesomeCount = new AtomicInteger(0);
	}

	@Override
	public String getAwesomeness() {
		int n = _awesomeCount.getAndIncrement();

		switch (n % 3) {
			case 0:
				return "Still Awesome!";
			case 1:
				return "Even more Awesome!!!";
			case 2:
				return "Just Moderately Awesome";
			default:
				return "huh?";
		}
	}

	private AtomicInteger _awesomeCount;

}