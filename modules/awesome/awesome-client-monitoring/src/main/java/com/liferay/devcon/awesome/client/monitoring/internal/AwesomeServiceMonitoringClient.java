package com.liferay.devcon.awesome.client.monitoring.internal;

import com.liferay.devcon.awesome.client.AwesomeClient;
import com.liferay.devcon.awesome.service.AwesomeService;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.osgi.service.component.annotations.ReferencePolicyOption;
@Component(
	immediate = true,
	property = {"osgi.command.function=monitor", "osgi.command.scope=awesome"},
	service = AwesomeClient.class
)
/**
 * @author Igor Arouca
 */
public class AwesomeServiceMonitoringClient implements AwesomeClient {

	@Reference(
		cardinality = ReferenceCardinality.MULTIPLE,
		policy = ReferencePolicy.DYNAMIC,
		policyOption = ReferencePolicyOption.GREEDY,
		unbind = "removeAwesomeService"
	)
	public void addAwesomeService(
		AwesomeService service, Map<String, Object> properties) {

		_services.put(getBehavior(properties), service);
	}

	public void monitor() {
		print();
	}

	@Override
	public void print() {
		if (_services.isEmpty()) {
			System.out.println("Nothing to say");
		}

		System.out.println("Services Available:");

		_services.forEach((key, value) -> {
			System.out.println("[" + key + "] = " + value.getAwesomeness());
		});
	}

	public void removeAwesomeService(
		AwesomeService service, Map<String, Object> properties) {

		_services.remove(getBehavior(properties));
	}

	private String getBehavior(Map<String, Object> properties) {
		return (String)properties.get("com.liferay.devcon.awesome.behavior");
	}

	private Map<String, AwesomeService> _services = new ConcurrentHashMap<>();

}