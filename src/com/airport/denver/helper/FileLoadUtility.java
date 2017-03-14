package com.airport.denver.helper;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import com.airport.denver.BaggageSystem;
import com.airport.denver.model.Baggage;
import com.airport.denver.model.ConveyorRoute;
import com.airport.denver.model.Departure;

public class FileLoadUtility {

	public static Map<String, List<ConveyorRoute>> readConveyorBeltInfo() {
		Map<String, List<ConveyorRoute>> conveyorBeltInfo = new HashMap<>();
		FileReader conveyorInfoFile;
		try {
			URL url = BaggageSystem.class.getResource("conveyorInfo.txt");
			conveyorInfoFile = new FileReader(url.getFile());
			BufferedReader br = new BufferedReader(conveyorInfoFile);
			String line;
			while ((line = br.readLine()) != null) {
				int j = 0;
				StringTokenizer tokenizer = new StringTokenizer(line, ",");

				ConveyorRoute conveyorBeltRoute = null;
				while (tokenizer.hasMoreTokens()) {
					String token = tokenizer.nextToken();

					boolean readRecord = false;

					if (j == 0) {
						conveyorBeltRoute = new ConveyorRoute();
						conveyorBeltRoute.setSource(token);
					}
					if (j == 1) {
						conveyorBeltRoute.setDestination(token);
					}
					if (j == 2) {
						conveyorBeltRoute.setTime(Integer.parseInt(token));
						readRecord = true;
					}
					j++;
					if (readRecord) {
						if (conveyorBeltInfo.get(conveyorBeltRoute.getSource()) != null) {
							List<ConveyorRoute> conveyorBeltInfos = conveyorBeltInfo.get(conveyorBeltRoute.getSource());
							conveyorBeltInfos.add(conveyorBeltRoute);
							conveyorBeltInfo.put(conveyorBeltRoute.getSource(), conveyorBeltInfos);
						} else {
							List<ConveyorRoute> conveyorBeltInfos = new ArrayList<>();
							conveyorBeltInfos.add(conveyorBeltRoute);
							conveyorBeltInfo.put(conveyorBeltRoute.getSource(), conveyorBeltInfos);
						}
						readRecord = false;
					}
				}
			}
			br.close();
			conveyorInfoFile.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return conveyorBeltInfo;
	}

	public static Map<String, Baggage> readBaggageInfo() {
		Map<String, Baggage> baggages = new HashMap<>();
		FileReader baggageFileInfo;
		try {
			URL url = BaggageSystem.class.getResource("Baggage.txt");
			baggageFileInfo = new FileReader(url.getFile());
			BufferedReader br = new BufferedReader(baggageFileInfo);
			String line;
			while ((line = br.readLine()) != null) {
				int j = 0;
				Baggage bag = new Baggage();
				StringTokenizer tokenizer = new StringTokenizer(line, ",");
				while (tokenizer.hasMoreTokens()) {
					if (j == 0) {
						bag.setBag_number(tokenizer.nextToken());
					}
					if (j == 1) {
						bag.setEntry_point(tokenizer.nextToken());
					}
					if (j == 2) {
						bag.setFlight_id(tokenizer.nextToken());
					}
					j++;
				}
				baggages.put(bag.getBag_number(), bag);
			}
			br.close();
			baggageFileInfo.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return baggages;
	}

	public static Map<String, Departure> readDepartureInfo() {

		Map<String, Departure> departures = new HashMap<>();

		FileReader departureInfoFile = null;
		try

		{
			URL url = BaggageSystem.class.getResource("departureInfo.txt");
			departureInfoFile = new FileReader(url.getFile());
			BufferedReader br = new BufferedReader(departureInfoFile);
			String line;
			while ((line = br.readLine()) != null) {
				int j = 0;
				Departure departure = new Departure();
				StringTokenizer tokenizer = new StringTokenizer(line, ",");
				while (tokenizer.hasMoreTokens()) {
					if (j == 0) {
						departure.setFlight_id(tokenizer.nextToken());
					}
					if (j == 1) {
						departure.setFlight_gate(tokenizer.nextToken());
					}
					if (j == 2) {
						departure.setDestination(tokenizer.nextToken());
					}
					if (j == 3) {
						departure.setFlight_time(tokenizer.nextToken());
					}
					j++;
				}
				departures.put(departure.getFlight_id(), departure);
			}
			br.close();
			departureInfoFile.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return departures;
	}

}
