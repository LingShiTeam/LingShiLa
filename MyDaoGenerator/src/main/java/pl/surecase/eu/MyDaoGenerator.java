package pl.surecase.eu;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

public class MyDaoGenerator {

    public static void main(String args[]) throws Exception {
        // 1: 数据库版本号
        Schema schema = new Schema(8, "greendao.searchbean");
        schema.setDefaultJavaPackageDao("greendao.searchbean");
        // 上面这两个文件夹路径都可以自定义，也可以不设置
        // 初始化表
        initSearch(schema);

        // 自动创建(这里的args[0]就是调用刚才设定的目录）
        new DaoGenerator().generateAll(schema, args[0]);
    }

    /**
     * 初始化search表
     * @param schema
     */
    private static void initSearch(Schema schema) {
        Entity searchHistory = schema.addEntity("SearchHistory");
        // 主键，索引
        // 与在 Java 中使用驼峰命名法不同，默认数据库中的命名是使用大写和下划线来分割单词的。
        //searchHistory.addLongProperty("id").primaryKey().autoincrement().index();
        searchHistory.addIdProperty().primaryKey().index();
        searchHistory.addStringProperty("record");
        searchHistory.addStringProperty("time");
    }
}
