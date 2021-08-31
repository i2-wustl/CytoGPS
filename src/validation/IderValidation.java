package validation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import business.DerEvent;
import business.Event;

/**
 * @author Lin Zhang
 * Programmer / Software Development Engineer
 * Institute for Informatics
 * Washington University School of Medicine in St. Louis
 * 
 * Date: August 30, 2021 
 */
public class IderValidation extends DerivativeValidation {
	
	private String cen;
	private String derChrArm;
	
	public IderValidation() {}
	public IderValidation(DerEvent e) {
		super(e);
		rawStringList.clear();
		segments.clear();
		this.cen = e.getBreakpointsFullName(e.getChrList(), e.getBreakpoints()).get(0).get(0);
		this.derChrArm = getChrArm(cen);
		rawStringList.add(cen + "M0");
		rawStringList.add(derChrArm + "terM0");
		segments.add(Arrays.asList(rawStringList.get(0), rawStringList.get(1)));
	}
	
	public DerivativeValidationOutcome getIderValidationOutcome() {
		boolean validDerivativeChr = true;
		outer:
		for (int i: eventIndexList) {
			Event subevent = subevents.get(i);
			if (!subevent.isUncertainEvent()) {
				List<List<String>> breakpointsFullName = subevent.getBreakpointsFullName(subevent.getChrList(), subevent.getBreakpoints());
				switch (subevent.getNature()) {
					case "add": {
						if (!isValidAdd(breakpointsFullName, derChr, eventIndexList, i)) {
							validDerivativeChr = false;
							break outer;
						}
						break;
					}
					case "del": {
						if (!isValidDel(breakpointsFullName, derChr, eventIndexList, i)) {
							validDerivativeChr = false;
							break outer;
						}
						break;
					}
					case "dup": {
						if (!isValidDup(breakpointsFullName, derChr, eventIndexList, i)) {
							validDerivativeChr = false;
							break outer;
						}
						break;
					}
					case "trp": {
						if (!isValidTrp(breakpointsFullName, derChr, eventIndexList, i)) {
							validDerivativeChr = false;
							break outer;
						}
						break;
					}
					case "qdp": {
						if (!isValidQdp(breakpointsFullName, derChr, eventIndexList, i)) {
							validDerivativeChr = false;
							break outer;
						}
						break;
					}
					case "ins": {
						if (!isValidIns(breakpointsFullName, derChr, eventIndexList, i)) {
							validDerivativeChr = false;
							break outer;
						}
						break;
					}
					case "inv": {
						if (!isValidInv(breakpointsFullName, derChr, eventIndexList, i)) {
							validDerivativeChr = false;
							break outer;
						}
						break;
					}
					case "t": {
						if (!isValidT(breakpointsFullName, derChr, eventIndexList, i)) {
							validDerivativeChr = false;
							break outer;
						}
						break;
					}
					case "hsr": {
						if (!isValidHsr(breakpointsFullName, derChr, eventIndexList, i)) {
							validDerivativeChr = false;
							break outer;
						}
						break;
					}
				}
			}
		}		
		if (validDerivativeChr) {
			// Record Loss of the opposite of derChrArm
			String oppositeArm = getOppositeChrArm(derChrArm);
			List<List<String>> deletedSegments = new ArrayList<>();
			deletedSegments.add(Arrays.asList(oppositeArm + "terM0", oppositeArm + "10M0"));
			recordLoss(deletedSegments, derChrGainedSegments);
			// Record the gain of the mirror
			List<List<Integer>> iderKaryotypeLGF = getDerKaryotypeLGF();
            IntStream.range(0, iderKaryotypeLGF.get(1).size())
                     .filter(i -> iderKaryotypeLGF.get(1).get(i) > 0)
                     .forEach(i -> iderKaryotypeLGF.get(1).set(i, iderKaryotypeLGF.get(1).get(i) * 2));             
            segments.stream().filter(seg -> seg.size() > 1 && getMark(seg).equals("M0") && !containsSegment(derChrGainedSegments, seg))
                    .flatMap(segment -> getAllSegmentBands(segment).stream())
                    .distinct()
                    .forEach(band -> 
	                                {
	                                	int index = chrToIndexMap.get(band); 
	                                	iderKaryotypeLGF.get(1).set(index, iderKaryotypeLGF.get(1).get(index) + 1);
	                                }); // For remaining (unduplicated) chrBreakpoints on derChrArm [It's not necessary to add && !containsSegment(derChrGainedSegments, seg) in the filter]
			// Making mirror image
			List<String> rawStringListCopy = new ArrayList<>(rawStringList);
			Collections.reverse(rawStringListCopy);
			rawStringList.addAll(0, rawStringListCopy);
			segments = getSegments(rawStringList);			
			// Update fusion outcome
			updateKaryotypeFusionOutcome(iderKaryotypeLGF);			
			return new DerivativeValidationOutcome(validDerivativeChr, segments, getDetailedSystem(), iderKaryotypeLGF);
		} else {
			return new DerivativeValidationOutcome(validDerivativeChr, segments, null, null);
		}		
	}
	
	private void updateKaryotypeFusionOutcome(List<List<Integer>> iderKaryotypeLGF) {
		List<Integer> karyotypeFusionOutcome = iderKaryotypeLGF.get(2);
		IntStream.range(0, karyotypeFusionOutcome.size()).filter(i -> karyotypeFusionOutcome.get(i) > 0).forEach(i -> karyotypeFusionOutcome.set(i, 0));
		List<String> fusionPointsList = getFusionPoints();
		for (String fusionPoint: fusionPointsList) {
			int index;
			if (chrToIndexMap.get(fusionPoint) == null) {
				for (String subband: getAllKeys(fusionPoint)) {
					index = chrToIndexMap.get(subband);
					karyotypeFusionOutcome.set(index, karyotypeFusionOutcome.get(index) + 1);
				}
			} else {
				index = chrToIndexMap.get(fusionPoint);
				karyotypeFusionOutcome.set(index, karyotypeFusionOutcome.get(index) + 1);
			}
		}
	}

}
