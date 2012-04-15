package aQute.lib.deployer.obr;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import aQute.bnd.service.OBRResolutionMode;
import aQute.bnd.service.ResolutionPhase;
import aQute.lib.deployer.repository.AbstractIndexedRepo;
import aQute.lib.deployer.repository.FixedIndexedRepo;

@SuppressWarnings("deprecation")
public class Conversions {
	
	public static final Map<String, String> convertConfig(Map<String, String> map) {
		if (!map.containsKey(AbstractIndexedRepo.PROP_REPO_TYPE))
			map.put(AbstractIndexedRepo.PROP_REPO_TYPE, AbstractIndexedRepo.REPO_TYPE_OBR);
		
		String location = map.get("location");
		if (location != null)
			map.put(FixedIndexedRepo.PROP_LOCATIONS, location);
		
		return map;
	}
	
	public static final Set<OBRResolutionMode> convertResolutionPhases(Collection<ResolutionPhase> phases) {
		Set<OBRResolutionMode> modes = new HashSet<OBRResolutionMode>();
		for (ResolutionPhase phase : phases) {
			OBRResolutionMode mode;
			switch (phase) {
			case build:
				mode = OBRResolutionMode.build;
				break;
			case runtime:
				mode = OBRResolutionMode.runtime;
				break;
			default:
				throw new IllegalArgumentException("");
			}
			modes.add(mode);
		}
		return modes;
	}
}
