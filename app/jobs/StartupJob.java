package jobs;

import models.AirCompany;
import play.Logger;
import play.jobs.Job;
import play.jobs.OnApplicationStart;
import play.test.Fixtures;

/**
 * Created with IntelliJ IDEA.
 * User: reyoung
 * Date: 3/22/13
 * Time: 3:00 PM
 * To change this template use File | Settings | File Templates.
 */
@OnApplicationStart
public class StartupJob extends Job{
    @Override
    public void doJob(){
        if (AirCompany.count()==0){
            Logger.info("Load Initial Data");
            Fixtures.loadModels("initial-data.yml");
        }
    }
}
