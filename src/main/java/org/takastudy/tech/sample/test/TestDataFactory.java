package org.takastudy.tech.sample.test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.takastudy.tech.sample.model.LineItem;

public class TestDataFactory {

	private static List<TestData> userDataList;
	private static List<String> txtDataList;

	private static SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	
	private static Random rnd = new Random();
	

	static {
		loadData();
	}

	public static List<LineItem> createTestData(int count) {
		
		List<LineItem> list = new ArrayList<>();
		
		for(int i=0;i<count;i++){
			list.add(oneTestData());
		}

		return list;
	}

	private static LineItem oneTestData() {
		LineItem item = new LineItem();
		
		String id = UUID.randomUUID().toString();
		TestData userData = userDataList.get(rnd.nextInt(userDataList.size()));
		TestData dateData = userDataList.get(rnd.nextInt(userDataList.size()));
		String txtData = txtDataList.get(rnd.nextInt(txtDataList.size()));
		
		item.setId(id);
		item.setUserid(userData.getEmail());
		item.setName(userData.getName());
		item.setDate(dateData.getEntry());
		item.setContents(txtData);

		return item;
	}

	private static void loadData() {

		try (Stream<String> mainDataStream = Files.lines(Paths.get("3710_20161218235250.csv"))) {

			userDataList = mainDataStream.map(line -> {
				String[] item = line.split(",");
				Date date = new Date();
				try {
					date = format.parse(item[2]);

				} catch (ParseException e) {
					e.printStackTrace();
				}

				return new TestData(item[0], item[1], date);

			}).collect(Collectors.toList());

		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			txtDataList = Files.readAllLines(Paths.get("textdata.txt"));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
