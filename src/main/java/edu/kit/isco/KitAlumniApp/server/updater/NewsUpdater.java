package edu.kit.isco.KitAlumniApp.server.updater;

import java.util.List;

import edu.kit.isco.KitAlumniApp.server.dataobject.DataAccessNews;
import edu.kit.isco.KitAlumniApp.server.dataobject.DataAccessObject;
import edu.kit.isco.KitAlumniApp.server.parser.NewsParser;
import edu.kit.isco.KitAlumniApp.server.dbservices.DbHandlerService;

public class NewsUpdater extends AbstractUpdater {
	
	public NewsUpdater(NewsParser parser) {
		super(parser);
	}

	@Override
	public boolean dataChanged(List<DataAccessObject> list) {
		List<DataAccessNews> load = DbHandlerService.getAllNews();
		return load.get(load.size()).equals(list.get(list.size()));
	}

	@Override
	public List<DataAccessObject> selectChangedItems(List<DataAccessObject> list) {
		List<DataAccessNews> load = DbHandlerService.getAllNews();
		DataAccessNews last = load.get(load.size() - 1);
		
		int size = list.size() - 1;
		while (size >= 0 && !last.equals(list.get(size)))
			size--;
		
		return list.subList(size, list.size() - 1);
	}

	@Override
	public boolean updateDb(List<DataAccessObject> list) {
		for (DataAccessObject news : list) {
			DbHandlerService.saveNews((DataAccessNews) news);
		}
		return true;
	}

	@Override
	public void sendNotification(List<DataAccessObject> list) {
		// TODO Auto-generated method stub
		
	}

}
