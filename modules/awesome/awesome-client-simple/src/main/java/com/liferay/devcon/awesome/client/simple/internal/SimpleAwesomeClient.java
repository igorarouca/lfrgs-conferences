package com.liferay.devcon.awesome.client.simple.internal;

import com.liferay.devcon.awesome.client.AwesomeClient;
import com.liferay.devcon.awesome.service.AwesomeService;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.osgi.service.component.annotations.ReferencePolicyOption;
@Component(
	immediate = true,
	property = {"osgi.command.function=print", "osgi.command.scope=awesome"},
	service = AwesomeClient.class
)
/**
 * @author Igor Arouca
 */
public class SimpleAwesomeClient implements AwesomeClient {

	@Override
	public void print() {

		// This code is just to illustrate how OSGi service references work

		System.out.print("'Always' awesome service reply:\t\t");
		if (_alwaysAwesome == null) {
			System.out.println("service not available");
		}
		else {
			System.out.println(_alwaysAwesome.getAwesomeness());
		}

		System.out.print("'Rank-Based' awesome service reply:\t");
		if (_rankBasedAwesome == null) {
			System.out.println("service not available");
		}
		else {
			System.out.println(_rankBasedAwesome.getAwesomeness());
		}

		System.out.print("'Sometimes' awesome service reply:\t");
		if (_sometimesAwesome == null) {
			System.out.println("service not available");
		}
		else {
			System.out.println(_sometimesAwesome.getAwesomeness());
		}
	}

	@Reference(
		cardinality = ReferenceCardinality.OPTIONAL,
		target ="(com.liferay.devcon.awesome.behavior=always)"
	)
	private volatile AwesomeService _alwaysAwesome;

	@Reference(
		cardinality = ReferenceCardinality.OPTIONAL,
		policy = ReferencePolicy.DYNAMIC,
		policyOption = ReferencePolicyOption.GREEDY
	)
	private volatile AwesomeService _rankBasedAwesome;

	@Reference(
		cardinality = ReferenceCardinality.OPTIONAL,
		target ="(com.liferay.devcon.awesome.behavior=sometimes)"
	)
	private volatile AwesomeService _sometimesAwesome;

}