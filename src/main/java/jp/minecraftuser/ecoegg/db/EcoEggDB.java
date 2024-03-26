package jp.minecraftuser.ecoegg.db;

import java.io.File;
import java.io.StringReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.UUID;

import jp.minecraftuser.ecoframework.DatabaseFrame;
import jp.minecraftuser.ecoframework.PluginFrame;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class EcoEggDB extends DatabaseFrame {

    public EcoEggDB(PluginFrame plg_, String dbname_, String name_) throws ClassNotFoundException, SQLException {
        super(plg_, dbname_, name_);
    }

    public EcoEggDB(PluginFrame plg_, String addr_, String user_, String pass_, String dbname_, String name_) throws ClassNotFoundException, SQLException {
        super(plg_, addr_, user_, pass_, dbname_, name_);
    }

    @Override
    protected void migrationData(Connection con) throws SQLException {
        // version 1 の場合、新規作成もしくは旧バージョンのデータベース引き継ぎの場合を検討する
        if (con == null) {
            System.out.println("con is null");
        } else {
            System.out.println("con is not null");
        }
        if (dbversion == 1) {
            if (justCreated) {

                String create_sql = "CREATE TABLE IF NOT EXISTS EcoEggData(ID UUID PRIMARY KEY, EggData TEXT)";
                PreparedStatement pstmt_create = con.prepareStatement(create_sql);
                pstmt_create.executeUpdate();
                con.commit();
                // 新規作成の場合、テーブル定義のみ作成して終わり

                File pluginDataFolder = plg.getDataFolder();

                for (File file : pluginDataFolder.listFiles()) {
                    if (file.getName().matches(".*\\.yml")) {
                        String sql = "INSERT INTO EcoEggData(ID, EggData) VALUES(?, ?)";
                        //例：ファイル名「3473890086364005309_-7341475336192907026.yml」を
                        //3473890086364005309_-7341475336192907026に変換して
                        //3473890086364005309と-7341475336192907026に分割して
                        //2つのLONG型に変換したものをUUIDに変換する
                        String[] split = file.getName().replace(".yml", "").split("_");
                        if (split.length != 2) {
                            continue;
                        }
                        String most = split[0];
                        String least = split[1];
                        UUID uuid = new UUID(Long.parseLong(most), Long.parseLong(least));

                        //ファイルを読み込んでデータベースに登録
                        YamlConfiguration yml = YamlConfiguration.loadConfiguration(file);

                        PreparedStatement pstmt_insert = con.prepareStatement(sql);
                        pstmt_insert.setObject(1, uuid);
                        pstmt_insert.setString(2, yml.saveToString());
                        pstmt_insert.executeUpdate();
                        con.commit();
                    }
                }

                // データベースバージョンは最新版数に設定する
                log.info("create " + name + " version 2");
                updateSettingsVersion(con, 2);

                con.commit();

                log.info(plg.getName() + " database migration " + name + " version 1 -> 2 completed.");

            } else {

                //-----------------------------------------------------------------------------------
                // データベースバージョンは次版にする
                //-----------------------------------------------------------------------------------
                updateSettingsVersion(con);

                log.info(plg.getName() + " database migration " + name + " version 1 -> 2 completed.");
            }
        }
    }

    public FileConfiguration loadFromDB(Connection con, UUID uuid) throws SQLException {
        PreparedStatement pstmt = null;
        try {
            String sql = "SELECT EggData FROM EcoEggData WHERE ID = ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setObject(1, uuid);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                String data = rs.getString("EggData");
                return YamlConfiguration.loadConfiguration(new StringReader(data));
            }
            return null;
        } finally {
            if (pstmt != null) {
                pstmt.close();
            }
        }
    }

    public boolean isExistData(Connection con, UUID uuid) throws SQLException {
        PreparedStatement pstmt = null;
        try {
            String sql = "SELECT COUNT(*) FROM EcoEggData WHERE ID = ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setObject(1, uuid);
            ResultSet rs = pstmt.executeQuery();
            rs.next();
            return rs.getInt(1) > 0;
        } finally {
            if (pstmt != null) {
                pstmt.close();
            }
        }
    }

    public void updateToDB(Connection con, UUID uuid, FileConfiguration cnf) throws SQLException {
        PreparedStatement pstmt = null;
        try {
            String sql = "UPDATE EcoEggData SET EggData = ? WHERE ID = ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, cnf.saveToString());
            pstmt.setObject(2, uuid);
            pstmt.executeUpdate();
            con.commit();
        } finally {
            if (pstmt != null) {
                pstmt.close();
            }
        }
    }

    public void insertToDB(Connection con, UUID uuid, FileConfiguration cnf) throws SQLException {
        PreparedStatement pstmt = null;
        try {
            String sql = "INSERT INTO EcoEggData(ID, EggData) VALUES(?, ?)";
            pstmt = con.prepareStatement(sql);
            pstmt.setObject(1, uuid);
            pstmt.setString(2, cnf.saveToString());
            pstmt.executeUpdate();
            con.commit();
        } finally {
            if (pstmt != null) {
                pstmt.close();
            }
        }
    }
}
