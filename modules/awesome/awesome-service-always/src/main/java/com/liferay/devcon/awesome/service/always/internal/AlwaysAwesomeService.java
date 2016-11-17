package com.liferay.devcon.awesome.service.always.internal;

import com.liferay.devcon.awesome.service.AwesomeService;

import org.osgi.service.component.annotations.Component;
@Component(
	immediate = true,
	property = {
		"com.liferay.devcon.awesome.behavior=always",
		"service.ranking:Integer=10"
	},
	service = AwesomeService.class
)
/**
 * @author Igor Arouca
 */
public class AlwaysAwesomeService implements AwesomeService {

	@Override
	public String getAwesomeness() {
		return "Always Awesome!";
	}

}