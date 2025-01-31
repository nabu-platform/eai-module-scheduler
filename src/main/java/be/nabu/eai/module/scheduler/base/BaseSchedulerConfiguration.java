/*
* Copyright (C) 2015 Alexander Verbruggen
*
* This program is free software: you can redistribute it and/or modify
* it under the terms of the GNU Lesser General Public License as published by
* the Free Software Foundation, either version 3 of the License, or
* (at your option) any later version.
*
* This program is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
* GNU Lesser General Public License for more details.
*
* You should have received a copy of the GNU Lesser General Public License
* along with this program. If not, see <https://www.gnu.org/licenses/>.
*/

package be.nabu.eai.module.scheduler.base;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import be.nabu.eai.api.Enumerator;
import be.nabu.eai.api.EnvironmentSpecific;
import be.nabu.eai.api.ValueEnumerator;
import be.nabu.eai.module.scheduler.provider.SchedulerProviderArtifact;
import be.nabu.eai.repository.jaxb.ArtifactXMLAdapter;
import be.nabu.eai.repository.util.KeyValueMapAdapter;
import be.nabu.libs.services.api.DefinedService;

@XmlType(propOrder = { "enabled", "startImmediately", "service", "amountOfTimes", "provider", "allowOverlap", "targets", "properties" })
public class BaseSchedulerConfiguration {
	
	private boolean enabled;
	private DefinedService service;
	private Map<String, String> properties;
	private Long amountOfTimes;
	private SchedulerProviderArtifact provider;
	private boolean allowOverlap;
	private List<String> targets;
	private boolean startImmediately;

	@EnvironmentSpecific
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
	@NotNull
	@XmlJavaTypeAdapter(value = ArtifactXMLAdapter.class)
	public DefinedService getService() {
		return service;
	}
	public void setService(DefinedService service) {
		this.service = service;
	}

	@XmlJavaTypeAdapter(value = KeyValueMapAdapter.class)
	public Map<String, String> getProperties() {
		if (properties == null) {
			properties = new LinkedHashMap<String, String>();
		}
		return properties;
	}
	public void setProperties(Map<String, String> properties) {
		this.properties = properties;
	}
	
	public Long getAmountOfTimes() {
		return amountOfTimes;
	}
	public void setAmountOfTimes(Long amountOfTimes) {
		this.amountOfTimes = amountOfTimes;
	}
	
	public boolean isStartImmediately() {
		return startImmediately;
	}
	public void setStartImmediately(boolean startImmediately) {
		this.startImmediately = startImmediately;
	}
	
	@NotNull
	@EnvironmentSpecific
	@XmlJavaTypeAdapter(value = ArtifactXMLAdapter.class)
	public SchedulerProviderArtifact getProvider() {
		return provider;
	}
	public void setProvider(SchedulerProviderArtifact provider) {
		this.provider = provider;
	}
	
	public boolean isAllowOverlap() {
		return allowOverlap;
	}
	public void setAllowOverlap(boolean allowOverlap) {
		this.allowOverlap = allowOverlap;
	}
	
	@EnvironmentSpecific
	@ValueEnumerator(enumerator = TargetEnumerator.class)
	public List<String> getTargets() {
		return targets;
	}
	public void setTargets(List<String> target) {
		this.targets = target;
	}
	
	public static class TargetEnumerator implements Enumerator {

		@Override
		public List<?> enumerate() {
			List<String> targets = new ArrayList<String>();
			targets.add("$any");
			targets.add("$all");
			return targets;
		}
		
	}
}
