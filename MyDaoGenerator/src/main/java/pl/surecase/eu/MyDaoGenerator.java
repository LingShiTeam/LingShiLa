package pl.surecase.eu;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

public class MyDaoGenerator {

    public static void main(String args[]) throws Exception {
        Schema schema = new Schema(8, "greendao.searchbean");
        schema.setDefaultJavaPackageDao("greendao.searchbean");
        initSearch(schema);

        new DaoGenerator().generateAll(schema, args[0]);
    }

    /**
     * @param schema
     */
    private static void initSearch(Schema schema) {
        Entity searchHistory = schema.addEntity("SearchHistory");
        searchHistory.addIdProperty().primaryKey().index();
        searchHistory.addStringProperty("record");
        searchHistory.addStringProperty("time");
    }
}
