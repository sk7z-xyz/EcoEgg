package jp.minecraftuser.ecoegg.config;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.UUID;
import java.util.logging.Logger;

import jp.minecraftuser.ecoegg.db.EcoEggDB;
import jp.minecraftuser.ecoframework.PluginFrame;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

/**
 * YAML読み込みクラス
 *
 * @author ecolight
 */
public class LoaderYaml {
    private PluginFrame plg = null;
    private UUID uuid = null;
    protected Logger log = null;

    private FileConfiguration cnf = null;

    /**
     * コンストラクタ
     *
     * @param plg_
     * @param uuid_
     */
    public LoaderYaml(PluginFrame plg_, UUID uuid_) {
        plg = plg_;
        uuid = uuid_;
        log = plg.getLogger();
    }

    /**
     * コンフィグ再読み込み
     */
    public void reloadCnf() throws SQLException {
        EcoEggDB db = (EcoEggDB) plg.getDB("egg");
        Connection con = db.connect();

        if (db.isExistData(con, uuid)) {
            cnf = db.loadFromDB(con, uuid);
        } else {
            cnf = new YamlConfiguration();
        }
    }

    /**
     * 直接操作用にコンフィグインスタンスを取得する
     *
     * @return コンフィグファイルインスタンス
     */
    public FileConfiguration getCnf() throws SQLException {
        /**
         * まだ未ロードであれば読み込んでから返す
         */
        if (cnf == null) {
            this.reloadCnf();
        }
        return cnf;
    }

    /**
     * データベースにセーブする
     */
    public void saveCnf() throws SQLException {
        EcoEggDB db = (EcoEggDB) plg.getDB("egg");
        Connection con = db.connect();
        if (db.isExistData(con, uuid)) {
            db.updateToDB(con, uuid, cnf);
        } else {
            db.insertToDB(con, uuid, cnf);
        }

    }

}